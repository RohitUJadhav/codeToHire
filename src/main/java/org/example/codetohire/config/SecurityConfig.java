package org.example.codetohire.config;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, HttpSession httpSession, HttpServletResponse httpServletResponse) throws Exception {
        http.csrf(csrf -> csrf.disable( ))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/signUp").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/user/**").hasRole("STUDENT")
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginProcessingUrl("/login")
                        .successHandler((request, response, authentication) -> {
                            response.setStatus(httpServletResponse.SC_OK);
                        }))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));
        return http.build();

    }
}
