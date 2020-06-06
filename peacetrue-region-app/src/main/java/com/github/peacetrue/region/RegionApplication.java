package com.github.peacetrue.region;

import com.github.peacetrue.spring.formatter.date.AutomaticDateFormatter;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.web.ReactivePageableHandlerMethodArgumentResolver;
import org.springframework.data.web.ReactiveSortHandlerMethodArgumentResolver;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * @author xiayx
 */
@SpringBootApplication
public class RegionApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegionApplication.class, args);
    }

    @Bean
    public ReactivePageableHandlerMethodArgumentResolver reactivePageableHandlerMethodArgumentResolver() {
        return new ReactivePageableHandlerMethodArgumentResolver();
    }

    @Bean
    public ReactiveSortHandlerMethodArgumentResolver reactiveSortHandlerMethodArgumentResolver() {
        return new ReactiveSortHandlerMethodArgumentResolver();
    }

    @Bean
    public AutomaticDateFormatter dateFormatter() {
        return new AutomaticDateFormatter();
    }

    @ControllerAdvice
    public static class StringTrimmerControllerAdvice {
        @InitBinder
        public void registerCustomEditors(WebDataBinder binder) {
            binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        }
    }

    @EnableWebFluxSecurity
    public static class SecurityConfig {

        @Bean
        public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
            http
                    .authorizeExchange(exchanges ->
                            exchanges
                                    .pathMatchers("/regions/**").hasAuthority("SCOPE_region")
                                    .anyExchange().authenticated()
                    )
                    .oauth2ResourceServer(oauth2ResourceServer ->
                            oauth2ResourceServer.jwt(withDefaults())
                    );
            return http.build();
        }
    }
}