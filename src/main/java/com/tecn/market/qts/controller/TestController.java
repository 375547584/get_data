package com.tecn.market.qts.controller;

import com.tecn.market.qts.GTAQTSManage;
import com.tecn.market.qts.async.AsyncManager;
import com.tecn.market.qts.entity.*;
import com.tecn.market.qts.socket.StockPush;
import com.tecn.market.qts.response.APIResult;
import com.tecn.market.qts.response.APIResultWrap;
import com.tecn.market.qts.util.TimeUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.TimerTask;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @ApiOperation(value = "上交所 L2 静态数据")
    @RequestMapping(value = "/getSSEL2Static", method = RequestMethod.GET)
    public APIResult<List<StockStatic>> getSSEL2Static(@RequestParam("code") String code) {
        List<StockStatic> stockStatics = GTAQTSManage.getSSEL2Static(code);
        long mils = System.currentTimeMillis();
        StockPush.pushStockStatic(stockStatics);
        log.info("pushStockStatic ：耗时:"+ TimeUtils.getHMSTime(System.currentTimeMillis()-mils));
        return APIResultWrap.ok(stockStatics, stockStatics == null ? 0 : stockStatics.size());
    }


    @ApiOperation(value = "深交所 L2 静态数据")
    @RequestMapping(value = "/getSZSEL2Static", method = RequestMethod.GET)
    public APIResult<List<StockStatic>> getSZSEL2Static(@RequestParam("code") String code) {
        List<StockStatic> stockStatics = GTAQTSManage.getSZSEL2Static(code);
        long mils = System.currentTimeMillis();
        StockPush.pushStockStatic(stockStatics);
        log.info("pushStockStatic ：耗时:"+ TimeUtils.getHMSTime(System.currentTimeMillis()-mils));
        return APIResultWrap.ok(stockStatics, stockStatics == null ? 0 : stockStatics.size());
    }




    @ApiOperation(value = "上交所所L2行情快照查询")
    @RequestMapping(value = "/getSSEL2Quotation", method = RequestMethod.GET)
    public APIResult<List<StockQuotation>> getSSEL2Quotation(@RequestParam("code") String code) {
        List<StockQuotation> stockQuotations = GTAQTSManage.getSSEL2Quotation(code);
        long mils = System.currentTimeMillis();
        StockPush.pushStockQuotation(stockQuotations);
        log.info("pushStockQuotation ：耗时:"+ TimeUtils.getHMSTime(System.currentTimeMillis()-mils));
        return APIResultWrap.ok(stockQuotations, stockQuotations == null ? 0 : stockQuotations.size());
    }

    @ApiOperation(value = "深交所L2行情快照查询")
    @RequestMapping(value = "/getSZSEL2Quotation", method = RequestMethod.GET)
    public APIResult<List<StockQuotation>> getSZSEL2Quotation(@RequestParam("code") String code) {
        List<StockQuotation> stockQuotations = GTAQTSManage.getSZSEL2Quotation(code);
        long mils = System.currentTimeMillis();
        StockPush.pushStockQuotation(stockQuotations);
        log.info("pushStockQuotation ：耗时:"+ TimeUtils.getHMSTime(System.currentTimeMillis()-mils));
        return APIResultWrap.ok(stockQuotations, stockQuotations == null ? 0 : stockQuotations.size());
    }





    @ApiOperation(value = "上交所 L2 指数行情")
    @RequestMapping(value = "/getSSEL2Index", method = RequestMethod.GET)
    public APIResult<List<IndexQuotation>> getSSEL2Index() {
        List<IndexQuotation> indexQuotations = GTAQTSManage.getSSEL2Index("000001");
        long mils = System.currentTimeMillis();
        StockPush.pushIndexQuotation(indexQuotations);
        log.info("pushIndexQuotation ：耗时:"+ TimeUtils.getHMSTime(System.currentTimeMillis()-mils));
        return APIResultWrap.ok(indexQuotations, indexQuotations == null ? 0 : indexQuotations.size());
    }

    @ApiOperation(value = "深交所 L2 指数快照")
    @RequestMapping(value = "/getSZSEL2Index", method = RequestMethod.GET)
    public APIResult<List<IndexQuotation>> getSZSEL2Index() {
        List<IndexQuotation> indexQuotations = GTAQTSManage.getSZSEL2Index("399001,399006");
        long mils = System.currentTimeMillis();
        StockPush.pushIndexQuotation(indexQuotations);
        log.info("pushIndexQuotation ：耗时:"+ TimeUtils.getHMSTime(System.currentTimeMillis()-mils));
        return APIResultWrap.ok(indexQuotations, indexQuotations == null? 0 : indexQuotations.size());
    }





    @ApiOperation(value = "沪交所 L2 逐笔委托")
    @RequestMapping(value = "/getSSEL2Order", method = RequestMethod.GET)
    public APIResult<List<EntrustOrder>> getSSEL2Order(@RequestParam("code") String code) {
        List<EntrustOrder> entrustOrders = GTAQTSManage.getSSEL2Order(code);
        long mils = System.currentTimeMillis();
        StockPush.pushEntrustOrder(entrustOrders);
        log.info("pushEntrustOrder ：耗时:"+ TimeUtils.getHMSTime(System.currentTimeMillis()-mils));
        return APIResultWrap.ok(entrustOrders, entrustOrders == null ? 0 : entrustOrders.size());
    }

    @ApiOperation(value = "深交所 L2 逐笔委托")
    @RequestMapping(value = "/getSZSEL2Order", method = RequestMethod.GET)
    public APIResult<List<EntrustOrder>> getSZSEL2Order(@RequestParam("code") String code) {
        List<EntrustOrder> entrustOrders = GTAQTSManage.getSZSEL2Order(code);
        long mils = System.currentTimeMillis();
        StockPush.pushEntrustOrder(entrustOrders);
        log.info("pushEntrustOrder ：耗时:"+ TimeUtils.getHMSTime(System.currentTimeMillis()-mils));
        return APIResultWrap.ok(entrustOrders, entrustOrders == null ? 0 : entrustOrders.size());
    }




    @ApiOperation(value = "沪交所 L2 逐笔成交")
    @RequestMapping(value = "/getSSEL2Transaction", method = RequestMethod.GET)
    public APIResult<List<StepTransaction>> getSSEL2Transaction(@RequestParam("code") String code) {
        List<StepTransaction> stepTransactions = GTAQTSManage.getSSEL2Transaction(code);
        long mils = System.currentTimeMillis();
        StockPush.pushStepTransaction(stepTransactions);
        log.info("pushStepTransaction ：耗时:"+ TimeUtils.getHMSTime(System.currentTimeMillis()-mils));
        return APIResultWrap.ok(stepTransactions, stepTransactions == null ? 0 : stepTransactions.size());
    }

    @ApiOperation(value = "深交所 L2 逐笔成交")
    @RequestMapping(value = "/getSZSEL2Transaction", method = RequestMethod.GET)
    public APIResult<List<StepTransaction>> getSZSEL2Transaction(@RequestParam("code") String code) {
        List<StepTransaction> stepTransactions = GTAQTSManage.getSZSEL2Transaction(code);
        long mils = System.currentTimeMillis();
        StockPush.pushStepTransaction(stepTransactions);
        log.info("pushStepTransaction ：耗时:"+ TimeUtils.getHMSTime(System.currentTimeMillis()-mils));
        return APIResultWrap.ok(stepTransactions, stepTransactions == null ? 0 : stepTransactions.size());
    }


    private boolean isStart = false;
    @ApiOperation(value = "开启推送测试")
    @RequestMapping(value = "/startSocket", method = RequestMethod.GET)
    public APIResult<String> startSocket() {
        isStart = true;
        AsyncManager.me().execute(new TimerTask() {
            @Override
            public void run() {
                do {
                    StockPush.pushStockStatic(MockData.buioldStockStatics());
                    StockPush.pushIndexQuotation(MockData.buioldIndexQuotations());
                    StockPush.pushStockQuotation(MockData.buioldStockQuotations());
                    StockPush.pushEntrustOrder(MockData.buioldEntrustOrders());
                    StockPush.pushStepTransaction(MockData.buioldStepTransactions());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                while (isStart);
            }
        });
        return APIResultWrap.ok();
    }

    @ApiOperation(value = "停止推送测试")
    @RequestMapping(value = "/stopSocket", method = RequestMethod.GET)
    public APIResult<String> stopSocket() {
        isStart = false;
        return APIResultWrap.ok();
    }
}
