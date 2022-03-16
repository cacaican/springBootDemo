package com.xiaocai.springboot.integration.cache.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/16 16:55
 */
@Data
public class Person implements Serializable {


    private Long id;

    private String username;

    private String email;

    public Person(Long id, String ramostear, String s) {
    }
}
