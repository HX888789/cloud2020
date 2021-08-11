package com.hx.cloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;

public class CustomerBlockHander {
    public static String handler1(BlockException blockException){
        return "统一处理-----1";
    }

    public static String handler2(BlockException blockException){
        return "统一处理-----2";
    }
}
