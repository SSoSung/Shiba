package time.clock.shiba.record.request;

import jakarta.validation.constraints.NotBlank;
import time.clock.shiba.domain.Category;

public record CreateCategory(
        @NotBlank(message = "카테고리명은 공백일수 없습니다.") String categoryNm
) {

    public Category toEntity(String userId, Integer categoryId, String categoryNm){
        return Category.builder()
                .userId(userId)
                .categoryId(categoryId)
                .categoryNm(categoryNm)
                .useYn("N")
                .build();
    }
}
