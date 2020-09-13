package dao;

public class UserDaoOracleImpl implements UserDao{

    @Override
    public void getUser() {
        System.out.println("Oracle 获取用户的数据");
    }
}
