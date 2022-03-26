package com.xiaocai.springboot.integration.ssm.mapper;

import com.xiaocai.springboot.integration.ssm.entity.Car;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface CarMapper {
   List getAllCar();

    Car getCarById(int id);

    List getCar(@Param("condition")Map condition);

   /*  boolean updateCar(int id, Car car);

    boolean deleteCar(int id);*/

    boolean createCar(Car car);

    List getCarsByCondition(@Param("from") int from ,@Param("limit") int limit  ,@Param("condition")Map Condition);

    @Select("select count(1) from car")
    int  count();


}
