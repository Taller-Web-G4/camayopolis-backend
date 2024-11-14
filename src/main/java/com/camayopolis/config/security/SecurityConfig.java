package com.camayopolis.config.security;

import com.camayopolis.service.implementation.UserDetailServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtUtil jwtUtil;

    public SecurityConfig(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    private static final String ADMIN_ROLE = "ADMIN";
    private static final String USER_ROLE = "USER";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(http -> {
                    // dndpoints públicos
                    http.requestMatchers(HttpMethod.POST, "/auth/**").permitAll();
                    // dndpoints seguros
                    http.requestMatchers(HttpMethod.GET, "/method/get").hasAnyRole(ADMIN_ROLE, USER_ROLE);
                    http.requestMatchers(HttpMethod.POST, "/method/post").hasRole(ADMIN_ROLE);
                    http.requestMatchers(HttpMethod.PUT, "/method/put").hasAnyRole(ADMIN_ROLE);
                    http.requestMatchers(HttpMethod.DELETE, "/method/delete").hasRole(ADMIN_ROLE);
                    http.requestMatchers(HttpMethod.POST, "/movie/create").hasRole(ADMIN_ROLE);
                    http.requestMatchers(HttpMethod.PUT, "/movie/update/**").hasRole(ADMIN_ROLE);
                    http.requestMatchers(HttpMethod.DELETE, "/movie/delete/**").hasRole(ADMIN_ROLE);
                    http.requestMatchers(HttpMethod.POST, "/category/create").hasRole(ADMIN_ROLE);
                    http.requestMatchers(HttpMethod.PUT, "/category/update/**").hasRole(ADMIN_ROLE);
                    http.requestMatchers(HttpMethod.DELETE, "/category/delete/**").hasRole(ADMIN_ROLE);
                    http.requestMatchers(HttpMethod.POST, "/combo/create").hasRole(ADMIN_ROLE);
                    http.requestMatchers(HttpMethod.PUT, "/combo/update/**").hasRole(ADMIN_ROLE);
                    http.requestMatchers(HttpMethod.DELETE, "/combo/delete/**").hasRole(ADMIN_ROLE);
                    http.requestMatchers(HttpMethod.POST, "/comboItem/create").hasRole(ADMIN_ROLE);
                    http.requestMatchers(HttpMethod.PUT, "/comboItem/update/**").hasRole(ADMIN_ROLE);
                    http.requestMatchers(HttpMethod.DELETE, "/comboItem/delete/**").hasRole(ADMIN_ROLE);
                    // resto de endpoints no especificados
                    http.anyRequest().permitAll();
                })
                .addFilterBefore(new JwtTokenValidator(jwtUtil), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        configuration.setExposedHeaders(List.of("Authorization"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailServiceImpl userDetailService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailService);
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
