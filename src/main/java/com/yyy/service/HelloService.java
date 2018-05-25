package com.yyy.service;

import com.yyy.po.Person;

/**
 * @author yinyiyun
 * @date 2018/5/25 15:02
 */
public interface HelloService {

    String hello(Person person);

    Person getPerson(String id, String name);

}
