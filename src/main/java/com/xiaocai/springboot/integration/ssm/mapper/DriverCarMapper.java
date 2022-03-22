package com.xiaocai.springboot.integration.ssm.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DriverCarMapper {
    List getDriverCarByDriverName(String driverName);
}
