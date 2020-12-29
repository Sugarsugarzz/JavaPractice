package com.sugar.mybatis_plus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sugar.mybatis_plus.pojo.User;
import org.springframework.stereotype.Repository;

// 在对应的Mapper上实现基本的接口
@Repository
public interface UserMapper extends BaseMapper<User> {
    // 所有的CRUD操作已完成，不需要像以前一样都写出来
}
