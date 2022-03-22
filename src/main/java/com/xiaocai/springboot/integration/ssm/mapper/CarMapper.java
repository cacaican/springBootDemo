package com.xiaocai.springboot.integration.ssm.mapper;

import com.xiaocai.springboot.integration.ssm.entity.Car;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CarMapper {
   List getAllCar();

    Car getCarById(int id);

    List getCar(Map condition);

   /*  boolean updateCar(int id, Car car);

    boolean deleteCar(int id);*/

    boolean createCar(Car car);
}
