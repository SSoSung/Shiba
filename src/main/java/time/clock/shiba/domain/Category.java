package time.clock.shiba.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import time.clock.shiba.domain.id.CategoryId;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(CategoryId.class)
@Table(name = "CATEGORY")
@Entity
public class Category extends BaseEntity{

    @Id
    @Column(name = "USER_ID", length = 100)
    private String userId;

    @Id
    @Column(name = "CATEGORY_ID")
    private Integer categoryId;

    @Column(name = "CATEGORY_NM", length = 100)
    private String categoryNm;

    @Column(name = "USE_YN", length = 1)
    private String useYn;

    @Builder
    public Category(String userId, Integer categoryId, String categoryNm, String useYn) {
        this.userId = userId;
        this.categoryId = categoryId;
        this.categoryNm = categoryNm;
        this.useYn = useYn;
    }

    public void updateCategory(String categoryNm){
        this.categoryNm = categoryNm;
    }

    public void deleteCategory(){
        this.useYn = "Y";
    }
}
