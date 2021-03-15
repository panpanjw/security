package com.security;

import com.entity.UserEntity;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author panjw
 * @date 2021/3/15 23:44
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserEntity userEntity = userService.findByUserName(userName);
        if (userEntity == null){
            throw new UsernameNotFoundException("用户不存在");
        }
        StringBuilder roles = new StringBuilder();
        userEntity.getRoleEntityList().stream().forEach( (roleEntity)-> {
            roles.append(roleEntity.getRoleName()).append(",");
        });

        UserDetail userDetail = new UserDetail();
        userDetail.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(roles.toString()));

        //将数据库的roles解析为UserDetails的权限集

        return null;
    }
}
