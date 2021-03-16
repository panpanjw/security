package com.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author panjw
 * @date 2021/3/16 11:38
 */
@RestController
public class LoginController {

    @PostMapping(value = "/login")
    public ResponseEntity<String> Login(@RequestParam(value = "userName") String userName,
                                        @RequestParam(value = "password") String password){

        return ResponseEntity.status(HttpStatus.OK).build();

    }
}
