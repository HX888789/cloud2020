package com.hx.cloud.service.impl;

import com.hx.cloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements PaymentService {

    /**
     * 正常访问方法
     * @param id
     * @return
     */
    @Override
    public String paymentInfo_OK(Integer id) {
        return "线程池"+Thread.currentThread().getName()+"         paymentInfo_OK,id" + id+"\t"+"O(∩_∩)O哈哈~";
    }

    @Override
    @HystrixCommand(fallbackMethod = "paymentInfo_Handler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    public String paymentInfo_TIMEOUT(Integer id) {
        try {
            TimeUnit.SECONDS.sleep(5);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return "线程池"+Thread.currentThread().getName()+"         paymentInfo_TIMEOUT,id" + id+"\t"+"O(∩_∩)O哈哈~  耗时5秒钟z";

    }

    public String paymentInfo_Handler(Integer id) {
        return "线程池"+Thread.currentThread().getName()+"         paymentInfo_Handler,id" + id+"\t"+"哭哭";
    }

}
