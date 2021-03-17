package com.security.filters;

import com.security.JwtTokenUtil;
import com.security.usersDetail.JwtUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author panjw
 * @date 2021/3/15 16:44
 */

/**
 * 授权过滤器
 */

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    @Autowired
    private UserDetailsService userDetailsService;


    public JwtAuthenticationFilter(AuthenticationManager authManager) {
        super(authManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        String authHeader = request.getHeader("Authorization");
        String tokenHead = "JWT ";

        if (StringUtils.isEmpty(authHeader) && authHeader.startsWith(tokenHead)) {
            new Exception("非法Token，或Token不存在");
        }

        String token = authHeader.substring(tokenHead.length());
        String userName = JwtTokenUtil.getUserNameFromToken(token);
        if (!StringUtils.isEmpty(userName) && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);

            if (JwtTokenUtil.checkToken(token, userDetails)) {
                //给使用该JWT令牌的用户进行授权
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
                        null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                //设置用户身份授权
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        chain.doFilter(request, response);
    }
}
