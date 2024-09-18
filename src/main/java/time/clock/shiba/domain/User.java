package time.clock.shiba.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;



@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "USER")
@Entity
public class User {

    @Id
    @Column(name = "USER_ID", length = 100)
    private String userId;

    @Column(name = "USER_PW", length = 255)
    private String userPw;

    @Column(name = "USER_NM", length = 100)
    private String userNm;

    @Column(name = "USER_EMAIL", length = 50)
    private String userEmail;

    @Column(name = "USER_PHONE", length = 50)
    private String userPhone;

    @Column(name = "USE_YN", length = 1)
    private String useYn;

    @Builder
    public User(String userId, String userPw, String userNm, String userEmail, String userPhone, String useYn) {
        this.userId = userId;
        this.userPw = userPw;
        this.userNm = userNm;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.useYn = useYn;
    }

    public void deleteUser(){
        this.useYn = "Y";
    }
}
