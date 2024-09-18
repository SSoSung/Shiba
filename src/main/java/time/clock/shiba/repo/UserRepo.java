package time.clock.shiba.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import time.clock.shiba.domain.User;

public interface UserRepo extends JpaRepository<User, String> {

    User findByUserId(String userId);
}
