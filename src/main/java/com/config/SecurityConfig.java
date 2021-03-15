package com.config;

import com.security.JwtAuthenticationFilter;
import com.security.LoginFilter;
import com.security.UnauthorizedEntryPoint;
import com.security.UserDetailServiceImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


import java.util.ArrayList;

/**
 * @author panjw
 * @date 2021/3/15 10:24
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.exceptionHandling().authenticationEntryPoint(new UnauthorizedEntryPoint())
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable()
                .logout().logoutUrl("/logout")
                .and()
                .addFilter(new LoginFilter(authenticationManager()))
                .addFilter(new JwtAuthenticationFilter(authenticationManager()));
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.userDetailsService(userDetailsService()).passwordEncoder()
    }

    /**
     * 配置不需要拦截的请求
     */
    @Override
    public void configure(WebSecurity web) throws Exception {

        ArrayList arrayList = new ArrayList();
        arrayList.add("/*");
        web.ignoring().antMatchers(String.valueOf(arrayList));

    }


}
