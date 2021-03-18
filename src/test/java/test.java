import com.SecurityApplication;
import com.entity.PermissionEntity;
import com.repository.PermissionReposiroty;
import com.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author panjw
 * @date 2021/3/15 23:04
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SecurityApplication.class)
@ActiveProfiles("dev")
public class test {
    @Autowired
    private UserService userService;
    @Autowired
    private PermissionReposiroty permissionReposiroty;

    @Test
    public void addData(){
        userService.addUser();
    }

    @Test
    public void addPermission(){
        PermissionEntity permissionEntity = new PermissionEntity();
        permissionEntity.setPermissionName("案源权限");
        permissionEntity.setUrl("/caseSource");
        permissionReposiroty.save(permissionEntity);
    }
}
