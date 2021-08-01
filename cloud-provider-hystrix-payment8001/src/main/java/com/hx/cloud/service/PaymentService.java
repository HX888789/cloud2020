package com.hx.cloud.service;

public interface PaymentService {
    String paymentInfo_OK(Integer id);
    String paymentInfo_TIMEOUT(Integer id);
}
