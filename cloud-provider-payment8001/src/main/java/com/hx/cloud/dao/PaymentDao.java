package com.hx.cloud.dao;

import com.hx.cloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 黄旭
 */
@Mapper
public interface PaymentDao {
     int create(Payment payment);
     Payment getPaymentById(Long id);
}
