package time.clock.shiba.global.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import time.clock.shiba.global.filter.CustomAuthenticationFilter;
import time.clock.shiba.global.filter.CustomAuthorizationFilter;
import time.clock.shiba.repo.UserRepo;

import java.util.Collections;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class Security {

    private final UserRepo userRepo;

    private final AuthenticationConfiguration authenticationConfiguration;

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManager(authenticationConfiguration));
        customAuthenticationFilter.setFilterProcessesUrl("/api/login");
        http
                .csrf((csrfConfig) ->
                        csrfConfig.disable())
                .sessionManagement(session->
                        session.sessionCreationPolicy(STATELESS)
                )
                .authorizeHttpRequests((authorizeRequests) ->
                        authorizeRequests.requestMatchers("/api/login", "/api/user/token/refresh").permitAll()
                                //.requestMatchers("/api/swagger-config", "/api/shiba")
                               //.requestMatchers("/js/**", "/css/**", "/images/**", "/fonts/**").permitAll()
                               .anyRequest().permitAll()
                )
                .addFilter(customAuthenticationFilter)
                .cors(corsConfigurer -> corsConfigurer.configurationSource(corsConfigurationSource()))
                .headers((headerConfig) ->
                        headerConfig.frameOptions(frameOptionsConfig ->
                                frameOptionsConfig.disable()
                        )
                )
                .addFilterBefore(new CustomAuthorizationFilter(userRepo), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    CorsConfigurationSource corsConfigurationSource() {
        return request -> {
            CorsConfiguration config = new CorsConfiguration();
            config.setAllowedHeaders(Collections.singletonList("*"));
            config.setAllowedMethods(Collections.singletonList("*"));
            config.setAllowedOriginPatterns(Collections.singletonList("*")); // ⭐️ 허용할 origin
            config.setAllowCredentials(true);
            return config;
        };
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
