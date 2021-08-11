package com.hx.cloud.service.impl;

import com.hx.cloud.dao.AccountDao;
import com.hx.cloud.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;

    @Override
    public void decrease(Long userId, BigDecimal money) {
        log.info("--------->account-service中扣减账户余额开始");
        accountDao.decrease(userId, money);
        log.info("--------->account-service中扣减账户余额结束");
    }
}
