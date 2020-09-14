import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.UserService;


public class MyTest {

    @Test
    public void test1() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 动态代理代理的是接口，不能返回实现类
        UserService userService = context.getBean("userService", UserService.class);

        userService.add();
    }
}
