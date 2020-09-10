package dao;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import pojo.User;
import utils.MybatisUtils;

import java.util.List;


public class UserDaoTest {

    @Test
    public void test() {

        SqlSession sqlSession = MybatisUtils.getSqlSession();
        // 底层主要通过反射来实现
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = userMapper.getUsers();
        for (User user : users) {
            System.out.println(user);
        }

//        User user = userMapper.getUserByID(1);
//        System.out.println(user);

//        userMapper.addUser(new User(6, "Sugar6", "123123"));

//        userMapper.updateUser(new User(6, "Sugar666", "123123"));

//        userMapper.deleteUser(5);

        sqlSession.close();
    }


}
