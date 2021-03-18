package com.security.usersDetail;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @author panjw
 * @date 2021/3/15 23:36
 */
public class JwtUserDetail implements UserDetails {
    private String userName;

    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    JwtUserDetail(String username, String password, Collection<? extends GrantedAuthority> authorities){
        this.userName = username;
        this.password = password;
        this.authorities = authorities;
    }

    public void setAuthorities(List<GrantedAuthority> authorities){
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    /**
     * 账户是否未过期，国企无法验证
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    /**
     * 指定用户是否解锁，锁定的用户无法进行身份验证
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    /**
     * 指示是否已经过期的用户的凭据（密码），过期的凭据防止认真
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    /**
     * 是否可用，禁用的用户无法进行身份验证
     * @return
     */
    @Override
    public boolean isEnabled() {
        return false;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
