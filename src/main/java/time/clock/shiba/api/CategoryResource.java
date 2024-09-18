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
import time.clock.shiba.record.request.CreateCategory;
import time.clock.shiba.record.request.DeleteCategory;
import time.clock.shiba.record.request.UpdateCategory;
import time.clock.shiba.service.CategoryService;

import java.util.List;

@Tag(name = "카테고리 컨트롤러", description = "카테고리 컨트롤을 한다")
@RequiredArgsConstructor
@RequestMapping("/api/category")
@RestController
public class CategoryResource {

    private final CategoryService categoryService;

    @Operation(summary = "카테고리 저장 api", description = "유저별 카테고리 저장을 진행한다.")
    @PostMapping
    public ResponseEntity<ShibaApiResponse> createCategory(@Valid @RequestBody List<CreateCategory> request){
        categoryService.createCategory(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ShibaApiResponse(ShibaStatus.CREATED, "카테고리저장"));
    }

    @Operation(summary = "카테고리 조회 api", description = "유저별 카테고리정보를 조회한다.")
    @GetMapping
    public ResponseEntity<ShibaApiResponse> readCategory(){
        return categoryService.readCategory();
    }

    @Operation(summary = "카테고리명 변경 api", description = "유저별 카테고리명을 변경 진행한다.")
    @PatchMapping
    public ResponseEntity<ShibaApiResponse> updateCategory(@Valid @RequestBody List<UpdateCategory> request){
        categoryService.updateCategory(request);
        return ResponseEntity.status(HttpStatus.OK).body(new ShibaApiResponse(ShibaStatus.SUCCESS, "카테고리명변경"));
    }

    @Operation(summary = "카테고리 삭제 api", description = "유저별 카테고리 삭제(소프트)을 진행한다.")
    @DeleteMapping
    public ResponseEntity<ShibaApiResponse> deleteCategory(@Valid @RequestBody List<DeleteCategory> request){
        categoryService.deleteCategory(request);
        return ResponseEntity.status(HttpStatus.OK).body(new ShibaApiResponse(ShibaStatus.SUCCESS, "카테고리삭제"));
    }

}
