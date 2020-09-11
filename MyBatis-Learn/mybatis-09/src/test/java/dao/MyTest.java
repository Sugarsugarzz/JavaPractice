package dao;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import pojo.User;
import utils.MybatisUtils;

import java.util.List;


public class MyTest {

    @Test
    public void queryUsersById() {

        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        // 相同SQL 只执行了一次
        User user = mapper.queryUsersById(1);
        System.out.println(user);
        System.out.println("====================");
        // 增删改
//        User u = new User(1, "haha", "121123");
//        mapper.updateUser(u);
        sqlSession.clearCache();  // 手动清除缓存

        User user2 = mapper.queryUsersById(1);
        System.out.println(user2);

        sqlSession.close();
    }

    @Test
    public void testCache() {

        // 测试二级缓存，不同SqlSession同一Mapper
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.queryUsersById(1);
        System.out.println(user);
        sqlSession.close();

        SqlSession sqlSession2 = MybatisUtils.getSqlSession();
        UserMapper mapper2 = sqlSession2.getMapper(UserMapper.class);
        User user2 = mapper2.queryUsersById(1);
        System.out.println(user2);
        sqlSession2.close();
    }


}
