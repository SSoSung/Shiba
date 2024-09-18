package time.clock.shiba.record.request;

import jakarta.validation.constraints.NotBlank;
import time.clock.shiba.domain.UseTime;

public record CreateUseTime(
        @NotBlank(message = "시간은 공백일수 없습니다.") Integer categoryId,
        @NotBlank(message = "시간은 공백일수 없습니다.") Long second
) {

    public UseTime toEntity(String userId, Integer categoryId, String ymd, Integer ymdSeq, Long second){
        return UseTime.builder()
                .userId(userId)
                .categoryId(categoryId)
                .ymd(ymd)
                .ymdSeq(ymdSeq)
                .delYn("N")
                .build();
    }

}
