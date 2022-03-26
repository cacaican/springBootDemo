package com.xiaocai.springboot.integration.quartz.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/26 21:19
 */
@Component
public class ScheduleTask {

    //共有七组参数（只能六个），分别是秒 分钟 小时 日 月份 星期 年（可选）
    @Scheduled(cron = "0/30 * * * * *") //每30秒执行一次
//    @Scheduled(cron = "* 0/5 * * * * * *")//每5分钟秒执行一次
//    @Scheduled(cron = "0 0 0 * * * * *")//每天凌晨执行
//    @Scheduled(cron = "* * 8,12,17 * * * * *")//每天八点十二点十七点执行
//    @Scheduled(cron = "0 30 3-5 * * * * *") //每天的3——5点的30 分执行
    public void TestFixedTask(){
        System.out.println("@Scheduled(cron = \"0/30 * * * * *\")每30秒执行一次");


    }

}
