package com.hx.cloud.service.impl;

import com.hx.cloud.dao.StorageDao;
import com.hx.cloud.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StorageServiceImpl implements StorageService {
    @Autowired
    private StorageDao storageDao;

    @Override
    public void decrease(Long productId, Integer count) {
        log.info("-------->storage-service中扣减库存开始");
        storageDao.decrease(productId,count);
        log.info("-------->storage-service中扣减库存结束");
    }
}
