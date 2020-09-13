package dao;

public class UserDaoSqlserverImpl implements UserDao{

    @Override
    public void getUser() {
        System.out.println("Sqlserver 获取用户的数据");
    }
}
