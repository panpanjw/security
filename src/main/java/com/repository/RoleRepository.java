package com.repository;

import com.entity.PermissionEntity;
import com.entity.RoleEntity;
import com.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author panjw
 * @date 2021/3/15 23:01
 */
@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, String> {

    RoleEntity findFirstByRoleName(String roleName);

    List<RoleEntity> findAllByPermissionEntityListContaining(PermissionEntity permissionEntity);
}
