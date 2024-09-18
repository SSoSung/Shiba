package time.clock.shiba.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import time.clock.shiba.domain.id.UseTimeId;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(UseTimeId.class)
@Table(name = "USE_TIME")
@Entity
public class UseTime extends BaseEntity{

    @Id
    @Column(name = "USER_ID", length = 100)
    private String userId;

    @Id
    @Column(name = "CATEGORY_ID")
    private Integer categoryId;

    @Id
    @Column(name = "YMD", length = 10)
    private String ymd;

    @Id
    @Column(name = "YMD_SEQ")
    private Integer ymdSeq;

    @Column(name = "SECOND")
    private Long second;

    @Column(name = "DEL_YN", length = 1)
    private String delYn;

    @Builder
    public UseTime(String userId, Integer categoryId, String ymd, Integer ymdSeq, Long second, String delYn) {
        this.userId = userId;
        this.categoryId = categoryId;
        this.ymd = ymd;
        this.ymdSeq = ymdSeq;
        this.second = second;
        this.delYn = delYn;
    }

    public void deleteUseTime(){
        this.delYn = "Y";
    }
}
