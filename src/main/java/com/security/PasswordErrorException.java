package com.security;

import org.springframework.security.core.AuthenticationException;

/**
 * @author panjw
 * @date 2021/3/16 17:52
 */
public class PasswordErrorException extends AuthenticationException {

    public PasswordErrorException(String msg) {
        super(msg);
    }
}
