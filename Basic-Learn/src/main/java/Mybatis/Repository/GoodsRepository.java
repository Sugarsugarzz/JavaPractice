package Mybatis.Repository;

import Mybatis.Entity.Customer;
import Mybatis.Entity.Goods;

public interface GoodsRepository {

    public Goods findById(long id);
}
