package time.clock.shiba.record.request;

import jakarta.validation.constraints.NotBlank;

public record DeleteUseTime(
        @NotBlank(message = "카테고리ID는 공백일수 없습니다.") Integer categoryId,
        @NotBlank(message = "시간저장일자는 공백일수 없습니다.") String ymd,
        @NotBlank(message = "시간저장순번 공백일수 없습니다.") Integer ymdSeq
) {


}
