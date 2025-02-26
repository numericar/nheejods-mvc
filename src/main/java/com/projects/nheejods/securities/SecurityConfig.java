package com.projects.nheejods.securities;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.projects.nheejods.repositories.UserRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final String[] resourceUrl = { "/css/**", "/js/**", "/images/**" };
    private final String[] publicUrl = {};
    private final String[] privateUrl = { "/boxs/**" };

    private final UserRepository userRepository;

    public SecurityConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        String[] combindPublicUrl = Stream.concat(Stream.of(this.resourceUrl), Stream.of(this.publicUrl))
                .toArray(String[]::new);

        http.authorizeHttpRequests(config -> config
                .requestMatchers(combindPublicUrl).permitAll()
                .requestMatchers(privateUrl).authenticated());

        http.formLogin(form -> {
            form.loginPage("/auths/login");
            form.loginProcessingUrl("/auths/login");
            form.failureUrl("/auths/login?error");
            form.defaultSuccessUrl("/boxs");
            form.usernameParameter("email");
            form.passwordParameter("password");
            form.permitAll();
        }).logout(logout -> logout.logoutUrl("/auths/logout").permitAll());

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService userDetailsService() {
        return new NheeJodsUserDetailService(this.userRepository);
    }

    @Bean
    AuthenticationManager authenticationManager() {
        return new ProviderManager(List.of(this.authenticationProvider()));
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setUserDetailsService(this.userDetailsService());
        provider.setPasswordEncoder(this.passwordEncoder());

        return provider;
    }

}
