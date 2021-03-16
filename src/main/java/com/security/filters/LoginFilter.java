package com.security.filters;

import com.repository.UserRepository;
import com.entity.UserEntity;
import com.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author panjw
 * @date 2021/3/15 11:43
 */

/**
 * 认证过滤器
 */

public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    @Autowired
    private UserRepository userReposiroty;

    private AuthenticationManager authenticationManager;



    public LoginFilter(AuthenticationManager authenticationManager) {

        this.authenticationManager = authenticationManager;
        this.setPostOnly(false);
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
    }

    /**
     * 获取用户参数并校验
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException{
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        //TODO 用户名密码校验
        
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password, new ArrayList<>()));
    }

    /**
     * 校验成功后颁发Token证书
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {


        String userName = authResult.getName();
        UserEntity  userEntity = userReposiroty.findByUserName(userName);
        //设置超时时间
        long millis = 24 * 60 * 60 * 1000L;
        String token = JwtTokenUtil.createToken(userEntity, millis);
        //将Token信息返回给用户
        response.addHeader("Authorization", "JWT " + token);
    }





}
