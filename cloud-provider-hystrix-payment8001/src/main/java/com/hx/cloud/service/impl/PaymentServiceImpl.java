package com.hx.cloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.hx.cloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Data
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

    @Override
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback" , commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),//失败率达到多少后断路器断开

    })

    public String paymentCircuitBreaker(Integer id){
        if(id < 0){
            throw new RuntimeException("**********id 不能为负数");
        }
        String simpleUUID = IdUtil.simpleUUID();

        return Thread.currentThread().getName() + "\t" +"调用成功,流水号:" + simpleUUID;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "id 不能为负数 , 请稍后再试";
    }

}
