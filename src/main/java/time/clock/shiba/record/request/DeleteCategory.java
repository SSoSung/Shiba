package time.clock.shiba.record.request;

import jakarta.validation.constraints.NotBlank;

public record DeleteCategory(
        @NotBlank(message = "카테고리아이디는 공백일수 없습니다.") Integer categoryId
) {

}
