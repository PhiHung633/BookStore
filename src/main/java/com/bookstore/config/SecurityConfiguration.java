package com.bookstore.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.bookstore.service.TaiKhoanService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration{
	
	@Autowired
	private TaiKhoanService taikhoanService;
	
	public SecurityConfiguration(TaiKhoanService taiKhoanService)
	{
		this.taikhoanService=taiKhoanService;
	}
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(taikhoanService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
    	auth.authenticationProvider(authenticationProvider());
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable())
            .authorizeRequests(authorize -> authorize
                .requestMatchers("/signup/**").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login").loginProcessingUrl("/perform_login")
                .usernameParameter("username").passwordParameter("password")
                .defaultSuccessUrl("/home", true)
                .permitAll()
            )
            .logout(logout->logout
            		.invalidateHttpSession(true)
            		.clearAuthentication(true)
            		.logoutUrl("/logout")
            		.logoutSuccessUrl("/login?logout")
            		.permitAll()
            		)
            .build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers("/css/**", "/images/**", "/js/**");
    }
}
