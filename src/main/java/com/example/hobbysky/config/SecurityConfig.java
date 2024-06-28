package com.example.hobbysky.config;

import com.example.hobbysky.security.CustomAuthenticationFailureHandler;
import com.example.hobbysky.security.CustomAuthenticationSuccessHandler;
import com.example.hobbysky.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.UUID;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserService userService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(requests -> requests
//                        .requestMatchers("/admin/**").hasRole("director")
                        .requestMatchers("/h2-console/**", "/Register", "/styles/**", "/js/**", "/login").permitAll()
                        .anyRequest().authenticated()
                )
                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF protection for H2 console
                .headers(headers -> headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin) // Allow frames from the same origin
                )
//                .formLogin(
//                        form -> form.loginPage("/Register")
//                                .defaultSuccessUrl("/HomePage", true)
//                                .successHandler(new CustomAuthenticationSuccessHandler())
//                                .failureHandler(new CustomAuthenticationFailureHandler())
//                                .permitAll()
//                )
                .formLogin(Customizer.withDefaults())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/Register?logout")
                        .permitAll()
                )
                .userDetailsService(userService);

        return http.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        String username = "user@test.com";
//        String password = UUID.randomUUID().toString();
//        System.out.println("Generated User Password: " + password);
//
//        return new InMemoryUserDetailsManager(
//                User.withUsername(username)
//                        .password(passwordEncoder().encode(password))
//                        .roles("USER")
//                        .build()
//        );
//    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}