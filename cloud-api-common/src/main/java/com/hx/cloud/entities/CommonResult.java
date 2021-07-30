package com.hx.cloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 黄旭
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    /**
     * 404 not_found
     */
    private Integer code;
    private String message;
    private T      data;

    public CommonResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
