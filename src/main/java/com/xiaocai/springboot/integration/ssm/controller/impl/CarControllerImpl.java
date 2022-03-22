package com.xiaocai.springboot.integration.ssm.controller.impl;

import com.xiaocai.springboot.integration.ssm.controller.CarController;
import com.xiaocai.springboot.integration.ssm.entity.Car;
import com.xiaocai.springboot.integration.ssm.mapper.CarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/22 12:15
 */
@RestController
@RequestMapping("/car")
public class CarControllerImpl implements CarController {
    @Autowired
    private CarMapper carMapper;

    @Override
    @RequestMapping("/getAllCar")
    public List getAllCar() {
        return carMapper.getAllCar();
    }

    @Override
    @RequestMapping("/getCarById")
    public Car getCarById(int id) {
        return carMapper.getCarById(id);
    }

    @Override
    @RequestMapping("/getCar")
    public List getCar(@RequestParam() Map condition) {
        return carMapper.getCar(condition);
    }

   /* @Override
    public boolean updateCar(int id, Car car) {
        return carMapper.updateCar(id,car);
    }

    @Override
    public boolean deleteCar(int id) {
        return carMapper.deleteCar(id);
    }*/

    @Override
    @RequestMapping("/createCar")
    public boolean createCar(Car car) {
        System.out.println("创建car");
        return carMapper.createCar(car);
    }

   /* @Override
    public boolean exist(Car car) {
        return exist(car);
    }*/
}
