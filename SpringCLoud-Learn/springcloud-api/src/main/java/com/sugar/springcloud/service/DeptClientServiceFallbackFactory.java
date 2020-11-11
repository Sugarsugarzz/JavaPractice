package com.sugar.springcloud.service;

import com.sugar.springcloud.pojo.Dept;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

// 服务降级~
@Component
public class DeptClientServiceFallbackFactory implements FallbackFactory {

    public DeptClientService create(Throwable throwable) {
        return new DeptClientService() {
            public Dept queryById(Long id) {
                return new Dept()
                        .setDeptno(id)
                        .setDname("没有对应的信息，客户端提供了降级的信息，这个服务现在已经被关闭")
                        .setDb_source("no database");
            }

            public List<Dept> queryAll() {
                return null;
            }

            public boolean addDept(Dept dept) {
                return false;
            }
        };
    }
}
