package com.hx.cloud.service.impl;

import com.hx.cloud.service.PaymentFeignService;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackServiceImpl implements PaymentFeignService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "----PaymentFallbaceService fall back paymentInfo_OK";
    }

    @Override
    public String paymentInfo_TIMEOUT(Integer id) {
        return "----PaymentFallbaceService fall back paymentInfo_TIMEOUT";
    }
}
