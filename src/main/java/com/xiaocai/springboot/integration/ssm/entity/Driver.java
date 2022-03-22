package com.xiaocai.springboot.integration.ssm.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/22 14:28
 */
@Data
public class Driver {

    private int id;
    private int car_id;
    private String name;
    private String license;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createDate;
}
