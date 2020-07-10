package Mybatis.Entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Account {
    // 使用Mybatis自带的二级缓存，需要实体类 implements Serializable 序列化接口
    private long id;
    private String username;
    private String password;
    private int age;

}
