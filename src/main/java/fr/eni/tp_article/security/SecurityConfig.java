package fr.eni.tp_article.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig implements WebMvcConfigurer {

    private final JwtAuthInterceptor jwtAuthInterceptor;

    public SecurityConfig(JwtAuthInterceptor jwtAuthInterceptor) {
        this.jwtAuthInterceptor = jwtAuthInterceptor;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // Desactiver CSRF
        http.csrf(csrf -> csrf.disable());

        // Desactiver CORS
        http.cors(cors -> cors.disable());

        return http.build();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Allez va s-y rajoute mon interceptor dans ton catalog d'interceptor
        registry.addInterceptor(jwtAuthInterceptor);
    }
}
