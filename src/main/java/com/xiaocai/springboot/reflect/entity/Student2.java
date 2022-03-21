package com.xiaocai.springboot.reflect.entity;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/21 9:10
 */
public class Student2 {

    private String name;
    private int age;
    private List<Course> courses;
    private Map map;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public String exam(String name ,Course course) {
        return String.format("%s 的 %s 课程考试成绩为%s",name,course.getName(),course.getScore());
    }
}
