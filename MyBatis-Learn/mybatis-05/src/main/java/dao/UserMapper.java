package dao;

import org.apache.ibatis.annotations.*;
import pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    @Select("SELECT * FROM `user`")
    List<User> getUsers();

    // 方法存在多个参数，所有的参数前面必须加上 @Param 注解
    @Select("SELECT * FROM `user` WHERE id = #{id}")
    User getUserByID(@Param("id") int id);

    @Insert("INSERT INTO `user`(`id`, `name`, `pwd`) VALUES (#{id}, #{name}, #{password})")
    int addUser(User user);

    @Update("UPDATE `user` SET name=#{name}, pwd=#{password} WHERE id=#{id}")
    int updateUser(User user);

    @Delete(("DELETE FROM `user` WHERE id = #{id}"))
    int deleteUser(@Param("id") int id);
}
