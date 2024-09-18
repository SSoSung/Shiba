package time.clock.shiba.domain.id;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UseTimeId implements Serializable {

    @EqualsAndHashCode.Include
    private String userId;

    @EqualsAndHashCode.Include
    private Integer categoryId;

    @EqualsAndHashCode.Include
    private String ymd;

    @EqualsAndHashCode.Include
    private Integer ymdSeq;
}