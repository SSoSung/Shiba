package time.clock.shiba.global.config;

import com.p6spy.engine.spy.P6SpyOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import time.clock.shiba.global.common.P6spyPrettySqlFormatter;


@Configuration
public class P6spy {

    @PostConstruct
    public void setLogMessageFormat(){
        P6SpyOptions.getActiveInstance().setLogMessageFormat(P6spyPrettySqlFormatter.class.getName());
    }
}
