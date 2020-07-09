package Mybatis.Test;

import Mybatis.Repository.ClassesRepository;
import Mybatis.Repository.CustomerRepository;
import Mybatis.Repository.GoodsRepository;
import Mybatis.Repository.StudentRepository;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class Test3 {

    public static void main(String[] args) {

        // 加载 MyBatis配置文件
        InputStream inputStream = Test.class.getClassLoader().getResourceAsStream("config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        StudentRepository studentRepository = sqlSession.getMapper(StudentRepository.class);

        // 级联查询
        // findById（多对一查询）
        // Student的classes为null，需要建立映射。在xml中用resultMap实现
        System.out.println(studentRepository.findById(1L));

        ClassesRepository classesRepository = sqlSession.getMapper(ClassesRepository.class);

        // findById（一对多查询）
        System.out.println(classesRepository.findById(1L));

        // findById（多对多查询）
        CustomerRepository customerRepository = sqlSession.getMapper(CustomerRepository.class);
        System.out.println(customerRepository.findById(1L));
        GoodsRepository goodsRepository = sqlSession.getMapper(GoodsRepository.class);
        System.out.println(goodsRepository.findById(1L));

        // 关闭
        sqlSession.close();
    }
}
