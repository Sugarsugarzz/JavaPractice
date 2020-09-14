package dao;

import org.mybatis.spring.SqlSessionTemplate;
import pojo.User;

import java.util.List;

public class UserMapperImpl implements UserMapper{

    // 在原来所有的操作都使用 sqlSession 来执行，现在都使用 SqlSessionTemplate
    private SqlSessionTemplate sqlSession;

    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List<User> selectUser() {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.selectUser();
    }


}
