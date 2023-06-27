package com.tecn.market.qts;


import com.gta.qts.c2j.adaptee.IGTAQTSCallbackBase;
import com.gta.qts.c2j.adaptee.structure.*;
import com.tecn.market.qts.entity.*;
import com.tecn.market.qts.socket.StockPush;
import com.tecn.market.qts.util.GtaBuilds;
import com.tecn.market.qts.util.TimeUtils;

import java.util.*;

/**
 * 订阅消息数据回调接口
 * */
public class GTACallbackBase implements IGTAQTSCallbackBase {

    private int time = 300;

    @Override
    public void OnConnectionState(int msgType, int errCode) {
        if(errCode != 0) {
            System.out.print("OnConnectionState  msgType:"+msgType+" errCode:"+errCode+" \n");
        }
    }

    @Override
    public void OnLoginState(int errCode) {
        if(errCode == 0) {
            System.out.print("OnLoginState  sucess"+" \n");
        }
        else {
            System.out.print("OnLoginState errCode:"+ errCode+" \n");
        }
    }


    @Override
    public void OnSubscribe_SSEL2_Static(SSEL2_Static data) {
        long mils = System.currentTimeMillis();
        List<StockStatic> stockStatics = GtaBuilds.castStockStaticsSSE(Collections.singletonList(data));
        StockPush.pushStockStatic(stockStatics);
        System.out.print("OnSubscribe_SSEL2_Static 单条数据 耗时:"+ TimeUtils.getHMSTime(System.currentTimeMillis()-mils)+" \n");
    }
    @Override
    public void OnSubscribe_SZSEL2_Static(SZSEL2_Static data) {
        long mils = System.currentTimeMillis();
        List<StockStatic> stockStatics = GtaBuilds.castStockStaticsSZSE(Collections.singletonList(data));
        StockPush.pushStockStatic(stockStatics);
        System.out.print("OnSubscribe_SZSEL2_Static 单条数据 耗时:"+ TimeUtils.getHMSTime(System.currentTimeMillis()-mils)+" \n");
    }


    @Override
    public void OnSubscribe_SSEL2_Index(SSEL2_Index[] data) {
        long mils = System.currentTimeMillis();
        List<IndexQuotation> indexQuotations = GtaBuilds.castIndexQuotationsSSE(Arrays.asList(data));
        StockPush.pushIndexQuotation(indexQuotations);
        System.out.print("OnSubscribe_SSEL2_Index 多条数据 length："+data.length+ " 耗时:"+ TimeUtils.getHMSTime(System.currentTimeMillis()-mils)+" \n");
    }
    @Override
    public void OnSubscribe_SZSEL2_Index(SZSEL2_Index data) {
        long mils = System.currentTimeMillis();
        List<IndexQuotation> indexQuotations = GtaBuilds.castIndexQuotationsSZSE(Collections.singletonList(data));
        StockPush.pushIndexQuotation(indexQuotations);
        System.out.print("OnSubscribe_SZSEL2_Index 单条数据 耗时:"+ TimeUtils.getHMSTime(System.currentTimeMillis()-mils)+" \n");
    }


    @Override
    public void OnSubscribe_SSEL2_Quotation(SSEL2_Quotation[] data) {
        long mils = System.currentTimeMillis();
        List<StockQuotation> stockQuotations = GtaBuilds.castStockQuotationsSSE(Arrays.asList(data));
        StockPush.pushStockQuotation(stockQuotations);
        System.out.print("OnSubscribe_SSEL2_Quotation 多条数据 length："+data.length+ " 耗时:"+ TimeUtils.getHMSTime(System.currentTimeMillis()-mils)+" \n");
    }
    @Override
    public void OnSubscribe_SZSEL2_Quotation(SZSEL2_Quotation[] data) {
        long mils = System.currentTimeMillis();
        List<StockQuotation> stockQuotations = GtaBuilds.castStockQuotationsSZSE(Arrays.asList(data));
        StockPush.pushStockQuotation(stockQuotations);
        System.out.print("OnSubscribe_SZSEL2_Quotation 多条数据 length："+data.length+ " 耗时:"+ TimeUtils.getHMSTime(System.currentTimeMillis()-mils)+" \n");
    }


    @Override
    public void OnSubscribe_SSEL2_Transaction(SSEL2_Transaction[] data) {
        long mils = System.currentTimeMillis();
        List<StepTransaction> stepTransactions = GtaBuilds.castStepTransactionsSSE(Arrays.asList(data));
        StockPush.pushStepTransaction(stepTransactions);
        System.out.print("OnSubscribe_SSEL2_Transaction 多条数据 length："+data.length+ " 耗时:"+ TimeUtils.getHMSTime(System.currentTimeMillis()-mils)+" \n");
    }
    @Override
    public void OnSubscribe_SZSEL2_Transaction(SZSEL2_Transaction[] data) {
        long mils = System.currentTimeMillis();
        List<StepTransaction> stepTransactions = GtaBuilds.castStepTransactionsSZSE(Arrays.asList(data));
        StockPush.pushStepTransaction(stepTransactions);
        System.out.print("OnSubscribe_SZSEL2_Transaction 多条数据 length："+data.length+ " 耗时:"+ TimeUtils.getHMSTime(System.currentTimeMillis()-mils)+" \n");
    }


    @Override
    public void OnSubscribe_SSEL2_Order(SSEL2_Order[] data) {
        long mils = System.currentTimeMillis();
        List<EntrustOrder> entrustOrders = GtaBuilds.castStepOrdersSSE(Arrays.asList(data));
        StockPush.pushEntrustOrder(entrustOrders);
        System.out.print("OnSubscribe_SSEL2_Order 多条数据 length："+data.length+ " 耗时:"+ TimeUtils.getHMSTime(System.currentTimeMillis()-mils)+" \n");
    }
    @Override
    public void OnSubscribe_SZSEL2_Order(SZSEL2_Order[] data) {
        long mils = System.currentTimeMillis();
        List<EntrustOrder> entrustOrders = GtaBuilds.castStepOrdersSZSE(Arrays.asList(data));
        StockPush.pushEntrustOrder(entrustOrders);
        System.out.print("OnSubscribe_SZSEL2_Order 多条数据 length："+data.length+ " 耗时:"+ TimeUtils.getHMSTime(System.currentTimeMillis()-mils)+" \n");
    }













    @Override
    public void OnSubscribe_SSEL2_Index(SSEL2_Index data) {
        long mils = System.currentTimeMillis();
        List<IndexQuotation> indexQuotations = GtaBuilds.castIndexQuotationsSSE(Collections.singletonList(data));
        StockPush.pushIndexQuotation(indexQuotations);
        System.out.print("OnSubscribe_SSEL2_Index 单条数据 耗时:"+ TimeUtils.getHMSTime(System.currentTimeMillis()-mils)+" \n");
    }

    @Override
    public void OnSubscribe_SSEL2_Quotation(SSEL2_Quotation data) {
        long mils = System.currentTimeMillis();
        List<StockQuotation> stockQuotations = GtaBuilds.castStockQuotationsSSE(Collections.singletonList(data));
        StockPush.pushStockQuotation(stockQuotations);
        System.out.print("OnSubscribe_SSEL2_Quotation 单条数据 耗时:"+ TimeUtils.getHMSTime(System.currentTimeMillis()-mils)+" \n");
    }

    @Override
    public void OnSubscribe_SSEL2_Transaction(SSEL2_Transaction data) {
        long mils = System.currentTimeMillis();
        List<StepTransaction> stepTransactions = GtaBuilds.castStepTransactionsSSE(Collections.singletonList(data));
        StockPush.pushStepTransaction(stepTransactions);
        System.out.print("OnSubscribe_SSEL2_Transaction 单条数据 耗时:"+ TimeUtils.getHMSTime(System.currentTimeMillis()-mils)+" \n");
    }

    @Override
    public void OnSubscribe_SSEL2_Order(SSEL2_Order data) {
        long mils = System.currentTimeMillis();
        List<EntrustOrder> entrustOrders = GtaBuilds.castStepOrdersSSE(Collections.singletonList(data));
        StockPush.pushEntrustOrder(entrustOrders);
        System.out.print("OnSubscribe_SSEL2_Order 单条数据 耗时:"+ TimeUtils.getHMSTime(System.currentTimeMillis()-mils)+" \n");
    }

    @Override
    public void OnSubscribe_SZSEL2_Quotation(SZSEL2_Quotation data) {
        long mils = System.currentTimeMillis();
        List<StockQuotation> stockQuotations = GtaBuilds.castStockQuotationsSZSE(Collections.singletonList(data));
        StockPush.pushStockQuotation(stockQuotations);
        System.out.print("OnSubscribe_SZSEL2_Quotation 单条数据 耗时:"+ TimeUtils.getHMSTime(System.currentTimeMillis()-mils)+" \n");
    }

    @Override
    public void OnSubscribe_SZSEL2_Transaction(SZSEL2_Transaction data) {
        long mils = System.currentTimeMillis();
        List<StepTransaction> stepTransactions = GtaBuilds.castStepTransactionsSZSE(Collections.singletonList(data));
        StockPush.pushStepTransaction(stepTransactions);
        System.out.print("OnSubscribe_SZSEL2_Transaction 单条数据 耗时:"+ TimeUtils.getHMSTime(System.currentTimeMillis()-mils)+" \n");
    }

    @Override
    public void OnSubscribe_SZSEL2_Order(SZSEL2_Order data) {
        long mils = System.currentTimeMillis();
        List<EntrustOrder> entrustOrders = GtaBuilds.castStepOrdersSZSE(Collections.singletonList(data));
        StockPush.pushEntrustOrder(entrustOrders);
        System.out.print("OnSubscribe_SZSEL2_Order 单条数据 耗时:"+ TimeUtils.getHMSTime(System.currentTimeMillis()-mils)+" \n");
    }








    @Override
    public void OnSubscribe_SSEL1_Static(SSEL1_Static data) {

    }

    @Override
    public void OnSubscribe_SSEL1_Quotation(SSEL1_Quotation data) {

    }

    @Override
    public void OnSubscribe_SSE_IndexPress(SSE_IndexPress data) {

    }

    @Override
    public void OnSubscribe_SSEIOL1_Quotation(SSEIOL1_Quotation data) {

    }

    @Override
    public void OnSubscribe_SSEIOL1_Static(SSEIOL1_Static data) {

    }

    @Override
    public void OnSubscribe_SSEL2_Auction(SSEL2_Auction data) {

    }

    @Override
    public void OnSubscribe_SSEL2_Overview(SSEL2_Overview data) {

    }

    @Override
    public void OnSubscribe_SZSEL2_Status(SZSEL2_Status data) {

    }

    @Override
    public void OnSubscribe_SZSEL1_Bulletin(SZSEL1_Bulletin arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void OnSubscribe_SZSEL1_Quotation(SZSEL1_Quotation arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void OnSubscribe_SZSEL1_Static(SZSEL1_Static arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void OnSubscribe_CFFEXL2_Static(CFFEXL2_Static data) {

    }

    @Override
    public void OnSubscribe_CFFEXL2_Quotation(CFFEXL2_Quotation data) {

    }

    @Override
    public void OnSubscribe_SHFEL1_Static(SHFEL1_Static data) {

    }

    @Override
    public void OnSubscribe_SHFEL1_Quotation(SHFEL1_Quotation data) {

    }

    @Override
    public void OnSubscribe_INEL1_Static(INEL1_Static data) {

    }

    @Override
    public void OnSubscribe_INEL1_Quotation(INEL1_Quotation data) {

    }

    @Override
    public void OnSubscribe_CZCEL1_Quotation(CZCEL1_Quotation arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void OnSubscribe_CZCEL1_Static(CZCEL1_Static arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void OnSubscribe_DCEL1_ArbiQuotation(DCEL1_ArbiQuotation arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void OnSubscribe_DCEL1_Quotation(DCEL1_Quotation arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void OnSubscribe_DCEL1_Static(DCEL1_Static arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void OnSubscribe_DCEL1_MktStatus(DCEL1_MktStatus dcel1_mktStatus) {

    }

    @Override
    public void OnSubscribe_DCEL1_Status(DCEL1_Status dcel1_status) {

    }

    @Override
    public void OnSubscribe_DCEL1_OptionParameter(DCEL1_OptionParameter dcel1_optionParameter) {

    }

    @Override
    public void OnSubscribe_DCEL2_ArbiQuotation(DCEL2_ArbiQuotation arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void OnSubscribe_DCEL2_MarchPriceQty(DCEL2_MarchPriceQty arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void OnSubscribe_DCEL2_MktStatus(DCEL2_MktStatus dcel2_mktStatus) {

    }

    @Override
    public void OnSubscribe_DCEL2_Status(DCEL2_Status dcel2_status) {

    }

    @Override
    public void OnSubscribe_DCEL2_OptionParameter(DCEL2_OptionParameter dcel2_optionParameter) {

    }

    @Override
    public void OnSubscribe_DCEL2_LevelQuotation(DCEL2_LevelQuotation dcel2_levelQuotation) {

    }

    @Override
    public void OnSubscribe_DCEL2_OrderQueue(DCEL2_OrderQueue dcel2_orderQueue) {

    }

    @Override
    public void OnSubscribe_DCEL2_OrderStatistic(DCEL2_OrderStatistic arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void OnSubscribe_DCEL2_Quotation(DCEL2_Quotation arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void OnSubscribe_DCEL2_RealTimePrice(DCEL2_RealTimePrice arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void OnSubscribe_DCEL2_Static(DCEL2_Static arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void OnSubscribe_ESUNNY_Index(ESUNNY_Index arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void OnSubscribe_HKEXL2_BrokerQueue(HKEXL2_BrokerQueue arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void OnSubscribe_HKEXL2_Index(HKEXL2_Index arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void OnSubscribe_HKEXL2_Overview(HKEXL2_Overview arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void OnSubscribe_HKEXL2_Quotation(HKEXL2_Quotation arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void OnSubscribe_HKEXL2_Static(HKEXL2_Static arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void OnDelayTime(DelayTime arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void OnSubscribe_HKEXL2_BrokerQueue(HKEXL2_BrokerQueue[] arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void OnSubscribe_HKEXL2_Quotation(HKEXL2_Quotation[] arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void OnSubscribe_HKSHL1_Quotation(HKSHL1_Quotation arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void OnSubscribe_HKSHL1_Static(HKSHL1_Static arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void OnSubscribe_HKSZL1_Quotation(HKSZL1_Quotation arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void OnSubscribe_HKSZL1_Static(HKSZL1_Static arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void OnSubscribe_INEL2_Quotation(INEL2_Quotation arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void OnSubscribe_INEL2_Static(INEL2_Static arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void OnSubscribe_SHFEL2_Quotation(SHFEL2_Quotation arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void OnSubscribe_SHFEL2_Static(SHFEL2_Static arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void OnSubscribe_SSEIOL1_Quotation(SSEIOL1_Quotation[] arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void OnSubscribe_SSEL1_Quotation(SSEL1_Quotation[] arg0) {
        // TODO Auto-generated method stub

    }


    @Override
    public void OnSubscribe_SZSEIOL1_Quotation(SZSEIOL1_Quotation arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void OnSubscribe_SZSEIOL1_Static(SZSEIOL1_Static arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void OnSubscribe_SZSEIOL1_Status(SZSEIOL1_Status arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void OnSubscribe_SZSEIOL1_Strategy(SZSEIOL1_Strategy arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void OnSubscribe_SZSEL1_Quotation(SZSEL1_Quotation[] arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void OnSubscribe_SZSEL1_BondQuotation(SZSEL1_BondQuotation[] szsel1_bondQuotations) {

    }

    @Override
    public void OnSubscribe_SZSEL2_BondQuotation(SZSEL2_BondQuotation[] szsel2_bondQuotations) {

    }

    @Override
    public void OnSubscribe_SZSEL2_BondTick(SZSEL2_BondTick[] szsel2_bondTicks) {

    }

    @Override
    public void OnSubscribe_SZSEL2_BondOrder(SZSEL2_BondOrder[] szsel2_bondOrders) {

    }

    @Override
    public void OnSubscribe_SZSEL2_BondTransaction(SZSEL2_BondTransaction[] szsel2_bondTransactions) {

    }








    @Override
    public void OnSubscribe_SSEIOL1_Strategy(SSEIOL1_Strategy arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void OnSubscribe_SZSEL2_Tick(SZSEL2_Tick arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void OnSubscribe_SZSEL2_Tick(SZSEL2_Tick[] arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void OnSubscribe_CZCEL1_ArbiQuotation(CZCEL1_ArbiQuotation arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void OnSubscribe_CZCEL1_MktStatus(CZCEL1_MktStatus arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void OnSubscribe_CZCEL1_Status(CZCEL1_Status arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void OnSubscribe_HKEXL2_DQB(HKEXL2_DQB arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void OnSubscribe_HKEXL2_MoneyFlow(HKEXL2_MoneyFlow arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void OnSubscribe_HKSHL1_Overview(HKSHL1_Overview arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void OnSubscribe_HKSZL1_Overview(HKSZL1_Overview arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void OnSubscribe_SSEL1_BondQuotation(SSEL1_BondQuotation arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void OnSubscribe_SZSEL1_BondStatic(SZSEL1_BondStatic szsel1_bondStatic) {

    }

    @Override
    public void OnSubscribe_SZSEL1_BondQuotation(SZSEL1_BondQuotation szsel1_bondQuotation) {

    }

    @Override
    public void OnSubscribe_BSEL1_Static(BSEL1_Static bsel1_static) {

    }

    @Override
    public void OnSubscribe_BSEL1_Quotation(BSEL1_Quotation bsel1_quotation) {

    }

    @Override
    public void OnSubscribe_SZSEL2_BondBidBookingInfo(SZSEL2_BondBidBookingInfo szsel2_bondBidBookingInfo) {

    }

    @Override
    public void OnSubscribe_CZCEL2_ArbiStatic(CZCEL2_ArbiStatic czcel2_arbiStatic) {

    }

    @Override
    public void OnSubscribe_CZCEL2_Static(CZCEL2_Static czcel2_static) {

    }

    @Override
    public void OnSubscribe_CZCEL2_MktStatus(CZCEL2_MktStatus czcel2_mktStatus) {

    }

    @Override
    public void OnSubscribe_CZCEL2_Status(CZCEL2_Status czcel2_status) {

    }

    @Override
    public void OnSubscribe_CZCEL2_Quotation(CZCEL2_Quotation czcel2_quotation) {

    }

    @Override
    public void OnSubscribe_CZCEL2_ChgQuotation(CZCEL2_ChgQuotation czcel2_chgQuotation) {

    }

    @Override
    public void OnSubscribe_CZCEL2_LevelQuotation(CZCEL2_LevelQuotation czcel2_levelQuotation) {

    }

    @Override
    public void OnSubscribe_CZCEL2_MarchPriceQty(CZCEL2_MarchPriceQty czcel2_marchPriceQty) {

    }


    @Override
    public void OnSubscribe_SSEL1_BondQuotation(SSEL1_BondQuotation[] arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void OnSubscribe_BSEL1_Quotation(BSEL1_Quotation[] bsel1_quotations) {

    }

    @Override
    public void OnSubscribe_SSEL1_BondStatic(SSEL1_BondStatic arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void OnSubscribe_SSEL2_BondOverview(SSEL2_BondOverview arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void OnSubscribe_SSEL2_BondQuotation(SSEL2_BondQuotation arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void OnSubscribe_SSEL2_BondQuotation(SSEL2_BondQuotation[] arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void OnSubscribe_SSEL2_BondStatic(SSEL2_BondStatic arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void OnSubscribe_SSEL2_BondTick(SSEL2_BondTick arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void OnSubscribe_SSEL2_BondTick(SSEL2_BondTick[] arg0) {
        // TODO Auto-generated method stub

    }



    @Override
    public void OnSubscribe_SZSEL2_Cnindex(SZSEL2_Cnindex arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void OnSubscribe_SZSEL2_BondStatic(SZSEL2_BondStatic szsel2_bondStatic) {

    }

    @Override
    public void OnSubscribe_SZSEL2_BondQuotation(SZSEL2_BondQuotation szsel2_bondQuotation) {

    }

    @Override
    public void OnSubscribe_SZSEL2_BondTransaction(SZSEL2_BondTransaction szsel2_bondTransaction) {

    }

    @Override
    public void OnSubscribe_SZSEL2_BondOrder(SZSEL2_BondOrder szsel2_bondOrder) {

    }

    @Override
    public void OnSubscribe_SZSEL2_BondTick(SZSEL2_BondTick szsel2_bondTick) {

    }

    @Override
    public void OnSubscribe_SZSEL2_BondMbrInfo(SZSEL2_BondMbrInfo szsel2_bondMbrInfo) {

    }

    @Override
    public void OnSubscribe_SZSEL2_BondInvestorInfo(SZSEL2_BondInvestorInfo szsel2_bondInvestorInfo) {

    }

    @Override
    public void OnSubscribe_SZSEL2_BondTraderInfo(SZSEL2_BondTraderInfo szsel2_bondTraderInfo) {

    }
}