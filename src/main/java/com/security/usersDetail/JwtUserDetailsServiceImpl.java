package com.security.usersDetail;

import com.entity.UserEntity;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author panjw
 * @date 2021/3/15 23:44
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserEntity userEntity = userService.findByUserName(userName);
        if (userEntity == null){
            throw new UsernameNotFoundException("用户不存在");
        }

        //将数据库的roles解析为UserDetails的权限集
        StringBuilder roles = new StringBuilder();
        userEntity.getRoles().stream().forEach( (roleEntity)-> {
            roles.append(roleEntity.getRoleName()).append(",");
        });


        JwtUserDetail jwtUserDetail = new JwtUserDetail(userEntity.getUserName(), userEntity.getPassword(),
                AuthorityUtils.commaSeparatedStringToAuthorityList(roles.toString()));
        return jwtUserDetail;
    }
}
