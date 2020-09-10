package dao;

import pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    // 查询全部用户
    List<User> getUserList();

    // 根据ID查询用户
    User getUserByID(int id);

    // insert 添加用户
    int addUser(User user);

    // update 修改用户
    int updateUser(User user);

    // delete 删除用户
    int deleteUser(int id);
}
