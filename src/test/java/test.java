import com.SecurityApplication;
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
    @Resource
    private UserService userService;

    @Test
    public void addData(){
        userService.addUser();
    }
}
