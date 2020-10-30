package Mybatis.Repository;

import Mybatis.Entity.Customer;

public interface CustomerRepository {

    public Customer findById(long id);
}
