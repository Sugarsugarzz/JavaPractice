package dao;

import pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    // 根据ID查询用户
    User getUserByID(int id);

    // 分页
    List<User> getUserByLimit(Map<String, Integer> map);

    // RowBounds
    List<User> getUserByRowBounds();
}
