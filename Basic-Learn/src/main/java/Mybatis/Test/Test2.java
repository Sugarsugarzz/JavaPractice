package Mybatis.Test;

import Mybatis.Entity.Account;
import Mybatis.Repository.AccountRepository;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class Test2 {

    public static void main(String[] args) {

        // 加载 MyBatis配置文件
        InputStream inputStream = Test.class.getClassLoader().getResourceAsStream("config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 获取实现接口的代理对象
        AccountRepository accountRepository = sqlSession.getMapper(AccountRepository.class);

        // save （添加、删除等数据发送变化必须提交事务）
//        Account account = new Account();
//        account.setUsername("李四");
//        account.setPassword("123456");
//        account.setAge(12);
//        accountRepository.save(account);
//        sqlSession.commit();

        // findAll
//        List<Account> list = accountRepository.findAll();
//        for (Account acc: list)
//            System.out.println(acc);

        // findById
//        Account acc = accountRepository.findById(3L);
//        System.out.println(acc);

        // update
//        Account ac = accountRepository.findById(4L);
//        ac.setUsername("小明");
//        ac.setPassword("321");
//        int result = accountRepository.update(ac);
//        System.out.println(result);
//        sqlSession.commit();

        // deleteById
//        result = accountRepository.deleteById(3L);
//        System.out.println(result);
//        sqlSession.commit();

        // findByName
//        Account a = accountRepository.findByName("小明");  // 多条会报错
//        System.out.println(a);

        // findByNameAndAge
//        System.out.println(accountRepository.findByNameAndAge("小明", 12));

        // count
//        System.out.println(accountRepository.count());

        // findNameById
        System.out.println(accountRepository.findNameById(5L));


        // 最后要关闭
        sqlSession.close();
    }
}
