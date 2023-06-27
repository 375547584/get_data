package com.tecn.market.qts.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class IndexQuotation {
    private String code;
    private String block;
    //当前价 2022-09-07 10:50:30
    private BigDecimal price;
    //交易日期 2022-09-07 10:50:30
    private LocalDateTime sendTime;
    //交易日期 2022-09-07 10:50:30
    private LocalDateTime tradeTime;
    // 涨跌幅
    private BigDecimal increase;
    // 涨跌额
    private BigDecimal risefall;
    // 开盘价
    private BigDecimal openPrice;
    // 昨日收盘价
    private BigDecimal yClose;
    // 最高股价
    private BigDecimal highPrice;
    // 最低股价
    private BigDecimal lowPrice;
    // 成交量
    private Long vol;
    // 成交额
    private BigDecimal amount;
}