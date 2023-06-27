package com.tecn.market.qts.entity;

import lombok.Data;

import java.math.BigDecimal;

//逐笔成交
@Data
public class StepTransaction {
    private String id;
    private String code;
    private String time;
    //成交时间 143025060 表示 14:30:25.060 精确到 10ms（百分之一秒）
    private int timeMillis;
    private BigDecimal price;
    private Long vol;
    private String volStr;
    private BigDecimal amount;
    private String bs;
}
