package dao;

import pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    // 查询全部用户
    List<User> getUserList();

    // 模糊查询
    List<User> getUserLike(String value);

    // 根据ID查询用户
    User getUserByID(int id);

    // 万能的Map2
    User getUserByID2(Map<String, Object> map);

    // insert 添加用户
    int addUser(User user);

    // 万能的Map
    int addUser2(Map<String, Object> map);

    // update 修改用户
    int updateUser(User user);

    // delete 删除用户
    int deleteUser(int id);
}
