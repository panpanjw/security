package com.security;

import com.entity.PermissionEntity;
import com.entity.RoleEntity;
import com.repository.PermissionReposiroty;
import com.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import sun.security.provider.PolicyParser;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

/**
 * @author panjw
 * @date 2021/3/18 15:58
 */

/**
 * 从数据库中加载权限资源
 */
@Component
public class UrlFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private PermissionReposiroty permissionReposiroty;
    @Autowired
    private RoleRepository  roleRepository;

    AntPathMatcher antPathMatcher =  new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        //获取请求地址
        String request = ((FilterInvocation) object).getRequest().getRequestURI();

        //获取所有权限
        List<PermissionEntity> permissionEntityList = permissionReposiroty.findAll();

        StringBuilder roles = new StringBuilder();
        //查找该url的请求权限
        for (PermissionEntity permissionEntity : permissionEntityList){
            //过滤非资源权限
            if (StringUtils.isEmpty(permissionEntity.getUrl())){
                continue;
            }
            //antPathMatcher = new AntPathMatcher(permissionEntity.getUrl());
            if (antPathMatcher.match(permissionEntity.getUrl(), String.valueOf(request))){
                //获取该url权限的所有角色
                //获取所有角色
                List<RoleEntity>  roleEntityList = roleRepository.findAllByPermissionEntityListContaining(permissionEntity);
                roleEntityList.stream().forEach((roleEntity) -> {
                    roles.append(roleEntity.getRoleName()).append(",");
                });

                return SecurityConfig.createList(roles.toString().split(","));

            }
        }

        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }
}
