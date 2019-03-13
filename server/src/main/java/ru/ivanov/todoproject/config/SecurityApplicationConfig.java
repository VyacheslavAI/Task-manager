package ru.ivanov.todoproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityApplicationConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(final HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.authorizeRequests().anyRequest().hasAnyRole("ADMIN", "USER")
//                .and()
//                .authorizeRequests().antMatchers("/disp/login**").permitAll()
//                .and()
//                .formLogin().loginPage("/disp/login").loginProcessingUrl("/loginAction").permitAll()
//                .and()
//                .logout().logoutSuccessUrl("/disp/login").permitAll()
//                .and()
//                .csrf().disable();
    }
}