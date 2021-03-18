package com.repository;

import com.entity.PermissionEntity;
import com.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author panjw
 * @date 2021/3/18 16:28
 */
@Repository
public interface PermissionReposiroty extends JpaRepository<PermissionEntity, String> {

}
