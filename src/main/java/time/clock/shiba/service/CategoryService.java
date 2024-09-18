package time.clock.shiba.service;

import org.springframework.http.ResponseEntity;
import time.clock.shiba.global.common.ShibaApiResponse;
import time.clock.shiba.record.request.CreateCategory;
import time.clock.shiba.record.request.DeleteCategory;
import time.clock.shiba.record.request.UpdateCategory;

import java.util.List;

public interface CategoryService {

    void createCategory(List<CreateCategory> request);

    ResponseEntity<ShibaApiResponse> readCategory();

    void updateCategory(List<UpdateCategory> request);

    void deleteCategory(List<DeleteCategory> request);

}
