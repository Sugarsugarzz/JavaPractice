package dao;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import pojo.User;

import java.util.List;

public class UserMapperImpl2 extends SqlSessionDaoSupport implements UserMapper{


    @Override
    public List<User> selectUser() {
        UserMapper mapper = getSqlSession().getMapper(UserMapper.class);

        return mapper.selectUser();
    }
}
