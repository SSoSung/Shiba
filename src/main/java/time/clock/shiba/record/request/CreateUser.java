package time.clock.shiba.record.request;

import jakarta.validation.constraints.NotBlank;
import org.springframework.security.crypto.password.PasswordEncoder;
import time.clock.shiba.domain.User;

public record CreateUser(
        @NotBlank(message = "ID는 공백일수 없습니다.") String userId,
        @NotBlank(message = "PW는 공백일수 없습니다.") String userPw,
        @NotBlank(message = "사용자명은 공백일수 없습니다.") String userNm,
        String userEmail,
        String userPhone
) {

    public User toEntity(CreateUser request, PasswordEncoder passwordEncoder){
        return User.builder()
                .userId(request.userId())
                .userPw(passwordEncoder.encode(request.userPw()))
                .userNm(request.userNm())
                .userEmail(request.userEmail())
                .userPhone(request.userPhone())
                .useYn("N")
                .build();
    }

}
