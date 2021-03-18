package com.service;

import com.entity.UserEntity;

/**
 * @author panjw
 * @date 2021/3/15 22:59
 */
public interface UserService {
    public void addUser();

    public void addRole();

    public UserEntity findByUserName(String userName);

    public UserEntity getLoginUser();
}
