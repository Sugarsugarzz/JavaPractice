import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.Student;

public class MyTest {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Student student = (Student) context.getBean("student");

        System.out.println(student.toString());

        /*
        Student{
            name='Sugar',
            address=Address{address='北京'},
            books=[Java, Python, C++],
            hobbies=[敲代码, 看电影],
            card={身份证=1313131, 手机号=1300123123},
            games=[Dota2, csgo],
            wife='null',
            info={学号=2019262, 姓名=小明}
        }
         */

    }
}
