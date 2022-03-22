package com.xiaocai.springboot.integration.ssm.controller.impl;

import com.xiaocai.springboot.integration.ssm.controller.DriverCarController;
import com.xiaocai.springboot.integration.ssm.mapper.DriverCarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/22 14:36
 */
@RestController
@RequestMapping("/driver")
public class DriverCarControllerImpl implements DriverCarController {

    @Autowired
    private DriverCarMapper driverCarMapper;

    @Override
    @RequestMapping("/getDriverCarByDriverName")
    public List getDriverCarByDriverName(@RequestParam(required = true) String driverName) {
        return driverCarMapper.getDriverCarByDriverName(driverName);
    }
}
