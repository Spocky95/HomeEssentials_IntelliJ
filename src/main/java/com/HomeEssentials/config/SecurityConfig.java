package com.HomeEssentials.config;

import com.okta.spring.boot.oauth.Okta;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain configureSecurityFilters(HttpSecurity http) throws Exception {

        configureCors(http);
        configureCsrf(http);
        configureOkta(http);

        http.authorizeHttpRequests(configurer ->
                        configurer
                                .requestMatchers("/api/orders/**")
                                .authenticated().anyRequest().permitAll())
                .oauth2ResourceServer()
                .jwt();


        http.setSharedObject(ContentNegotiationStrategy.class,
                new HeaderContentNegotiationStrategy());

        // force a non-empty response body for 401's to make the response more friendly
        Okta.configureResourceServer401ResponseBody(http);

        // disable CSRF since we are not using Cookies for session tracking

        return http.build();
    }

    private void configureOkta(HttpSecurity http) {
        try {
            Okta.configureResourceServer401ResponseBody(http);
        } catch (Exception e) {
            System.err.println("Wystąpił błąd podczas konfigurowania Okta: " + e.getMessage());
        }
    }

    private void configureCors(HttpSecurity http) {
        try {
            http.cors();
        } catch (Exception e) {
            System.err.println("Wystąpił błąd podczas konfigurowania CORS: " + e.getMessage());
        }
    }

    private void configureCsrf(HttpSecurity http) {
        try {
            http.csrf().disable();
        } catch (Exception e) {
            System.err.println("Wystąpił błąd podczas konfigurowania CSRF: " + e.getMessage());
        }
    }
}