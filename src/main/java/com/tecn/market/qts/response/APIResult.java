package com.tecn.market.qts.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: xiuwei.nie
 * @Date: 2021/4/15
 * @Description:
 */
@Data
public class APIResult<T> {

    @ApiModelProperty("返回码")
    protected Integer code;

    @ApiModelProperty("返回码信息")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    protected String message;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    protected T data;

    protected Integer count = 0;

    public APIResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public APIResult(Integer code, String message, int count) {
        this.code = code;
        this.message = message;
        this.count = count;
    }

    public APIResult(Integer code, String message, T result) {
        this.code = code;
        this.message = message;
        this.data = result;
    }

    public APIResult(Integer code, String message, T result, int count) {
        this.code = code;
        this.message = message;
        this.data = result;
        this.count = count;
    }
}
