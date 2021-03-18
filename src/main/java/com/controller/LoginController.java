package com.controller;

import com.entity.RoleEntity;
import com.entity.UserEntity;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * @author panjw
 * @date 2021/3/16 11:38
 */
@RestController
public class LoginController {
    @Autowired
    private UserService userService;


    @PostMapping(value = "/login")
    public ResponseEntity<String> Login(@RequestParam(value = "userName") String userName,
                                        @RequestParam(value = "password") String password){
        System.out.println("123444");
        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @GetMapping(value = "/loginUser")
    public ResponseEntity<UserEntity> getRoles(){
        UserEntity userEntity = userService.getLoginUser();

        return ResponseEntity.status(HttpStatus.OK).body(userEntity);
    }
}
