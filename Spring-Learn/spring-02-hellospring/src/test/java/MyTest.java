import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.Hello;

public class MyTest {
    public static void main(String[] args) {
        // 获取 Spring 上下文对象
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        // 对象都在 Spring 中管理了，使用直接从里面取出即可！
        Hello hello = (Hello) context.getBean("hello");
        System.out.println(hello.toString());
    }
}
