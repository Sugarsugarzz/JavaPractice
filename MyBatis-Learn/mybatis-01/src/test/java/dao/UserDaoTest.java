package dao;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import pojo.User;
import utils.MybatisUtils;

import java.util.List;

public class UserDaoTest {

    @Test
    public void test() {

        // 第一步：获得 SqlSession 对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        // 方式一：getMapper
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<User> userList = userDao.getUserList();

        // 方式二：不建议昂
//        List<User> userList = sqlSession.selectList("dao.UserDao.getUserList");

        for (User user : userList) {
            System.out.println(user);
        }

        // 关闭 sqlSession
        sqlSession.clearCache();
    }
}
