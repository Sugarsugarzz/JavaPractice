package Mybatis.Repository;

import Mybatis.Entity.Student;

public interface StudentRepository {

    public Student findById(long id);

    // 延迟加载
    public Student findByIdLazy(long id);
}
