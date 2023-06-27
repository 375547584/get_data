package com.tecn.market.qts.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class StockStatic {
    private String firstday;//上市日期

    //该字段为 20 位字符串，每位表示允
    //许对应的业务，无定义则填空格。
    //第 0 位对应：‘N’表示首日上市。
    //第 1 位对应：‘D’表示除权。
    //第 2 位对应：‘R’表示除息。
    //第 3 位对应：’D’表示国内正常交易产品，’S’表示股票风险警示产品，’P’表示退市整理产品（主板），’T’表示退市转让产品，’U’表示优先股产品。
    //第 4 位对应：‘Y’表示该产品为存托凭证。
    //第 5 位对应：’L’表示债券投资者适当性要求类，’M’表示债券机构投资者适当性要求类。
    //第 6 位对应：‘F’表示 15:00 闭市的产品，‘S’表示 15:30 闭市的产品，为空表示非竞价撮合平台挂牌产品，无意义。
    //第 7 位对应：‘U’表示上市时尚未盈利的发行人的股票或存托凭证，发行人首次实现盈利后，取消该特别标识。该字段仅针对科创板产品有效。
    //第 8 位对应：‘W’表示具有表决权差异安排的发行人的股票或存托凭证。该字段仅针对科创板产品有效。
    //第 9 位对应：'Y'表示支持当日交易/回转，'N'表示不支持当日交易回转。
    private String SecurityStatus;

    private Integer buyVolumeUnit; //买订单的申报数量必须是该字段的整数倍。
    private Integer sellVolumeUnit; //卖订单的申报数量必须是该字段的整数倍。
    private Integer declareVolumeFloor; // 限价申报数量下限
    private Integer declareVolumeCeiling; //限价申报数量上限
    //‘N’表示交易规则规定的有涨跌幅限制类型或者权证管理办法第22 条规定；
    //‘R'表示交易规则规定的无涨跌幅限制类型；
    //’S‘表示回购涨跌幅控制类型；
    //‘F’表示基于参考价格的涨跌幅控制；
    //‘P’表示 IPO 上市首日的涨跌幅控制类型；
    //‘U’表示无任何价格涨跌幅控制类型。
    private String UpDownLimitType; //涨跌幅限制类型

    private String code;
    private String block;
    private String stockJaneSpell;

    private BigDecimal fallStopPrice;//跌停价
    private BigDecimal riseStopPrice;//涨停价

    private BigDecimal pe;//市盈率（静） 当前的价格/上一年的EPS

    private Long folweQuity;//流通股本
    private BigDecimal totaleValue; //总市值
}