package dao;

public class UserDaoMysqlImpl implements UserDao{

    @Override
    public void getUser() {
        System.out.println("MySQL 获取用户的数据");
    }
}
