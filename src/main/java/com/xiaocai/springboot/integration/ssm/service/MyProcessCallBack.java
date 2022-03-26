package com.xiaocai.springboot.integration.ssm.service;

import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/24 17:43
 */
public class MyProcessCallBack implements ListenableFutureCallback< Object> {
    public MyProcessCallBack(Object p0, Object p1, Object p2, Object p3) {
    }

    @Override
    public void onFailure(Throwable ex) {

    }

    @Override
    public void onSuccess(Object result) {

    }
}
