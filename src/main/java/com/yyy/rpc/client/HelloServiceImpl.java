package com.yyy.rpc.client;

import com.yyy.po.Person;
import com.yyy.service.HelloService;

/**
 * @author yinyiyun
 * @date 2018/5/25 16:46
 */
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(Person person) {
        return null;
    }

    @Override
    public Person getPerson(String id, String name) {
        return null;
    }
}
