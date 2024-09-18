package time.clock.shiba.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import time.clock.shiba.global.common.ShibaApiResponse;
import time.clock.shiba.global.common.ShibaStatus;
import time.clock.shiba.record.request.CreateUseTime;
import time.clock.shiba.record.request.DeleteUseTime;
import time.clock.shiba.service.UseTimeService;

import java.util.List;

@Tag(name = "저장시간 컨트롤러", description = "저장시간 컨트롤을 한다")
@RequiredArgsConstructor
@RequestMapping("/api/use-time")
@RestController
public class UseTimeResource {

    private final UseTimeService useTimeService;

    @Operation(summary = "시간 저장 api", description = "유저별 시간 저장을 진행한다.")
    @PostMapping
    public ResponseEntity<ShibaApiResponse> createUseTime(@Valid @RequestBody CreateUseTime request){
        useTimeService.createUseTime(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ShibaApiResponse(ShibaStatus.CREATED, "시간저장완료"));
    }

    @Operation(summary = "시간 정보 삭제 api", description = "유저별 시간 정보 삭제(소프트)를 진행한다.")
    @DeleteMapping
    public ResponseEntity<ShibaApiResponse> deleteUseTime(@Valid @RequestBody DeleteUseTime request){
        useTimeService.deleteUseTime(request);
        return ResponseEntity.status(HttpStatus.OK).body(new ShibaApiResponse(ShibaStatus.SUCCESS, "시간저장삭제완료"));
    }


}
