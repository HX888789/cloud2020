package com.hx.cloud.controller;

import com.hx.cloud.service.PaymentFeignService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_fallbackMethod")
public class OrderController {

    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        String result = paymentFeignService.paymentInfo_OK(id);
        return result;
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentInfo_TIMEOUT_Handler",commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",
//            value = "1500")
//    })
    @HystrixCommand
    public String paymentInfo_TIMEOUT(@PathVariable("id") Integer id){
        int i = 10/0;
        String result = paymentFeignService.paymentInfo_TIMEOUT(id);
        return result;
    }

    public String paymentInfo_TIMEOUT_Handler(@PathVariable("id") Integer id){
        return "我是消费者80，对方支付系统繁忙";
    }
    public String payment_Global_fallbackMethod(){
        return "全局异常处理";
    }

}
