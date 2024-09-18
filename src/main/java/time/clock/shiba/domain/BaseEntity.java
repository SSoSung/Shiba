package time.clock.shiba.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.Instant;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @CreatedBy
    @Column(name = "CRE_USER", nullable = false, updatable = false, insertable = true)
    private String creUser;

    @CreatedDate
    @Column(name = "CRE_DTM", nullable = false, updatable = false, insertable = true)
    private Instant creDtm;

    @LastModifiedBy
    @Column(name = "UPD_USER", updatable = true, insertable = false)
    private String updUser;

    @LastModifiedDate
    @Column(name = "UPD_DTM", updatable = true, insertable = false)
    private Instant updDtm;

}
