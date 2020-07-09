package Mybatis.Entity;

import lombok.Data;

@Data
public class Account {

    private long id;
    private String username;
    private String password;
    private int age;

}
