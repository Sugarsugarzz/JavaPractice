package Mybatis.Repository;

import Mybatis.Entity.Student;

public interface StudentRepository {

    public Student findById(long id);
}
