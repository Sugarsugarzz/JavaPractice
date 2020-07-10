package Mybatis.Test;

import Mybatis.Entity.Account;
import Mybatis.Repository.AccountRepository;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Test5 {

    public static void main(String[] args) {

        // 加载 MyBatis配置文件
        InputStream inputStream = Test.class.getClassLoader().getResourceAsStream("config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 动态SQL
        // 常规方式，如果少一个字段，返回的就是null
        // 因此需要动态构建，避免字段为空而查询不到的情况
        Account account = new Account();
//        account.setId(4L);
        account.setUsername("小明");
        account.setPassword("321");
        account.setAge(12);

        AccountRepository accountRepository = sqlSession.getMapper(AccountRepository.class);
        Account account1 = accountRepository.findByAccount(account);
        System.out.println(account1);

        // Set 标签（更新）
//        account1.setUsername("小李");
//        System.out.println(accountRepository.update(account1));
//        sqlSession.commit();

        // foreach 标签
        Account account2 = new Account();
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(2L);
        ids.add(4L);
        ids.add(5L);
        account2.setIds(ids);
        System.out.println(accountRepository.findByIds(account2));



        // 关闭sqlSession
        sqlSession.close();
    }
}
