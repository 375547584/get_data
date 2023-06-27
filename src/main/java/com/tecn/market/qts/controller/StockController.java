package com.tecn.market.qts.controller;

import com.tecn.market.qts.GTAQTSManage;
import com.tecn.market.qts.entity.*;
import com.tecn.market.qts.response.APIResult;
import com.tecn.market.qts.response.APIResultWrap;
import com.tecn.market.qts.util.GZIPUtils;
import com.tecn.market.qts.util.JSONUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/stock")
@Slf4j
public class StockController {

    @ApiOperation(value = "上交所 L2 静态数据")
    @PostMapping(value = "/getSSEL2Static")
    public APIResult<String> getSSEL2Static() {
        List<StockStatic> stockStatics = GTAQTSManage.getSSEL2Static(null);
        String dataStr = JSONUtils.toJson(stockStatics);
        String compress =  Base64.getEncoder().encodeToString(GZIPUtils.compress(dataStr));
        return APIResultWrap.ok(compress, stockStatics == null ? 0 : stockStatics.size());
    }

    @ApiOperation(value = "深交所 L2 静态数据")
    @PostMapping(value = "/getSZSEL2Static")
    public APIResult<String> getSZSEL2Static() {
        List<StockStatic> stockStatics = GTAQTSManage.getSZSEL2Static(null);
        String dataStr = JSONUtils.toJson(stockStatics);
        String compress =  Base64.getEncoder().encodeToString(GZIPUtils.compress(dataStr));
        return APIResultWrap.ok(compress, stockStatics == null ? 0 : stockStatics.size());
    }

    @ApiOperation(value = "上交所所L2行情快照查询")
    @PostMapping(value = "/getSSEL2Quotation")
    public APIResult<String> getSSEL2Quotation() {
        List<StockQuotation> stockQuotations = GTAQTSManage.getSSEL2Quotation(null);
        String dataStr = JSONUtils.toJson(stockQuotations);
        String compress =  Base64.getEncoder().encodeToString(GZIPUtils.compress(dataStr));
        return APIResultWrap.ok(compress, stockQuotations == null ? 0 : stockQuotations.size());
    }

    @ApiOperation(value = "深交所L2行情快照查询")
    @PostMapping(value = "/getSZSEL2Quotation")
    public APIResult<String> getSZSEL2Quotation() {
        List<StockQuotation> stockQuotations = GTAQTSManage.getSZSEL2Quotation(null);
        String dataStr = JSONUtils.toJson(stockQuotations);
        String compress =  Base64.getEncoder().encodeToString(GZIPUtils.compress(dataStr));
        return APIResultWrap.ok(compress, stockQuotations == null ? 0 : stockQuotations.size());
    }

    @ApiOperation(value = "上交所 L2 指数行情")
    @PostMapping(value = "/getSSEL2Index")
    public APIResult<String> getSSEL2Index() {
        List<IndexQuotation> indexQuotations = GTAQTSManage.getSSEL2Index("000001");
        String dataStr = JSONUtils.toJson(indexQuotations);
        String compress =  Base64.getEncoder().encodeToString(GZIPUtils.compress(dataStr));
        return APIResultWrap.ok(compress, indexQuotations == null ? 0 : indexQuotations.size());
    }

    @ApiOperation(value = "深交所 L2 指数快照")
    @PostMapping(value = "/getSZSEL2Index")
    public APIResult<String> getSZSEL2Index() {
        List<IndexQuotation> indexQuotations = GTAQTSManage.getSZSEL2Index("399001,399006");
        String dataStr = JSONUtils.toJson(indexQuotations);
        String compress =  Base64.getEncoder().encodeToString(GZIPUtils.compress(dataStr));
        return APIResultWrap.ok(compress, indexQuotations == null? 0 : indexQuotations.size());
    }

    @ApiOperation(value = "沪交所 L2 逐笔委托")
    @PostMapping(value = "/getSSEL2Order")
    public APIResult<String> getSSEL2Order() {
        List<EntrustOrder> entrustOrders = GTAQTSManage.getSSEL2Order(null);
        String dataStr = JSONUtils.toJson(entrustOrders);
        String compress =  Base64.getEncoder().encodeToString(GZIPUtils.compress(dataStr));
        return APIResultWrap.ok(compress, entrustOrders == null ? 0 : entrustOrders.size());
    }

    @ApiOperation(value = "深交所 L2 逐笔委托")
    @PostMapping(value = "/getSZSEL2Order")
    public APIResult<String> getSZSEL2Order() {
        List<EntrustOrder> entrustOrders = GTAQTSManage.getSZSEL2Order(null);
        String dataStr = JSONUtils.toJson(entrustOrders);
        String compress =  Base64.getEncoder().encodeToString(GZIPUtils.compress(dataStr));
        return APIResultWrap.ok(compress, entrustOrders == null ? 0 : entrustOrders.size());
    }


    @ApiOperation(value = "沪交所 L2 逐笔成交")
    @PostMapping(value = "/getSSEL2Transaction")
    public APIResult<String> getSSEL2Transaction() {
        List<StepTransaction> stepTransactions = GTAQTSManage.getSSEL2Transaction(null);
        String dataStr = JSONUtils.toJson(stepTransactions);
        String compress =  Base64.getEncoder().encodeToString(GZIPUtils.compress(dataStr));
        return APIResultWrap.ok(compress, stepTransactions == null ? 0 : stepTransactions.size());
    }

    @ApiOperation(value = "深交所 L2 逐笔成交")
    @PostMapping(value = "/getSZSEL2Transaction")
    public APIResult<String> getSZSEL2Transaction() {
        List<StepTransaction> stepTransactions = GTAQTSManage.getSZSEL2Transaction(null);
        String dataStr = JSONUtils.toJson(stepTransactions);
        String compress =  Base64.getEncoder().encodeToString(GZIPUtils.compress(dataStr));
        return APIResultWrap.ok(compress, stepTransactions == null ? 0 : stepTransactions.size());
    }
}
