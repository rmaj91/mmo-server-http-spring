package com.maiu.mmoserverhttpspring.config;

import jakarta.servlet.DispatcherType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.maiu.mmoserverhttpspring.config.Config.HTTP_ACCOUNTS_PREFIX;
import static com.maiu.mmoserverhttpspring.config.Config.HTTP_AUTH_PREFIX;

@Configuration
public class SecurityConfig {

    @Autowired
    private AuthorizationBearerTokenFilter authorizationBearerTokenFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authorize) -> authorize
                .dispatcherTypeMatchers(DispatcherType.ERROR).permitAll()
                .requestMatchers("/h2-console/**").permitAll()
                .requestMatchers(HttpMethod.POST, HTTP_ACCOUNTS_PREFIX).permitAll()
                .requestMatchers(HttpMethod.POST, HTTP_AUTH_PREFIX + "/**").permitAll()
                .anyRequest().authenticated())
                .headers(customizer -> customizer.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));

        http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .formLogin(AbstractHttpConfigurer::disable);

        http.addFilterAt(authorizationBearerTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
