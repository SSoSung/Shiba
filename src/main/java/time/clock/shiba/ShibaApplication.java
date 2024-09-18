package time.clock.shiba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ShibaApplication {

    private static PasswordEncoder encoder;

    public static void main(String[] args) {
        SpringApplication.run(ShibaApplication.class, args);
        System.out.println("Developer SSoSung");
    }

}
