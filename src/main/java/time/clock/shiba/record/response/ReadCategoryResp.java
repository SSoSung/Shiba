package time.clock.shiba.record.response;


import time.clock.shiba.domain.Category;

public class ReadCategoryResp{

    private Integer categoryId;
    private String categoryNm;

    public ReadCategoryResp(Category category) {
        this.categoryId = category.getCategoryId();
        this.categoryNm = category.getCategoryNm();
    }

}
