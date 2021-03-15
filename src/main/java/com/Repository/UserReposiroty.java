package com.Repository;

import com.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author panjw
 * @date 2021/3/15 18:12
 */
@Repository
public interface UserReposiroty extends JpaRepository<UserEntity, Long> {

    UserEntity findByUserName(String userName);

}
