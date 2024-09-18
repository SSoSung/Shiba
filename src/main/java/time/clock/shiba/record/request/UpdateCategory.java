package time.clock.shiba.record.request;

import jakarta.validation.constraints.NotBlank;

public record UpdateCategory(
        @NotBlank(message = "카테고리아이디는 공백일수 없습니다.") Integer categoryId,
        @NotBlank(message = "카테고리명은 공백일수 없습니다.") String categoryNm
) {

}
