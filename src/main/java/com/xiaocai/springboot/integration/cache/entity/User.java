package com.xiaocai.springboot.integration.cache.entity;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/16 17:30
 */
public class User {

    private  int id ;

    private  String Name;

    public User(int id, String name) {
        this.id = id;
        Name = name;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
