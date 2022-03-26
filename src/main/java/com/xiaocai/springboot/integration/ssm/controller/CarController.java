package com.xiaocai.springboot.integration.ssm.controller;

import com.xiaocai.springboot.integration.ssm.entity.Car;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/22 11:18
 */
public interface CarController{
    List getAllCar();
    Car getCarById(int id);
     List getCar(Map condition);
    List getCarsByCondition(int from ,int limit ,Map condition);

    /* boolean updateCar(int id ,Car car);
     boolean deleteCar (int id);*/
    boolean createCar(Car car);

    @RequestMapping("/count")
    int count(Car car);
    /* boolean exist(Car car);*/
}
