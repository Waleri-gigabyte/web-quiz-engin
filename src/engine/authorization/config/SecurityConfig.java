package engine.authorization.config;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception{
        http.csrf(AbstractHttpConfigurer::disable);
         http.authorizeHttpRequests(
                 r->r
                         .dispatcherTypeMatchers(DispatcherType.ERROR).permitAll()
                         .requestMatchers("/api/quizzes/**").authenticated()
                         .requestMatchers("/api/register", "/actuator/shutdown").permitAll()
         );
         http.formLogin(Customizer.withDefaults());
         http.httpBasic(Customizer.withDefaults());
         return http.build();
    }
}
