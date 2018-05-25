package com.yyy.po;

import java.io.Serializable;

/**
 * @author yinyiyun
 * @date 2018/5/25 13:53
 */
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    public Person(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
