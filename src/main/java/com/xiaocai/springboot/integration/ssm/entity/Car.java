package com.xiaocai.springboot.integration.ssm.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/22 11:19
 */
@Data
public class Car {
    private int id;
    private String brand;
    private String color;
    private int seats;
    private double price;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createDate;

}
