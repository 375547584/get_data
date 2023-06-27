package com.tecn.market.qts.util;

import com.gta.qts.c2j.adaptee.structure.*;
import com.tecn.market.qts.entity.*;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
public class GtaBuilds {

    public static List<StockStatic> castStockStaticsSSE(List<SSEL2_Static> snapList) {
        List<StockStatic> stockStatics = new ArrayList<>();
        if(StringUtils.isEmpty(snapList)) {
            return stockStatics;
        }
        for (SSEL2_Static ssel2_static : snapList) {
            StockStatic stockStatic = castStockStatic(ssel2_static);
            if(stockStatic != null) {
                stockStatics.add(stockStatic);
            }
        }
        return stockStatics;
    }
    private static StockStatic castStockStatic(SSEL2_Static ssel2_static) {
        if(ssel2_static == null) {
            return null;
        }
        String code = StringUtil.toUTF8(ssel2_static.Symbol);
        if (StringUtils.isEmpty(code)) {
            return null;
        }
        if (!StockUtils.isSH(code)) {
            return null;
        }
        StockStatic stockStatic = new StockStatic();
        stockStatic.setCode(code);
        stockStatic.setBlock("SH");
        stockStatic.setStockJaneSpell(StringUtil.toUTF8(ssel2_static.ISINCode));
        stockStatic.setFirstday(String.valueOf(ssel2_static.ListingDate));

        stockStatic.setRiseStopPrice(StringUtil.convertBigDecimal(ssel2_static.PriceUpLimit));
        stockStatic.setFallStopPrice(StringUtil.convertBigDecimal(ssel2_static.PriceDownLimit));

        stockStatic.setPe(StringUtil.convertBigDecimal(ssel2_static.StaticPERatio));
        stockStatic.setFolweQuity(ssel2_static.OutstandingShare);
        stockStatic.setTotaleValue(StringUtil.convertBigDecimal(ssel2_static.TotalMarketValue));

        stockStatic.setSecurityStatus(StringUtil.toUTF8(ssel2_static.SecurityStatus));

        stockStatic.setBuyVolumeUnit(ssel2_static.BuyVolumeUnit);
        stockStatic.setSellVolumeUnit(ssel2_static.SellVolumeUnit);
        stockStatic.setDeclareVolumeFloor(ssel2_static.DeclareVolumeFloor);
        stockStatic.setDeclareVolumeCeiling(ssel2_static.DeclareVolumeCeiling);
        //涨跌幅限制类型
        stockStatic.setUpDownLimitType(StringUtil.toUTF8(ssel2_static.UpDownLimitType));

        return stockStatic;
    }

    public static List<StockQuotation> castStockQuotationsSSE(List<SSEL2_Quotation> snapList) {
        List<StockQuotation> stockQuotations = new ArrayList<>();
        if(StringUtils.isEmpty(snapList)) {
            return stockQuotations;
        }
        for (SSEL2_Quotation ssel2_quotation : snapList) {
            StockQuotation stockQuotation = castStockQuotation(ssel2_quotation);
            if(stockQuotation != null) {
                stockQuotations.add(stockQuotation);
            }
        }
        return stockQuotations;
    }
    public static StockQuotation castStockQuotation(SSEL2_Quotation ssel2_quotation) {
        if(ssel2_quotation == null) {
            return null;
        }
        String code = StringUtil.toUTF8(ssel2_quotation.Symbol);
        if (StringUtils.isEmpty(code)) {
            return null;
        }
        if(!StockUtils.isSH(code)) {
            return null;
        }
        StockQuotation stockQuotation = new StockQuotation();
        stockQuotation.setCode(code);
        //   <9:05
        // S 10   <9:15
        // C111  >=9:15 <9:25
        // P010  >=9:25 <9:30
        // T111  9:30之后
        String securityPhaseTag = StringUtil.toUTF8(ssel2_quotation.SecurityPhaseTag);
        if(StringUtils.isNotEmpty(securityPhaseTag) & securityPhaseTag.equalsIgnoreCase("E110")) {
            stockQuotation.setTradeStatus("C");
        }
        else if(StringUtils.isNotEmpty(securityPhaseTag) & securityPhaseTag.equalsIgnoreCase("T111")) {
            stockQuotation.setTradeStatus("T");
        }
        else {
            // START  - 启动
            // OCALL  - 收盘集合竞价
            // SUSP  - 停牌（SUSP和HALT的区别在于SUSP时可以撤单）
            String tradeStatus = StringUtil.toUTF8(ssel2_quotation.TradeStatus);
            // 已经闭市
            if(StringUtils.isNotEmpty(tradeStatus) & tradeStatus.equalsIgnoreCase("TRADE")) {
                stockQuotation.setTradeStatus("T");
            }
            else if(StringUtils.isNotEmpty(tradeStatus) & tradeStatus.equalsIgnoreCase("CLOSE")) {
                stockQuotation.setTradeStatus("C");
            }
            else if(StringUtils.isNotEmpty(tradeStatus) & tradeStatus.equalsIgnoreCase("ENDTR")) {
                stockQuotation.setTradeStatus("C");
            }
        }

        stockQuotation.setSendTime(LocalDateUtils.fromHMSsss(String.valueOf(ssel2_quotation.LocalTimeStamp)));
        stockQuotation.setTradeTime(LocalDateUtils.fromHMSsss(String.valueOf(ssel2_quotation.Time)));
        stockQuotation.setPrice(StringUtil.convertBigDecimal(ssel2_quotation.LastPrice));
        stockQuotation.setOpenPrice(StringUtil.convertBigDecimal(ssel2_quotation.OpenPrice));
        stockQuotation.setYClose(StringUtil.convertBigDecimal(ssel2_quotation.PreClosePrice));
        stockQuotation.setHighPrice(StringUtil.convertBigDecimal(ssel2_quotation.HighPrice));
        stockQuotation.setLowPrice(StringUtil.convertBigDecimal(ssel2_quotation.LowPrice));
        stockQuotation.setRiseStopPrice(StringUtil.convertBigDecimal(ssel2_quotation.WarrantUpLimit));
        stockQuotation.setFallStopPrice(StringUtil.convertBigDecimal(ssel2_quotation.WarrantDownLimit));
        stockQuotation.setVol(new Double(ssel2_quotation.TotalVolume).longValue());
        stockQuotation.setAmount(StringUtil.convertBigDecimal(ssel2_quotation.TotalAmount));

        BigDecimal risefall = BigDecimalUtil.minus(stockQuotation.getPrice(), stockQuotation.getYClose());
        BigDecimal increase = BigDecimalUtil.divide4Scale(risefall, stockQuotation.getYClose());
        stockQuotation.setIncrease(BigDecimalUtil.multiply100(increase));
        stockQuotation.setRisefall(risefall);

        BuySellLevelInfo3[] sellLevels = ssel2_quotation.SellLevel;
        for (int i = 0; i < sellLevels.length; i++) {
            BuySellLevelInfo3 sellLevel = sellLevels[i];
            if(i == 0) {
                stockQuotation.setSellpx1(StringUtil.convertBigDecimal(sellLevel.Price));
                stockQuotation.setSellvol1(new Double(sellLevel.Volume).longValue());
            }
            else if(i == 1) {
                stockQuotation.setSellpx2(StringUtil.convertBigDecimal(sellLevel.Price));
                stockQuotation.setSellvol2(new Double(sellLevel.Volume).longValue());
            }
            else if(i == 2) {
                stockQuotation.setSellpx3(StringUtil.convertBigDecimal(sellLevel.Price));
                stockQuotation.setSellvol3(new Double(sellLevel.Volume).longValue());
            }
            else if(i == 3) {
                stockQuotation.setSellpx4(StringUtil.convertBigDecimal(sellLevel.Price));
                stockQuotation.setSellvol4(new Double(sellLevel.Volume).longValue());
            }
            else if(i == 4) {
                stockQuotation.setSellpx5(StringUtil.convertBigDecimal(sellLevel.Price));
                stockQuotation.setSellvol5(new Double(sellLevel.Volume).longValue());
            }
            else if(i == 5) {
                stockQuotation.setSellpx6(StringUtil.convertBigDecimal(sellLevel.Price));
                stockQuotation.setSellvol6(new Double(sellLevel.Volume).longValue());
            }
            else if(i == 6) {
                stockQuotation.setSellpx7(StringUtil.convertBigDecimal(sellLevel.Price));
                stockQuotation.setSellvol7(new Double(sellLevel.Volume).longValue());
            }
            else if(i == 7) {
                stockQuotation.setSellpx8(StringUtil.convertBigDecimal(sellLevel.Price));
                stockQuotation.setSellvol8(new Double(sellLevel.Volume).longValue());
            }
            else if(i == 8) {
                stockQuotation.setSellpx9(StringUtil.convertBigDecimal(sellLevel.Price));
                stockQuotation.setSellvol9(new Double(sellLevel.Volume).longValue());
            }
            else if(i == 9) {
                stockQuotation.setSellpx10(StringUtil.convertBigDecimal(sellLevel.Price));
                stockQuotation.setSellvol10(new Double(sellLevel.Volume).longValue());
            }
        }

        BuySellLevelInfo3[] buyLevels = ssel2_quotation.BuyLevel;
        for (int i = 0; i < buyLevels.length; i++) {
            BuySellLevelInfo3 buyLevel = buyLevels[i];
            if(i == 0) {
                stockQuotation.setBuypx1(StringUtil.convertBigDecimal(buyLevel.Price));
                stockQuotation.setBuyvol1(new Double(buyLevel.Volume).longValue());
            }
            else if(i == 1) {
                stockQuotation.setBuypx2(StringUtil.convertBigDecimal(buyLevel.Price));
                stockQuotation.setBuyvol2(new Double(buyLevel.Volume).longValue());
            }
            else if(i == 2) {
                stockQuotation.setBuypx3(StringUtil.convertBigDecimal(buyLevel.Price));
                stockQuotation.setBuyvol3(new Double(buyLevel.Volume).longValue());
            }
            else if(i == 3) {
                stockQuotation.setBuypx4(StringUtil.convertBigDecimal(buyLevel.Price));
                stockQuotation.setBuyvol4(new Double(buyLevel.Volume).longValue());
            }
            else if(i == 4) {
                stockQuotation.setBuypx5(StringUtil.convertBigDecimal(buyLevel.Price));
                stockQuotation.setBuyvol5(new Double(buyLevel.Volume).longValue());
            }
            else if(i == 5) {
                stockQuotation.setBuypx6(StringUtil.convertBigDecimal(buyLevel.Price));
                stockQuotation.setBuyvol6(new Double(buyLevel.Volume).longValue());
            }
            else if(i == 6) {
                stockQuotation.setBuypx7(StringUtil.convertBigDecimal(buyLevel.Price));
                stockQuotation.setBuyvol7(new Double(buyLevel.Volume).longValue());
            }
            else if(i == 7) {
                stockQuotation.setBuypx8(StringUtil.convertBigDecimal(buyLevel.Price));
                stockQuotation.setBuyvol8(new Double(buyLevel.Volume).longValue());
            }
            else if(i == 8) {
                stockQuotation.setBuypx9(StringUtil.convertBigDecimal(buyLevel.Price));
                stockQuotation.setBuyvol9(new Double(buyLevel.Volume).longValue());
            }
            else if(i == 9) {
                stockQuotation.setBuypx10(StringUtil.convertBigDecimal(buyLevel.Price));
                stockQuotation.setBuyvol10(new Double(buyLevel.Volume).longValue());
            }
        }

        return stockQuotation;
    }

    public static List<IndexQuotation> castIndexQuotationsSSE(List<SSEL2_Index> snapList) {
        List<IndexQuotation> indexQuotations = new ArrayList<>();
        if(StringUtils.isEmpty(snapList)) {
            return indexQuotations;
        }
        for (SSEL2_Index ssel2_index : snapList) {
            IndexQuotation indexQuotation = castIndexQuotation(ssel2_index);
            if(indexQuotation != null) {
                indexQuotations.add(indexQuotation);
            }
        }
        return indexQuotations;
    }
    public static IndexQuotation castIndexQuotation(SSEL2_Index ssel2_index) {
        if(ssel2_index == null) {
            return null;
        }
        String code = StringUtil.toUTF8(ssel2_index.Symbol);
        if (StringUtils.isEmpty(code)) {
            return null;
        }
        IndexQuotation indexQuotation = new IndexQuotation();
        indexQuotation.setCode(code);
        indexQuotation.setBlock("SH");
        indexQuotation.setSendTime(LocalDateUtils.fromHMSsss(String.valueOf(ssel2_index.LocalTimeStamp)));
        indexQuotation.setTradeTime(LocalDateUtils.fromHMSsss(String.valueOf(ssel2_index.Time)));
        indexQuotation.setPrice(StringUtil.convertBigDecimal(ssel2_index.LastPrice));
        indexQuotation.setOpenPrice(StringUtil.convertBigDecimal(ssel2_index.OpenPrice));
        indexQuotation.setYClose(StringUtil.convertBigDecimal(ssel2_index.PreClosePrice));
        indexQuotation.setHighPrice(StringUtil.convertBigDecimal(ssel2_index.HighPrice));
        indexQuotation.setLowPrice(StringUtil.convertBigDecimal(ssel2_index.LowPrice));
        indexQuotation.setVol(new Double(ssel2_index.TotalVolume).longValue());
        indexQuotation.setAmount(StringUtil.convertBigDecimal(ssel2_index.TotalAmount));
        BigDecimal risefall = BigDecimalUtil.minus(indexQuotation.getPrice(), indexQuotation.getYClose());
        BigDecimal increase = BigDecimalUtil.divide4Scale(risefall, indexQuotation.getYClose());
        indexQuotation.setIncrease(BigDecimalUtil.multiply100(increase));
        indexQuotation.setRisefall(risefall);
        return indexQuotation;
    }

    public static List<EntrustOrder> castStepOrdersSSE(List<SSEL2_Order> snapList) {
        List<EntrustOrder> entrustOrders = new ArrayList<>();
        if(StringUtils.isEmpty(snapList)) {
            return entrustOrders;
        }
        for (SSEL2_Order ssel2_order : snapList) {
            EntrustOrder entrustOrder = castStepOrder(ssel2_order);
            if (entrustOrder != null) {
                entrustOrders.add(entrustOrder);
            }
        }
        return entrustOrders;
    }
    public static EntrustOrder castStepOrder(SSEL2_Order ssel2_order) {
        if(ssel2_order == null) {
            return null;
        }
        String code = StringUtil.toUTF8(ssel2_order.Symbol);
        if(StringUtils.isEmpty(code)) {
            return null;
        }
        if(!StockUtils.isSH(code)) {
            return null;
        }
        EntrustOrder entrustOrder = new EntrustOrder();
        entrustOrder.setCode(code);
        LocalDateTime sendTime = LocalDateUtils.fromHMSsss(String.valueOf(ssel2_order.LocalTimeStamp));
        entrustOrder.setSendTime(sendTime);
        entrustOrder.setEntrustTime(ssel2_order.Time);
        entrustOrder.setPrice(StringUtil.convertBigDecimal(ssel2_order.OrderPrice));
        entrustOrder.setVol(new Double(ssel2_order.Balance).longValue());
        String orderCode = StringUtil.toUTF8(ssel2_order.OrderCode);
        if(orderCode.equalsIgnoreCase("B")) {
            entrustOrder.setEntrustType(1);
        }
        else if(orderCode.equalsIgnoreCase("S")) {
            entrustOrder.setEntrustType(2);
        }
        String orderType = StringUtil.toUTF8(ssel2_order.OrderType);
        if(orderType.equalsIgnoreCase("A") && (entrustOrder.getEntrustType() == 1 || entrustOrder.getEntrustType() == 2)) {
            return entrustOrder;
        }
        else {
            return null;
        }
    }

    public static List<StepTransaction> castStepTransactionsSSE(List<SSEL2_Transaction> snapList) {
        List<StepTransaction> stepTransactions = new ArrayList<>();
        if(StringUtils.isEmpty(snapList)) {
            return stepTransactions;
        }
        for (SSEL2_Transaction ssel2_transaction : snapList) {
            StepTransaction stepTransaction = castStepTransaction(ssel2_transaction);
            if (stepTransaction != null) {
                stepTransactions.add(stepTransaction);
            }
        }
        return stepTransactions;
    }
    public static StepTransaction castStepTransaction(SSEL2_Transaction ssel2_transaction) {
        if(ssel2_transaction == null) {
            return null;
        }
        String code = StringUtil.toUTF8(ssel2_transaction.Symbol);
        if(StringUtils.isEmpty(code)) {
            return  null;
        }
        if(!StockUtils.isSH(code)) {
            return null;
        }
        StepTransaction stepTransaction = new StepTransaction();
        String id = String.format("s%s_r%s", ssel2_transaction.TradeChannel, ssel2_transaction.RecID);
        stepTransaction.setId(id);
        stepTransaction.setCode(code);
        stepTransaction.setTimeMillis(ssel2_transaction.TradeTime);
        LocalDateTime tradeTime = LocalDateUtils.fromHMSsss(String.valueOf(ssel2_transaction.TradeTime));
        stepTransaction.setTime(LocalDateUtils.format(tradeTime, LocalDateUtils.formatterDD7));
        stepTransaction.setPrice(StringUtil.convertBigDecimal(ssel2_transaction.TradePrice));
        stepTransaction.setVol(new Double(ssel2_transaction.TradeVolume).longValue());
        String volStr = String.valueOf(BigDecimalUtil.divide100(stepTransaction.getVol()));
        stepTransaction.setVolStr(volStr);
        stepTransaction.setAmount(BigDecimalUtil.multiply(stepTransaction.getVol(), stepTransaction.getPrice()));
        String bs = StringUtil.toUTF8(ssel2_transaction.BuySellFlag);
        if(bs.equals("N")) {
            bs = "-";
        }
        stepTransaction.setBs(bs);

        if(!stepTransaction.getBs().equalsIgnoreCase("-")) {
            return stepTransaction;
        }
        else {
            return null;
        }
    }





    public static List<StockStatic> castStockStaticsSZSE(List<SZSEL2_Static> snapList) {
        List<StockStatic> stockStatics = new ArrayList<>();
        if(StringUtils.isEmpty(snapList)) {
            return stockStatics;
        }
        for (SZSEL2_Static szsel2_static : snapList) {
            StockStatic stockStatic = castStockStatic(szsel2_static);
            if(stockStatic != null) {
                stockStatics.add(stockStatic);
            }
        }
        return stockStatics;
    }
    private static StockStatic castStockStatic(SZSEL2_Static szsel2_static) {
        if(szsel2_static == null) {
            return null;
        }
        String code = StringUtil.toUTF8(szsel2_static.Symbol);
        if (StringUtils.isEmpty(code)) {
            return null;
        }
        if(!StockUtils.isSZ(code)) {
            return null;
        }
        StockStatic stockStatic = new StockStatic();
        stockStatic.setCode(code);
        stockStatic.setBlock("SZ");
        stockStatic.setStockJaneSpell(StringUtil.toUTF8(szsel2_static.ISINCode));
        stockStatic.setFirstday(String.valueOf(szsel2_static.ListingDate));
        return stockStatic;
    }

    public static List<StockQuotation> castStockQuotationsSZSE(List<SZSEL2_Quotation> snapList) {
        List<StockQuotation> stockQuotations = new ArrayList<>();
        if(StringUtils.isEmpty(snapList)) {
            return stockQuotations;
        }
        for (SZSEL2_Quotation szsel2_quotation : snapList) {
            StockQuotation stockQuotation = castStockQuotation(szsel2_quotation);
            if(stockQuotation != null) {
                stockQuotations.add(stockQuotation);
            }
        }
        return stockQuotations;
    }
    public static StockQuotation castStockQuotation(SZSEL2_Quotation szsel2_quotation) {
        if(szsel2_quotation == null) {
            return null;
        }
        String code = StringUtil.toUTF8(szsel2_quotation.Symbol);
        if (StringUtils.isEmpty(code)) {
            return null;
        }
        if(!StockUtils.isSZ(code)) {
            return null;
        }
        StockQuotation stockQuotation = new StockQuotation();
        stockQuotation.setCode(code);

        //S0  9:05之前
        //S0  9:15之前
        //O0  9:25之前
        //B0  9:30之前
        //T0  9:30之后
        String tradeStatus = StringUtil.toUTF8(szsel2_quotation.SecurityPhaseTag);
        // 已经闭市
        if(StringUtils.isNotEmpty(tradeStatus) & tradeStatus.equalsIgnoreCase("T1")) {
            stockQuotation.setTradeStatus("T");
        }
        else if(StringUtils.isNotEmpty(tradeStatus) & tradeStatus.equalsIgnoreCase("E0")) {
            stockQuotation.setTradeStatus("C");
        }

        stockQuotation.setSendTime(LocalDateUtils.fromHMSsss(String.valueOf(szsel2_quotation.LocalTimeStamp)));
        stockQuotation.setTradeTime(LocalDateUtils.fromYMDHMSsss(String.valueOf(szsel2_quotation.Time)));
        stockQuotation.setPrice(StringUtil.convertBigDecimal(szsel2_quotation.LastPrice));
        stockQuotation.setOpenPrice(StringUtil.convertBigDecimal(szsel2_quotation.OpenPrice));
        stockQuotation.setYClose(StringUtil.convertBigDecimal(szsel2_quotation.PreClosePrice));
        stockQuotation.setHighPrice(StringUtil.convertBigDecimal(szsel2_quotation.HighPrice));
        stockQuotation.setLowPrice(StringUtil.convertBigDecimal(szsel2_quotation.LowPrice));
        stockQuotation.setRiseStopPrice(StringUtil.convertBigDecimal(szsel2_quotation.PriceUpLimit));
        stockQuotation.setFallStopPrice(StringUtil.convertBigDecimal(szsel2_quotation.PriceDownLimit));
        stockQuotation.setVol(new Double(szsel2_quotation.TotalVolume).longValue());
        stockQuotation.setAmount(StringUtil.convertBigDecimal(szsel2_quotation.TotalAmount));

        BigDecimal risefall = BigDecimalUtil.minus(stockQuotation.getPrice(), stockQuotation.getYClose());
        BigDecimal increase = BigDecimalUtil.divide4Scale(risefall, stockQuotation.getYClose());
        stockQuotation.setIncrease(BigDecimalUtil.multiply100(increase));
        stockQuotation.setRisefall(risefall);

        SZSE_BuySellLevelInfo3[] sellLevels = szsel2_quotation.SellLevel;
        for (int i = 0; i < sellLevels.length; i++) {
            SZSE_BuySellLevelInfo3 sellLevel = sellLevels[i];
            if(i == 0) {
                stockQuotation.setSellpx1(StringUtil.convertBigDecimal(sellLevel.Price));
                stockQuotation.setSellvol1(new Double(sellLevel.Volume).longValue());
            }
            else if(i == 1) {
                stockQuotation.setSellpx2(StringUtil.convertBigDecimal(sellLevel.Price));
                stockQuotation.setSellvol2(new Double(sellLevel.Volume).longValue());
            }
            else if(i == 2) {
                stockQuotation.setSellpx3(StringUtil.convertBigDecimal(sellLevel.Price));
                stockQuotation.setSellvol3(new Double(sellLevel.Volume).longValue());
            }
            else if(i == 3) {
                stockQuotation.setSellpx4(StringUtil.convertBigDecimal(sellLevel.Price));
                stockQuotation.setSellvol4(new Double(sellLevel.Volume).longValue());
            }
            else if(i == 4) {
                stockQuotation.setSellpx5(StringUtil.convertBigDecimal(sellLevel.Price));
                stockQuotation.setSellvol5(new Double(sellLevel.Volume).longValue());
            }
            else if(i == 5) {
                stockQuotation.setSellpx6(StringUtil.convertBigDecimal(sellLevel.Price));
                stockQuotation.setSellvol6(new Double(sellLevel.Volume).longValue());
            }
            else if(i == 6) {
                stockQuotation.setSellpx7(StringUtil.convertBigDecimal(sellLevel.Price));
                stockQuotation.setSellvol7(new Double(sellLevel.Volume).longValue());
            }
            else if(i == 7) {
                stockQuotation.setSellpx8(StringUtil.convertBigDecimal(sellLevel.Price));
                stockQuotation.setSellvol8(new Double(sellLevel.Volume).longValue());
            }
            else if(i == 8) {
                stockQuotation.setSellpx9(StringUtil.convertBigDecimal(sellLevel.Price));
                stockQuotation.setSellvol9(new Double(sellLevel.Volume).longValue());
            }
            else if(i == 9) {
                stockQuotation.setSellpx10(StringUtil.convertBigDecimal(sellLevel.Price));
                stockQuotation.setSellvol10(new Double(sellLevel.Volume).longValue());
            }
        }


        SZSE_BuySellLevelInfo3[] buyLevels = szsel2_quotation.BuyLevel;
        for (int i = 0; i < buyLevels.length; i++) {
            SZSE_BuySellLevelInfo3 buyLevel = buyLevels[i];
            if(i == 0) {
                stockQuotation.setBuypx1(StringUtil.convertBigDecimal(buyLevel.Price));
                stockQuotation.setBuyvol1(new Double(buyLevel.Volume).longValue());
            }
            else if(i == 1) {
                stockQuotation.setBuypx2(StringUtil.convertBigDecimal(buyLevel.Price));
                stockQuotation.setBuyvol2(new Double(buyLevel.Volume).longValue());
            }
            else if(i == 2) {
                stockQuotation.setBuypx3(StringUtil.convertBigDecimal(buyLevel.Price));
                stockQuotation.setBuyvol3(new Double(buyLevel.Volume).longValue());
            }
            else if(i == 3) {
                stockQuotation.setBuypx4(StringUtil.convertBigDecimal(buyLevel.Price));
                stockQuotation.setBuyvol4(new Double(buyLevel.Volume).longValue());
            }
            else if(i == 4) {
                stockQuotation.setBuypx5(StringUtil.convertBigDecimal(buyLevel.Price));
                stockQuotation.setBuyvol5(new Double(buyLevel.Volume).longValue());
            }
            else if(i == 5) {
                stockQuotation.setBuypx6(StringUtil.convertBigDecimal(buyLevel.Price));
                stockQuotation.setBuyvol6(new Double(buyLevel.Volume).longValue());
            }
            else if(i == 6) {
                stockQuotation.setBuypx7(StringUtil.convertBigDecimal(buyLevel.Price));
                stockQuotation.setBuyvol7(new Double(buyLevel.Volume).longValue());
            }
            else if(i == 7) {
                stockQuotation.setBuypx8(StringUtil.convertBigDecimal(buyLevel.Price));
                stockQuotation.setBuyvol8(new Double(buyLevel.Volume).longValue());
            }
            else if(i == 8) {
                stockQuotation.setBuypx9(StringUtil.convertBigDecimal(buyLevel.Price));
                stockQuotation.setBuyvol9(new Double(buyLevel.Volume).longValue());
            }
            else if(i == 9) {
                stockQuotation.setBuypx10(StringUtil.convertBigDecimal(buyLevel.Price));
                stockQuotation.setBuyvol10(new Double(buyLevel.Volume).longValue());
            }
        }
        return stockQuotation;
    }

    public static List<IndexQuotation> castIndexQuotationsSZSE(List<SZSEL2_Index> snapList) {
        List<IndexQuotation> indexQuotations = new ArrayList<>();
        if(StringUtils.isEmpty(snapList)) {
            return indexQuotations;
        }
        for (SZSEL2_Index szsel2_index : snapList) {
            IndexQuotation indexQuotation = castIndexQuotation(szsel2_index);
            if(indexQuotation != null) {
                indexQuotations.add(indexQuotation);
            }
        }
        return indexQuotations;
    }
    public static IndexQuotation castIndexQuotation(SZSEL2_Index szsel2_index) {
        if(szsel2_index == null) {
            return null;
        }
        String code = StringUtil.toUTF8(szsel2_index.Symbol);
        if (StringUtils.isEmpty(code)) {
            return null;
        }
        IndexQuotation indexQuotation = new IndexQuotation();
        indexQuotation.setCode(code);
        indexQuotation.setBlock("SZ");
        indexQuotation.setSendTime(LocalDateUtils.fromHMSsss(String.valueOf(szsel2_index.LocalTimeStamp)));
        indexQuotation.setTradeTime(LocalDateUtils.fromYMDHMSsss(String.valueOf(szsel2_index.Time)));
        indexQuotation.setPrice(StringUtil.convertBigDecimal(szsel2_index.LastPrice));
        indexQuotation.setOpenPrice(StringUtil.convertBigDecimal(szsel2_index.OpenPrice));
        indexQuotation.setYClose(StringUtil.convertBigDecimal(szsel2_index.PreClosePrice));
        indexQuotation.setHighPrice(StringUtil.convertBigDecimal(szsel2_index.HighPrice));
        indexQuotation.setLowPrice(StringUtil.convertBigDecimal(szsel2_index.LowPrice));
        indexQuotation.setVol(new Double(szsel2_index.TotalVolume).longValue());
        indexQuotation.setAmount(StringUtil.convertBigDecimal(szsel2_index.TotalAmount));
        BigDecimal risefall = BigDecimalUtil.minus(indexQuotation.getPrice(), indexQuotation.getYClose());
        BigDecimal increase = BigDecimalUtil.divide4Scale(risefall, indexQuotation.getYClose());
        indexQuotation.setIncrease(BigDecimalUtil.multiply100(increase));
        indexQuotation.setRisefall(risefall);
        return indexQuotation;
    }

    public static List<EntrustOrder> castStepOrdersSZSE(List<SZSEL2_Order> snapList) {
        List<EntrustOrder> entrustOrders = new ArrayList<>();
        if(StringUtils.isEmpty(snapList)) {
            return entrustOrders;
        }
        for (SZSEL2_Order szsel2_order : snapList) {
            EntrustOrder entrustOrder = castStepOrder(szsel2_order);
            if (entrustOrder != null) {
                entrustOrders.add(entrustOrder);
            }
        }
        return entrustOrders;
    }
    public static EntrustOrder castStepOrder(SZSEL2_Order szsel2_order) {
        if(szsel2_order == null) {
            return null;
        }
        String code = StringUtil.toUTF8(szsel2_order.Symbol);
        if(StringUtils.isEmpty(code)) {
            return null;
        }
        if(!StockUtils.isSZ(code)) {
            return null;
        }
        EntrustOrder entrustOrder = new EntrustOrder();
        entrustOrder.setCode(code);
        LocalDateTime sendTime = LocalDateUtils.fromHMSsss(String.valueOf(szsel2_order.LocalTimeStamp));
        entrustOrder.setSendTime(sendTime);
        String entrustTime = String.valueOf(szsel2_order.Time).substring(8);
        entrustOrder.setEntrustTime(StringUtil.convertInt(entrustTime));
        entrustOrder.setPrice(StringUtil.convertBigDecimal(szsel2_order.OrderPrice));
        entrustOrder.setVol(new Double(szsel2_order.OrderVolume).longValue());
        String orderCode = StringUtil.toUTF8(szsel2_order.OrderCode);
        if(orderCode.equalsIgnoreCase("1")) {
            entrustOrder.setEntrustType(1);
        }
        else if(orderCode.equalsIgnoreCase("2")) {
            entrustOrder.setEntrustType(2);
        }
        if(entrustOrder.getEntrustType() == 1 || entrustOrder.getEntrustType() == 2) {
            return entrustOrder;
        }
        else {
            return null;
        }
    }

    public static List<StepTransaction> castStepTransactionsSZSE(List<SZSEL2_Transaction> snapList) {
        List<StepTransaction> stepTransactions = new ArrayList<>();
        if(StringUtils.isEmpty(snapList)) {
            return stepTransactions;
        }
        for (SZSEL2_Transaction szsel2_transaction : snapList) {
            StepTransaction stepTransaction = castStepTransaction(szsel2_transaction);
            if (stepTransaction != null) {
                stepTransactions.add(stepTransaction);
            }
        }
        return stepTransactions;
    }
    public static StepTransaction castStepTransaction(SZSEL2_Transaction szsel2_transaction) {
        if(szsel2_transaction == null) {
            return null;
        }
        String code = StringUtil.toUTF8(szsel2_transaction.Symbol);
        if(StringUtils.isEmpty(code)) {
            return null;
        }
        if(!StockUtils.isSZ(code)) {
            return null;
        }
        StepTransaction stepTransaction = new StepTransaction();
        String id = String.format("s%s_r%s", szsel2_transaction.SetID, szsel2_transaction.RecID);
        stepTransaction.setId(id);
        stepTransaction.setCode(code);
        String tradeTimeHms = String.valueOf(szsel2_transaction.TradeTime).substring(8);
        stepTransaction.setTimeMillis(StringUtil.convertInt(tradeTimeHms));
        LocalDateTime tradeTime = LocalDateUtils.fromYMDHMSsss(String.valueOf(szsel2_transaction.TradeTime));
        stepTransaction.setTime(LocalDateUtils.format(tradeTime, LocalDateUtils.formatterDD7));
        stepTransaction.setPrice(StringUtil.convertBigDecimal(szsel2_transaction.TradePrice));
        stepTransaction.setVol(new Double(szsel2_transaction.TradeVolume).longValue());
        String volStr = String.valueOf(BigDecimalUtil.divide100(stepTransaction.getVol()));
        stepTransaction.setVolStr(volStr);
        stepTransaction.setAmount(BigDecimalUtil.multiply(stepTransaction.getVol(),stepTransaction.getPrice()));
        if(szsel2_transaction.BuyOrderID < szsel2_transaction.SellOrderID) {
            stepTransaction.setBs("B");
        }
        else if(szsel2_transaction.BuyOrderID > szsel2_transaction.SellOrderID) {
            stepTransaction.setBs("S");
        }
        else {
            stepTransaction.setBs("-");
        }
        if(!stepTransaction.getBs().equalsIgnoreCase("-")) {
            return stepTransaction;
        }
        else {
            return null;
        }
    }





}
