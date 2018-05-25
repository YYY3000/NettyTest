package com.yyy.rpc.server;

import com.yyy.po.Person;
import com.yyy.service.HelloService;

/**
 * @author yinyiyun
 * @date 2018/5/25 15:03
 */
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(Person person) {
        return person.getName();
    }

    @Override
    public Person getPerson(String id, String name) {
        return new Person(id, name);
    }
}
