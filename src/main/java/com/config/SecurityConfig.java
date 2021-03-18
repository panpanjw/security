package com.config;

import com.security.*;
import com.security.filters.JwtAuthenticationFilter;
import com.security.filters.LoginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import java.util.ArrayList;

/**
 * @author panjw
 * @date 2021/3/15 10:24
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private DefaultPasswordEncoder defaultPasswordEncoder;

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Autowired
    private UrlFilterInvocationSecurityMetadataSource urlFilterInvocationSecurityMetadataSource;
    @Autowired UrlAccessDecisionManager urlAccessDecisionManager;



    @Bean
    public LoginFilter loginFilter(AuthenticationManager authenticationManager){
        return new LoginFilter(authenticationManager);
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(AuthenticationManager authenticationManager){
        return new JwtAuthenticationFilter(authenticationManager);
    }



    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .exceptionHandling()
                .authenticationEntryPoint(new UnauthorizedEntryPoint())
                .and()
                .authorizeRequests()
                    .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                        @Override
                        public <UrlFilterSecurityInterceptor extends FilterSecurityInterceptor> UrlFilterSecurityInterceptor postProcess(UrlFilterSecurityInterceptor o) {
                            o.setSecurityMetadataSource(urlFilterInvocationSecurityMetadataSource);
                            o.setAccessDecisionManager(urlAccessDecisionManager);
                            return o;
                        }
                    })
                .antMatchers("/login").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable()
                .logout().logoutUrl("/logout")
                .and()
                .addFilter(loginFilter(authenticationManager()))
                .addFilter(jwtAuthenticationFilter(authenticationManager()));
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthenticationProvider)
                .userDetailsService(userDetailsService)
                .passwordEncoder(defaultPasswordEncoder);
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

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }


}
