package Mybatis.Repository;

import Mybatis.Entity.Account;

import java.util.List;

public interface AccountRepository {

    public int save(Account account);
    public int update(Account account);
    public int deleteById(long id);
    public List<Account> findAll();
    public Account findById(long id);
    public Account findByName(String name);
    public Account findByNameAndAge(String name, int age);

    public int count();
    public String findNameById(long id);

    // 动态SQL
    public Account findByAccount(Account account);
    public List<Account> findByIds(Account account);
}
