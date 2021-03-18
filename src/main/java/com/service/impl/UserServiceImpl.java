package com.service.impl;

import com.entity.RoleEntity;
import com.entity.UserEntity;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.repository.RoleRepository;
import com.repository.UserRepository;
import com.security.usersDetail.JwtUserDetail;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author panjw
 * @date 2021/3/15 22:59
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void addUser() {
        UserEntity userEntity = new UserEntity();
        RoleEntity roleEntity = roleRepository.findFirstByRoleName("admin");
        RoleEntity roleEntity1 = roleRepository.findFirstByRoleName("user");
        List<RoleEntity> roleEntityList = new ArrayList<>();
        //roleEntityList.add(roleEntity);
        roleEntityList.add(roleEntity1);
        userEntity.setUserName("panjw");
        userEntity.setPassword("123456");
        userEntity.setEnable(true);
        userEntity.setRoles(roleEntityList);
        userRepository.save(userEntity);
    }

    @Override
    public void addRole() {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRoleName("user");
        roleRepository.save(roleEntity);
    }

    @Override
    public UserEntity findByUserName(String userName) {
        UserEntity userEntity = userRepository.findByUserName(userName);
        return userEntity;
    }

    @Override
    public UserEntity getLoginUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JwtUserDetail jwtUserDetail = null;

        if (Objects.nonNull(authentication)){
            Object principal = authentication.getPrincipal();
            if (Objects.nonNull(principal)) {
                jwtUserDetail = (JwtUserDetail) principal;
            }
        }
        String userName = jwtUserDetail.getUserName();
        UserEntity userEntity = findByUserName(userName);
        return userEntity;
    }

}
