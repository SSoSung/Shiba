package time.clock.shiba.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import time.clock.shiba.domain.Category;
import time.clock.shiba.domain.id.CategoryId;

import java.util.List;

public interface CategoryRepo extends JpaRepository<Category, CategoryId> {

    @Query(value = "select max(category.categoryId) " +
            "from Category category  " +
            "where category.userId = :userId " )
    Integer findByMaxCategoryId(@Param("userId") String userId);

    List<Category> findByUserId(String userId);

}
