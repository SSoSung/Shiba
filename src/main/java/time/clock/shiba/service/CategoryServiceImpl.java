package time.clock.shiba.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import time.clock.shiba.domain.Category;
import time.clock.shiba.domain.User;
import time.clock.shiba.domain.id.CategoryId;
import time.clock.shiba.global.common.ShibaApiResponse;
import time.clock.shiba.global.common.ShibaStatus;
import time.clock.shiba.global.exception.BadRequestException;
import time.clock.shiba.record.request.CreateCategory;
import time.clock.shiba.record.request.DeleteCategory;
import time.clock.shiba.record.request.UpdateCategory;
import time.clock.shiba.record.response.ReadCategoryResp;
import time.clock.shiba.repo.CategoryRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;

    @Override
    @Transactional
    public void createCategory(List<CreateCategory> request) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String userId = user.getUserId();

        Integer maxCategoryId = categoryRepo.findByMaxCategoryId(userId);
        log.info("사용자의 마지막 저장 카테고리 아이디 : {}", maxCategoryId);

        int i = maxCategoryId != null ? maxCategoryId : 0;

        List<Category> categoryList = new ArrayList<>();

        for (CreateCategory data : request){
            categoryList.add(data.toEntity(userId, i + 1, data.categoryNm()));
            i++;
        }

        log.info("사용자 카테고리 저장목록 : {}", categoryList);

        // 벌크 insert
        categoryRepo.saveAll(categoryList);

    }

    @Override
    public ResponseEntity<ShibaApiResponse> readCategory() {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = user.getUserId();

        List<Category> categoryList = categoryRepo.findByUserIdAndUseYn(userId, "N");

        List<ReadCategoryResp> response = new ArrayList<>();
        if(!categoryList.isEmpty()){
            response = categoryList.stream().map(ReadCategoryResp::new).collect(Collectors.toList());
        }

        log.info("사용자 조회목록 : {}", response);

        ShibaApiResponse apiResponse = new ShibaApiResponse(ShibaStatus.OK, "사용자별 카테고리 조회 완료", response);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


    @Override
    @Transactional
    public void updateCategory(List<UpdateCategory> request) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = user.getUserId();

        for(UpdateCategory data : request){
            Category category = categoryRepo.findById(new CategoryId(userId, data.categoryId())).orElseThrow(()-> new BadRequestException("없는 카테고리 아이디 입니다."));
            category.updateCategory(data.categoryNm());
        }

    }

    @Override
    @Transactional
    public void deleteCategory(List<DeleteCategory> request) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = user.getUserId();

        for(DeleteCategory data : request){
            Category category = categoryRepo.findById(new CategoryId(userId, data.categoryId())).orElseThrow(()-> new BadRequestException("없는 카테고리 아이디 입니다."));
            // 소프트 삭제
            category.deleteCategory();
        }

    }
}
