package com.nsiagoassur.api.security;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;




@Configuration
public class SecurityConfig {
    private final JwtUtil jwtUtil;
    private final JwtFilter jwtFilter; 

    public SecurityConfig(JwtUtil jwtUtil, JwtFilter jwtFilter) {
        this.jwtUtil = jwtUtil;
        this.jwtFilter = jwtFilter;
    }



 @Bean
    public FilterRegistrationBean<JwtFilter> jwtFilterRegistration(JwtFilter jwtFilter) {
        FilterRegistrationBean<JwtFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(jwtFilter);
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE); // ou un autre ordre souhaité
        registrationBean.addUrlPatterns("/api/v1/*"); // ou spécifiez les URL à filtrer
        return registrationBean;
    }
    

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtFilter jwtFilter) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
           
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/v1/*").permitAll()
                .requestMatchers("/auth/*").permitAll()
                .anyRequest().authenticated()
            )
            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                // Utilisation d'une méthode de hachage sécurisée recommandée
                return rawPassword.toString(); // ⚠️ Ceci n'est pas sécurisé ! Utilisez BCrypt à la place.
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return rawPassword.toString().equals(encodedPassword);
            }
        };
    }

}
