package com.soccerapp.gameday.security;

import com.soccerapp.gameday.enums.Role;
import com.soccerapp.gameday.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
public class SecurityConfig {
    
    @Autowired
    private UserService userService;
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
            //.requestMatchers("/login", "/register").permitAll()
            .requestMatchers("/admin/**").hasRole(Role.ADMIN.name())
            .anyRequest().permitAll()
            //.anyRequest().authenticated()
            )
            .formLogin(form -> form
            .loginPage("/login")
            .defaultSuccessUrl("/admin/tournament", true)
            )
                
            .logout(logout -> logout.permitAll());
        
        return http.build();
    }
    
    @Bean
    public UserDetailsService userDetailsService() {
        return name -> {
            com.soccerapp.gameday.models.User user = userService.getUserByName(name);
            if (user == null) {
                throw new UsernameNotFoundException("User not found!");
            }
            UserBuilder builder = User.withUsername(user.getName());
            builder.password(user.getPassword());
            if (user.getRole() != null) {
                builder.roles(user.getRole().name());
            }
            return builder.build();
        };
    }
}