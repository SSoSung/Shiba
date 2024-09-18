package time.clock.shiba.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import time.clock.shiba.domain.UseTime;
import time.clock.shiba.domain.id.UseTimeId;

import java.util.Optional;

public interface UseTimeRepo extends JpaRepository<UseTime, UseTimeId> {

    @Query(value = "select max(useTime.ymdSeq) " +
            "from UseTime useTime  " +
            "where useTime.userId = :userId " +
            "and useTime.ymd = :ymd ")
    Integer findByMaxYmdSeq(@Param("userId") String userId, @Param("ymd") String ymd);

    Optional<UseTime> findByUserIdAndCategoryIdAndYmdAndYmdSeq(String userId, Integer categoryId, String ymd, Integer ymdSeq);

}
