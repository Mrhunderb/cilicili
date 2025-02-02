package com.chameleon.cilicili.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // 禁用 CSRF 防护（根据需求）
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/api/**").permitAll() // 开放 /api/** 接口
                        .anyRequest().authenticated())
                .httpBasic(withDefaults()); // 启用 Basic 认证（可选，根据需求）

        return http.build();
    }
    
}
