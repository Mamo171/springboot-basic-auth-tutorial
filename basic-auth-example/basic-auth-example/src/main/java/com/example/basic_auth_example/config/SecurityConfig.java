package com.example.basic_auth_example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // Markiert die Klasse als Konfigurationsklasse für Spring Security
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Deaktiviert CSRF-Schutz (nicht empfohlen für Produktion ohne spezielle Gründe)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated() // Erfordert Authentifizierung für alle Anfragen
                )
                .httpBasic(); // Aktiviert Basic Authentication (Benutzername und Passwort in Header)
        return http.build(); // Erstellt die SecurityFilterChain und gibt sie zurück
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // BCrypt zur sicheren Passwortverschlüsselung
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        // Liefert den Standard-AuthenticationManager von Spring zurück
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // Erstellt einen Benutzer mit Benutzername "user", Passwort "password" und Rolle "USER"
        UserDetails user = User.builder()
                .username("user") // Benutzername
                .password(passwordEncoder().encode("password")) // Passwort verschlüsselt mit BCrypt
                .roles("USER") // Zuweisung der Rolle "USER"
                .build();

        // Speichert den Benutzer in einem In-Memory-User-Store
        return new InMemoryUserDetailsManager(user);
    }
}
