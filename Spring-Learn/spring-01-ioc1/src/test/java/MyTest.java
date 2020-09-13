import dao.UserDaoMysqlImpl;
import dao.UserDaoSqlserverImpl;
import javafx.application.Application;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.UserService;
import service.UserServiceImpl;

public class MyTest {

    public static void main(String[] args) {

        // 用户实际调用的是业务层，Dao层不需要接触
//        UserService userService = new UserServiceImpl();
//        ((UserServiceImpl) userService).setUserDao(new UserDaoSqlserverImpl());
//        userService.getUser();

        // Spring写法，不需要new对象了
        // 获取 ApplicationContext，拿到 Spring 的容器
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        // 需要什么，get什么
        UserServiceImpl userServiceImpl = (UserServiceImpl) context.getBean("UserServiceImpl");
        userServiceImpl.getUser();


    }
}
