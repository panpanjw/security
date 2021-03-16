package com.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author panjw
 * @date 2021/3/16 12:03
 */
@Component
public class DefaultPasswordEncoder extends BCryptPasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if (rawPassword == null) {
            throw new IllegalArgumentException("rawPassword cannot be null");
        }
        if (encodedPassword == null || encodedPassword.length() == 0) {

            throw new IllegalArgumentException("encodedPassword is null");
        }
        return encodedPassword.equals(rawPassword);
    }

}
