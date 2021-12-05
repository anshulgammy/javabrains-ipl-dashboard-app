package com.zefrocan.ipldashboard.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${app.user.name}")
    private String userName;
    @Value("${app.user.password}")
    private String userPassword;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // allowing '/h2-console' to get accessed without spring security credentials.
        httpSecurity.authorizeRequests()
                .antMatchers("/h2-console/**")
                .permitAll();
        // ignoring only h2-console csrf
        httpSecurity.csrf().ignoringAntMatchers("/h2-console/**");
        // this will allow frames with same origin which is much more safe
        httpSecurity.headers().frameOptions().sameOrigin();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {

    }

}