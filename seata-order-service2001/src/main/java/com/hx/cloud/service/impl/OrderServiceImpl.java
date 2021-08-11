package com.hx.cloud.service.impl;

import com.hx.cloud.dao.OrderDao;
import com.hx.cloud.domain.Order;
import com.hx.cloud.service.AccountService;
import com.hx.cloud.service.OrderService;
import com.hx.cloud.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private AccountService accountService;
    @Autowired
    private StorageService storageService;

    @GlobalTransactional(name = "fsp-create-order",rollbackFor = Exception.class)
    @Override
    public void create(Order order) {
        log.info("--------------->开始新建订单");
        orderDao.create(order);
        log.info("-------------->订单微服务开始调用库存，做扣减count");
        storageService.decrease(order.getProductId(),order.getCount());
        log.info("------------->订单微服务开始调用账户，做扣减money");
        accountService.decrease(order.getUserId(),order.getMoney());
        log.info("-------------->修改订单的状态开始");
        orderDao.update(order.getUserId(),0);
        log.info("---------->下订单结束了");

    }
}
