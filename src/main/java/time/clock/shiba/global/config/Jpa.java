package time.clock.shiba.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;
import time.clock.shiba.domain.User;

import java.util.Optional;

@Configuration
@EnableJpaAuditing // BaseTimeEntity Auto 활성화
public class Jpa implements AuditorAware<String> {

    // createBy, LastModified
    @Override
    public Optional<String> getCurrentAuditor() {
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String userId = "";

        if(user != null){
            userId = user.getUserId();
        }
        return Optional.of(userId);
    }
}
