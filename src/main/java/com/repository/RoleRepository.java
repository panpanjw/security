package com.repository;

import com.entity.RoleEntity;
import com.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author panjw
 * @date 2021/3/15 23:01
 */
@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, String> {

    RoleEntity findFirstByRoleName(String roleName);
}
