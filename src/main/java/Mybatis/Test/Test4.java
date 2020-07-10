package Mybatis.Test;

import Mybatis.Entity.Account;
import Mybatis.Repository.AccountRepository;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class Test4 {

    public static void main(String[] args) {

        // 加载 MyBatis配置文件
        InputStream inputStream = Test.class.getClassLoader().getResourceAsStream("config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        /*
        MyBatis缓存：减少交互次数，提升运行效率。（数据库缓存机制）
            - 1、一级缓存：SqlSession级别，默认开启，不能关闭
                同一SqlSession对相同的SQL存入缓存。
                注：执行了DML（insert,update,delete）操作，必须将缓存清空，以保证数据的准确性。
            - 2、二级缓存：Mapper级别，默认关闭，可以开启。
                对多个SqlSession使用同一个Mapper的SQL的存入缓存。
         */

        // 一级缓存
        // 同样的sql，只查询了一次
        AccountRepository accountRepository = sqlSession.getMapper(AccountRepository.class);
        Account account = accountRepository.findById(1L);
        System.out.println(account);
        Account account1 = accountRepository.findById(1L);
        System.out.println(account1);

        // 二级缓存
        // 关闭SqlSession，会查询两次。
        // 但是开启二级缓存，只查一次。
        Account account2 = accountRepository.findById(2L);
        System.out.println(account2);
        sqlSession.close();
        sqlSession = sqlSessionFactory.openSession();
        accountRepository = sqlSession.getMapper(AccountRepository.class);
        Account account3 = accountRepository.findById(2L);
        System.out.println(account3);


    }

}
