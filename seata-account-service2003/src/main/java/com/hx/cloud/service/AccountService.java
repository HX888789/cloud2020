package com.hx.cloud.service;



import java.math.BigDecimal;

public interface AccountService {
    void decrease( Long userId, BigDecimal money);
}
