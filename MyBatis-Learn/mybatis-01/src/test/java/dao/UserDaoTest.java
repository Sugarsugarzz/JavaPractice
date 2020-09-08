package dao;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import pojo.User;
import utils.MybatisUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoTest {

    @Test
    public void getUserList() {

        // 第一步：获得 SqlSession 对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        // 方式一：getMapper
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = userMapper.getUserList();

        // 方式二：不建议昂
//        List<User> userList = sqlSession.selectList("dao.UserDao.getUserList");

        for (User user : userList) {
            System.out.println(user);
        }

        // 关闭 sqlSession
        sqlSession.clearCache();
    }

    @Test
    public void getUserLike() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

//        List<User> users = mapper.getUserLike("%Sugar%");
        List<User> users = mapper.getUserLike("Sugar");

        for (User user : users) {
            System.out.println(user);
        }

        sqlSession.close();
    }

    @Test
    public void getUserByID() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        User user = mapper.getUserByID(1);
        System.out.println(user);


        sqlSession.close();
    }

    @Test
    public void getUserByID2() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        Map<String, Object> map = new HashMap<>();
        map.put("id", 1);

        User user = mapper.getUserByID2(map);
        System.out.println(user);

        sqlSession.close();
    }

    // 增删改需要提交事务
    @Test
    public void addUser() {

        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        mapper.addUser(new User(4, "哈哈", "123456"));
        sqlSession.commit();  // 提交事务

        sqlSession.close();
    }

    @Test
    public void addUser2() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        Map<String, Object> map = new HashMap<>();
        map.put("userid", 5);
        map.put("userName", "heihei");
        map.put("passWord", "222222");
        mapper.addUser2(map);
        sqlSession.commit();

        sqlSession.close();
    }

    @Test
    public void updateUser() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        mapper.updateUser(new User(4, "呵呵", "123123"));
        sqlSession.commit();

        sqlSession.close();
    }

    @Test
    public void deleteUser() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        mapper.deleteUser(4);
        sqlSession.commit();

        sqlSession.close();
    }
}
