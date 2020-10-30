package Mybatis.Repository;

import Mybatis.Entity.Classes;

public interface ClassesRepository {

    public Classes findById(long id);

    public Classes findByIdLazy(long id);
}
