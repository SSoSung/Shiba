package time.clock.shiba.api;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import time.clock.shiba.domain.User;
import time.clock.shiba.global.common.ShibaApiResponse;
import time.clock.shiba.global.common.ShibaStatus;
import time.clock.shiba.record.request.CreateUser;
import time.clock.shiba.service.UserService;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "사용자 컨트롤러", description = "사용자 컨트롤을 한다")
@RequiredArgsConstructor
@RequestMapping("/api/user")
@RestController
public class UserResource {

    //    나만의 규칙
    //    C -> void
    //    R -> return
    //    U -> void
    //    D -> void

    private final UserService userService;

    @Operation(summary = "회원가입 api", description = "회원가입을 진행한다.")
    @PostMapping
    public ResponseEntity<ShibaApiResponse> createUser(@Valid @RequestBody CreateUser request){
        userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ShibaApiResponse(ShibaStatus.CREATED, "회원가입완료"));
    }

    @Operation(summary = "회원탈퇴 api", description = "회원탈퇴(소프트삭제)을 진행한다.")
    @DeleteMapping
    public ResponseEntity<ShibaApiResponse> deleteUser(){
        userService.deleteUser();
        return ResponseEntity.status(HttpStatus.OK).body(new ShibaApiResponse(ShibaStatus.SUCCESS, "회원탈퇴완료"));
    }

    @Operation(summary="Access Token 재발급", description="Access Token이 만료되면 재발급한다.(Refresh Token이 만료되면 다시 로그인 시키는 구조)," +
            "Access Token을 던졌을시 기간이 만료되었으면 FORBIDDEN에러가 발생!" +
            "에러가 발생하면 헤더에 Refresh Token을 넣어서 보낸다.")
    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("NexaDsAndTanHaySCM".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String userId = decodedJWT.getSubject();
                User user = userService.getUser(userId);
                String access_token = JWT.create()
                        .withSubject(user.getUserId())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 120 * 60 * 1000)) // 2시간
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("role", "사용자") // 임시
                        .sign(algorithm);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            }catch (Exception e){
                response.setHeader("error", e.getMessage());
                response.setStatus(FORBIDDEN.value());
                //response.sendError(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", e.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        }else{
            throw new RuntimeException("Refresh token is missing");
        }
    }

}
