package com.dse.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class DseWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
                http
                    .formLogin().loginPage("/require/form")
                        .and()
                        .authorizeRequests()
                        .antMatchers("/require/form","/css/**", "/js/**","/fonts/**").permitAll()
                        .anyRequest().authenticated()
                        .and()
                        .csrf().disable();
    }
}
