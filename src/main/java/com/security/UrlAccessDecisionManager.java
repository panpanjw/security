package com.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import springfox.documentation.schema.property.bean.Accessors;

import javax.persistence.Access;
import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Stream;

/**
 * @author panjw
 * @date 2021/3/18 17:01
 */

/**|
 * 自定义授权策略
 * 当前根据资源权限角色，判断当前用户是否拥有该资源的角色
 */
@Component
public class UrlAccessDecisionManager implements AccessDecisionManager {

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        Iterator<ConfigAttribute> iterator = configAttributes.iterator();
        while (iterator.hasNext()){
            ConfigAttribute configAttribute = iterator.next();
            //获取当前用户所具有的角色
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority grantedAuthority : authorities){
                if (grantedAuthority.equals(configAttribute.getAttribute())){
                    return;
                }
            }
        }

        throw new AccessDeniedException("当前用户权限不足");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
