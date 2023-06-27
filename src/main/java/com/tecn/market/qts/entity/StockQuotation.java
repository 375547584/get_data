package com.tecn.market.qts.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class StockQuotation {

    //交易日期 2022-09-07 10:50:30
    private LocalDateTime sendTime;
    //交易日期 2022-09-07 10:50:30
    private LocalDateTime tradeTime;

    private String code;

    // T-交易中 C-闭市 为空或者其他不用处理
    private String tradeStatus;


    //当前价 2022-09-07 10:50:30
    private BigDecimal price;

    // 涨跌幅
    private BigDecimal increase;
    // 涨跌额
    private BigDecimal risefall;

    // 最高股价
    private BigDecimal highPrice;
    // 最低股价
    private BigDecimal lowPrice;

    // 开盘价
    private BigDecimal openPrice;
    // 昨日收盘价
    private BigDecimal yClose;

    // 跌停价
    private BigDecimal fallStopPrice;
    // 涨停价
    private BigDecimal riseStopPrice;

    // 成交量
    private Long vol;
    // 成交额
    private BigDecimal amount;

    @ApiModelProperty(value = "买价一")
    private BigDecimal buypx1 = BigDecimal.ZERO;
    @ApiModelProperty(value = "买价二")
    private BigDecimal buypx2 = BigDecimal.ZERO;
    @ApiModelProperty(value = "买价三")
    private BigDecimal buypx3 = BigDecimal.ZERO;
    @ApiModelProperty(value = "买价四")
    private BigDecimal buypx4 = BigDecimal.ZERO;
    @ApiModelProperty(value = "买价五")
    private BigDecimal buypx5 = BigDecimal.ZERO;
    @ApiModelProperty(value = "买价6")
    private BigDecimal buypx6 = BigDecimal.ZERO;
    @ApiModelProperty(value = "买价7")
    private BigDecimal buypx7 = BigDecimal.ZERO;
    @ApiModelProperty(value = "买价8")
    private BigDecimal buypx8 = BigDecimal.ZERO;
    @ApiModelProperty(value = "买价9")
    private BigDecimal buypx9 = BigDecimal.ZERO;
    @ApiModelProperty(value = "买价10")
    private BigDecimal buypx10 = BigDecimal.ZERO;

    @ApiModelProperty(value = "买量一")
    private Long buyvol1 = 0L;
    @ApiModelProperty(value = "买量二")
    private Long buyvol2 = 0L;
    @ApiModelProperty(value = "买量三")
    private Long buyvol3 = 0L;
    @ApiModelProperty(value = "买量四")
    private Long buyvol4 = 0L;
    @ApiModelProperty(value = "买量五")
    private Long buyvol5 = 0L;
    @ApiModelProperty(value = "买量6")
    private Long buyvol6 = 0L;
    @ApiModelProperty(value = "买量7")
    private Long buyvol7 = 0L;
    @ApiModelProperty(value = "买量8")
    private Long buyvol8 = 0L;
    @ApiModelProperty(value = "买量9")
    private Long buyvol9 = 0L;
    @ApiModelProperty(value = "买量10")
    private Long buyvol10 = 0L;

    @ApiModelProperty(value = "卖价一")
    private BigDecimal sellpx1 = BigDecimal.ZERO;
    @ApiModelProperty(value = "卖价二")
    private BigDecimal sellpx2 = BigDecimal.ZERO;
    @ApiModelProperty(value = "卖价三")
    private BigDecimal sellpx3 = BigDecimal.ZERO;
    @ApiModelProperty(value = "卖价四")
    private BigDecimal sellpx4 = BigDecimal.ZERO;
    @ApiModelProperty(value = "卖价五")
    private BigDecimal sellpx5 = BigDecimal.ZERO;
    @ApiModelProperty(value = "卖价6")
    private BigDecimal sellpx6 = BigDecimal.ZERO;
    @ApiModelProperty(value = "卖价7")
    private BigDecimal sellpx7 = BigDecimal.ZERO;
    @ApiModelProperty(value = "卖价8")
    private BigDecimal sellpx8 = BigDecimal.ZERO;
    @ApiModelProperty(value = "卖价9")
    private BigDecimal sellpx9 = BigDecimal.ZERO;
    @ApiModelProperty(value = "卖价10")
    private BigDecimal sellpx10 = BigDecimal.ZERO;

    @ApiModelProperty(value = "卖量一")
    private Long sellvol1 = 0L;
    @ApiModelProperty(value = "卖量二")
    private Long sellvol2 = 0L;
    @ApiModelProperty(value = "卖量三")
    private Long sellvol3 = 0L;
    @ApiModelProperty(value = "卖量四")
    private Long sellvol4 = 0L;
    @ApiModelProperty(value = "卖量五")
    private Long sellvol5 = 0L;
    @ApiModelProperty(value = "卖量6")
    private Long sellvol6 = 0L;
    @ApiModelProperty(value = "卖量7")
    private Long sellvol7 = 0L;
    @ApiModelProperty(value = "卖量8")
    private Long sellvol8 = 0L;
    @ApiModelProperty(value = "卖量9")
    private Long sellvol9 = 0L;
    @ApiModelProperty(value = "卖量10")
    private Long sellvol10 = 0L;
}