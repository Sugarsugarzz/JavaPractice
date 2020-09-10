package dao;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;
import pojo.User;
import utils.MybatisUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UserDaoTest {

    static Logger logger = Logger.getLogger(UserDaoTest.class);

    @Test
    public void getUserByRowBounds() {

        SqlSession sqlSession = MybatisUtils.getSqlSession();

        // RowBounds 实现（实际开发中用得少了）
        RowBounds rowBounds = new RowBounds(1, 2);

        // 通过 Java 代码层面实现分页
        List<User> users = sqlSession.selectList("dao.UserMapper.getUserByRowBounds", null, rowBounds);
        for (User user : users) {
            System.out.println(user);
        }

        sqlSession.clearCache();
    }

    @Test
    public void getUserByLimit() {

        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        Map<String, Integer> map = new HashMap<>();
        map.put("startIndex", 0);
        map.put("pageSize", 2);
        List<User> users = userMapper.getUserByLimit(map);
        System.out.println(users);

        sqlSession.clearCache();
    }

    @Test
    public void test() {

        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.getUserByID(1);

        System.out.println(user);

        // 关闭 sqlSession
        sqlSession.clearCache();
    }

    @Test
    public void testLog4j() {

        logger.info("info 进入了 testLog4j 方法");
        logger.debug("debug 进入了 testLog4j 方法");
        logger.error("error 进入了 testLog4j 方法");
    }

}
