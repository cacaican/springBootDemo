package com.xiaocai.springboot.javase.invoke.entity;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/21 9:11
 */
public class Course {

    private String name;
    private String Score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return Score;
    }

    public void setScore(String score) {
        Score = score;
    }

    public Course(String name, String score) {
        this.name = name;
        Score = score;
    }
}
