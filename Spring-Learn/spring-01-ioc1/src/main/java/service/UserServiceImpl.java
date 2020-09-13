package service;

import com.mysql.cj.protocol.a.MysqlBinaryValueDecoder;
import dao.UserDao;
import dao.UserDaoImpl;
import dao.UserDaoMysqlImpl;
import dao.UserDaoOracleImpl;

public class UserServiceImpl implements UserService{

    // 这种主动创建的写法，将控制权掌握在程序员手中
//    private UserDao userDao = new MysqlBinaryValueDecoder();

    // 这种set写法，使程序不具备主动性，而是被动接收对象（IOC控制反转思想）
    private UserDao userDao;

    // 利用set进行动态实现指导注入！
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void getUser() {
        userDao.getUser();
    }
}
