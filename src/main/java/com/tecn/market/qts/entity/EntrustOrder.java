package com.tecn.market.qts.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

//逐笔委托
@Data
public class EntrustOrder {
    //委托时间
    private LocalDateTime sendTime;
    private String code;
    private BigDecimal price;
    private Long vol;
    //  1 – 买单 2 – 卖单
    private Integer entrustType;
    //委托时间 - 143025060 表示 14:30:25.060（只精确到百分之一秒
    private int entrustTime;
}
