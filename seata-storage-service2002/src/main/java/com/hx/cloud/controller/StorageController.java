package com.hx.cloud.controller;

import com.hx.cloud.domain.CommonResult;
import com.hx.cloud.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StorageController {

    @Autowired
    private StorageService storageService;

    @RequestMapping("/storage/decrease")
    public CommonResult decrease(Long productId , Integer count){
        storageService.decrease(productId, count);
        return new CommonResult(200,"库存扣减成功");
    }

}
