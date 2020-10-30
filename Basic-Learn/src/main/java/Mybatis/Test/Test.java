package Mybatis.Test;

import Mybatis.Entity.Account;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class Test {

    public static void main(String[] args) {

        // 加载 MyBatis配置文件
        InputStream inputStream = Test.class.getClassLoader().getResourceAsStream("config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 原生接口
        String statement = "Mybatis.Mapper.AccountMapper.save";
        Account account = new Account();
        account.setUsername("王哈哈");
        account.setPassword("123456");
        account.setAge(12);
        sqlSession.insert(statement, account);
        sqlSession.commit();




        // 最后要关闭
        sqlSession.close();
    }
}
