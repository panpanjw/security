package com.security;

import com.entity.UserEntity;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @author panjw
 * @date 2021/3/16 17:42
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取用户登录时输入的用户名
        String username = authentication.getName();
        UserEntity userEntity = userService.findByUserName(username);
        if (userEntity == null){
            throw new UsernameNotFoundException("用户不存在");
        }

        if (userEntity.getEnable() == false){
            throw new AccountExpiredException("用户被禁用");
        }

        if (!userEntity.getUserName().equals(authentication.getCredentials().toString()) ){
            throw new PasswordErrorException("密码错误");
        }

        return new UsernamePasswordAuthenticationToken(authentication, authentication.getCredentials(), new ArrayList<>());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
