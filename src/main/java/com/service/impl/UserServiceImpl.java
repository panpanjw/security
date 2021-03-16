package com.service.impl;

import com.entity.RoleEntity;
import com.entity.UserEntity;
import com.repository.RoleRepository;
import com.repository.UserRepository;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        userEntity.setRoleEntityList(roleEntityList);
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

}
