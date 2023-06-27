package com.tecn.market.qts;

import com.gta.qts.c2j.adaptee.IGTAQTSApi;
import com.gta.qts.c2j.adaptee.IGTAQTSCallbackBase;
import com.gta.qts.c2j.adaptee.impl.GTAQTSApiBaseImplEx;
import com.gta.qts.c2j.adaptee.structure.*;
import com.tecn.market.qts.entity.*;
import com.tecn.market.qts.queue.AutoWaitHandleQueue;
import com.tecn.market.qts.queue.Event;
import com.tecn.market.qts.util.GtaBuilds;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * 重要说明：
 * A 如果创建的IGTAQTSApi是由GTAQTSApiBaseImpl类实例化的，那接收数据的回调函数中的对应消息类型的批量数据的回调接口起作用
 * B 如果创建的IGTAQTSApi是由GTAQTSApiBaseImplEx类实例化的，那接收数据的回调函数中的对应消息类型的非批量数据的回调接口起作用
 * IGTAQTSCallbackBase中相同消息类型的批量和非批量接口的回调两个中只有一个起作用，如果一个有数据，另外一个就没有数据，这取决于你实现的
 * 类对象的类型；如果只有一个回调函数，就不区分；本例子中使用的是非批量接口；
 * 如果要使用批量接口的，请参考开发包中的Example_Java_HighSpeed例子
 **/
public class GTAQTSManage {

    private static IGTAQTSApi baseService;
    private static boolean isSuccess = false;

    private static boolean isPro = false;

    public static boolean isIsPro() {
        return isPro;
    }
    public static void setIsPro(boolean isPro) {
        GTAQTSManage.isPro = isPro;
    }

    public static void init() throws IOException {
        //创建消息回调对象，用于接收实时数据
        System.out.print("GTAQTSManage start..."+"\n");
        //1====== 创建消息回调对象，用于接收实时数据
        IGTAQTSCallbackBase callback = new GTACallbackBase();
        //创建API对象，与服务端交互使用,高性能的回调接口要用名为GTAQTSApiBaseImplEx类，数据就是批量回调接口处理，
        //老的接口暂时不提供数据回调,具体参加说明文档,那些消息类型究竟是那个接口，参考用户手册
        baseService = GTAQTSApiBaseImplEx.getInstance().CreateInstance(callback);
        //2======基础API环境初始化，在开始使用API操作函数前，只调用一次
        baseService.BaseInit();
        //设置超时时间
        baseService.BaseSetTimeout(30);
        isSuccess = false;
    }


    public static void login() {
        if (baseService == null) {
            System.out.print("未初始化"+"\n");
            return;
        }

        //开始登陆
        baseService.BaseRegisterService("103.44.253.196", (short)8866);
        isSuccess = true;
        do {
            int ret;
            //4======通过用户名与密码向服务器登陆
            if(isPro) {
                ret = baseService.BaseLoginX("grzhxs", "2nWGVUS6", "NetType=0");
            }
            else {
                ret = baseService.BaseLoginX("gzsjkj05", "3DWCQ79U", "NetType=0");
            }

            if (QTSDataType.RetCode.Ret_Success != QTSDataType.RetCode.fetchByCode(ret)) {
                System.out.print("Login 登陆失败 error:" + ret+ " \n");
                isSuccess = false;
                break;
            }
            System.out.print("Login success"+" \n");


            // 订阅上交所 L2 实时指数 SSEL2_Static
            ret = baseService.BaseSubscribe(QTSDataType.MsgType.SSEL2_Static.code, null);
            if (QTSDataType.RetCode.Ret_Success != QTSDataType.RetCode.fetchByCode(ret)) {
                System.out.print("Subscribe SSEL2_Static 订阅上交所 L2 基本数据 error:" + ret+ " \n");
                isSuccess = false;
                break;
            }
            System.out.print("Subscribe SSEL2_Static 订阅上交所 L2 基本数据 success"+" \n");

            // 订阅深交所 L2 基本数据 SZSEL2_Static
            ret = baseService.BaseSubscribe(QTSDataType.MsgType.SZSEL2_Static.code, null);
            if (QTSDataType.RetCode.Ret_Success != QTSDataType.RetCode.fetchByCode(ret)) {
                System.out.print("Subscribe SZSEL2_Static 订阅深交所 L2 基本数据 error:" + ret+ " \n");
                isSuccess = false;
                break;
            }
            System.out.print("Subscribe SZSEL2_Static 订阅深交所 L2 基本数据 success"+" \n");



            // 订阅上交所 L2 实时指数 SSEL2_Index
            ret = baseService.BaseSubscribe(QTSDataType.MsgType.SSEL2_Index.code, "000001");
            if (QTSDataType.RetCode.Ret_Success != QTSDataType.RetCode.fetchByCode(ret)) {
                System.out.print("Subscribe SSEL2_Index 订阅上交所 L2 指数行情 error:" + ret+ " \n");
                isSuccess = false;
                break;
            }
            System.out.print("Subscribe SSEL2_Index 订阅上交所 L2 指数行情 success"+" \n");

            // 订阅深交所 L2 实时指数 SZSEL2_Index
            ret = baseService.BaseSubscribe(QTSDataType.MsgType.SZSEL2_Index.code, "399001,399006");
            if (QTSDataType.RetCode.Ret_Success != QTSDataType.RetCode.fetchByCode(ret)) {
                System.out.print("Subscribe SSEL2_Index 订阅深交所 L2 指数行情 error:" + ret+ " \n");
                isSuccess = false;
                break;
            }
            System.out.print("Subscribe SSEL2_Index 订阅深交所 L2 指数行情 success"+" \n");



            // 订阅上交所 L2 实时行情 SSEL2_Quotation
            ret = baseService.BaseSubscribe(QTSDataType.MsgType.SSEL2_Quotation.code, null);
            if (QTSDataType.RetCode.Ret_Success != QTSDataType.RetCode.fetchByCode(ret)) {
                System.out.print("Subscribe SSEL2_Quotation 订阅上交所 L2 实时行情 error:" + ret+ " \n");
                isSuccess = false;
                break;
            }
            System.out.print("Subscribe SSEL2_Quotation 订阅上交所 L2 实时行情 success"+" \n");

            // 订阅深交所 L2 实时行情 SZSEL2_Quotation
            ret = baseService.BaseSubscribe(QTSDataType.MsgType.SZSEL2_Quotation.code, null);
            if (QTSDataType.RetCode.Ret_Success != QTSDataType.RetCode.fetchByCode(ret)) {
                System.out.print("Subscribe SZSEL2_Quotation 订阅订阅深交所 L2 实时行情 error:" + ret+ " \n");
                isSuccess = false;
                break;
            }
            System.out.print("Subscribe SZSEL2_Quotation 订阅订阅深交所 L2 实时行情 success"+" \n");



            // 订阅上交所 L2 逐笔委托 SSEL2_Order
            ret = baseService.BaseSubscribe(QTSDataType.MsgType.SSEL2_Order.code, null);
            if (QTSDataType.RetCode.Ret_Success != QTSDataType.RetCode.fetchByCode(ret)) {
                System.out.print("Subscribe SSEL2_Order 订阅上交所 L2 逐笔委托 error:" + ret+ " \n");
                isSuccess = false;
                break;
            }
            System.out.print("Subscribe SSEL2_Order 订阅上交所 L2 逐笔委托 success"+" \n");

            // 订阅深交所 L2 逐笔委托
            ret = baseService.BaseSubscribe(QTSDataType.MsgType.SZSEL2_Order.code, null);
            if (QTSDataType.RetCode.Ret_Success != QTSDataType.RetCode.fetchByCode(ret)) {
                System.out.print("Subscribe SZSEL2_Order 订阅深交所 L2 逐笔委托 error:" + ret+ " \n");
                isSuccess = false;
                break;
            }
            System.out.print("Subscribe SZSEL2_Order 订阅深交所 L2 逐笔委托 success"+" \n");



            // 订阅上交所 L2 逐笔成交 SSEL2_Transaction
            ret = baseService.BaseSubscribe(QTSDataType.MsgType.SSEL2_Transaction.code, null);
            if (QTSDataType.RetCode.Ret_Success != QTSDataType.RetCode.fetchByCode(ret)) {
                System.out.print("Subscribe 上交所 L2 逐笔成交 error:" + ret+ " \n");
                isSuccess = false;
                break;
            }
            System.out.print("Subscribe 上交所 L2 逐笔成交 success"+" \n");

            // 订阅深交所 L2 逐笔成交 SZSEL2_Transaction
            ret = baseService.BaseSubscribe(QTSDataType.MsgType.SZSEL2_Transaction.code, null);
            if (QTSDataType.RetCode.Ret_Success != QTSDataType.RetCode.fetchByCode(ret)) {
                System.out.print("Subscribe SZSEL2_Transaction 订阅深交所 L2 逐笔成交 error:" + ret+ " \n");
                isSuccess = false;
                break;
            }
            System.out.print("Subscribe SZSEL2_Transaction 订阅深交所 L2 逐笔成交 success"+" \n");
        }
        while (false);

        if (!isSuccess) {
            System.out.print("GTAQTSManage login 失败!!!"+"\n");
            AutoWaitHandleQueue.produce(Event.qtsReLogin);
        }

        AutoWaitHandleQueue.produce(Event.refreshStatic);
    }

    public static void release() {
        if (baseService == null) {
            System.out.print("未初始化"+"\n");
            return;
        }
        //接口服务释放
        baseService.BaseUninit();
        baseService = null;
        isSuccess = false;

        System.out.print("GTAQTSManage release"+"\n");
    }


    /**
     *  上交所 L2 静态数据 SSEL2_Static
     * @return
     */
    public static List<StockStatic> getSSEL2Static(String stockCode) {
        if (baseService == null) {
            System.out.print("未启动引擎"+" \n");
            return null;
        }
        //=======>上交所L2行情快照查询
        List<SSEL2_Static> snapList = new ArrayList<>();
        int ret = baseService.QuerySnap_SSEL2_Static(stockCode, snapList);
        if (QTSDataType.RetCode.Ret_Success != QTSDataType.RetCode.fetchByCode(ret)) {
            System.out.print("QuerySnap_SSEL2_Static error:" + ret+ " \n");
            return null;
        }
        List<StockStatic> stockStatics = GtaBuilds.castStockStaticsSSE(snapList);
        System.out.print("getSSEL2Static success : count=" + stockStatics.size()+" \n");
        return stockStatics;
    }

    /**
     *  深交所 L2 静态数据 SZSEL2_Static
     * @return
     */
    public static List<StockStatic> getSZSEL2Static(String stockCode) {
        if (baseService == null) {
            System.out.print("未启动引擎"+" \n");
            return null;
        }
        //=======>深交所 L2 静态数据
        List<SZSEL2_Static> snapList = new ArrayList<>();
        int ret = baseService.QuerySnap_SZSEL2_Static(stockCode, snapList);
        if (QTSDataType.RetCode.Ret_Success != QTSDataType.RetCode.fetchByCode(ret)) {
            System.out.print("QuerySnap_SZSEL2_Static error:" + ret+ " \n");
            return null;
        }
        List<StockStatic> stockStatics =GtaBuilds.castStockStaticsSZSE(snapList);
        System.out.print("getSZSEL2Static success : count=" + stockStatics.size()+" \n");
        return stockStatics;
    }


    /**
     * 上交所个股期权 L1 实时行情
     * @return
     */
    public static List<StockQuotation> getSSEL2Quotation(String stockCode) {
        if (baseService == null) {
            System.out.print("未启动引擎"+" \n");
            return null;
        }
        //=======>上交所L2行情快照查询
        List<SSEL2_Quotation> snapList = new ArrayList<>();
        int ret = baseService.QuerySnap_SSEL2_Quotation(stockCode, snapList);
        if (QTSDataType.RetCode.Ret_Success != QTSDataType.RetCode.fetchByCode(ret)) {
            System.out.print("QuerySnap_SSEL2_Quotation error:" + ret+ " \n");
            return null;
        }
        List<StockQuotation> stockQuotations = GtaBuilds.castStockQuotationsSSE(snapList);
        System.out.print("QuerySnap_SSEL2_Quotation success : count=" + stockQuotations.size()+ " \n");
        return stockQuotations;
    }
    /**
     *  深交所 L2 实时行情
     * @return
     */
    public static List<StockQuotation> getSZSEL2Quotation(String stockCode) {
        if (baseService == null) {
            System.out.print("未启动引擎"+" \n");
            return null;
        }
        //=======>深所L2行情快照查询
        List<SZSEL2_Quotation> snapList = new ArrayList<>();
        int ret = baseService.QuerySnap_SZSEL2_Quotation(stockCode, snapList);
        if (QTSDataType.RetCode.Ret_Success != QTSDataType.RetCode.fetchByCode(ret)) {
            System.out.print("QuerySnap_SZSEL2_Quotation error:" + ret+ " \n");
            return null;
        }
        List<StockQuotation> stockQuotations = GtaBuilds.castStockQuotationsSZSE(snapList);
        System.out.print("QuerySnap_SZSEL2_Quotation success : count=" + stockQuotations.size()+ " \n");
        return stockQuotations;
    }



    /**
     * 上交所 L2 指数行情
     * @param stockCode
     * @return
     */
    public static List<IndexQuotation> getSSEL2Index (String stockCode) {
        if (baseService == null) {
            System.out.print("未启动引擎"+" \n");
            return null;
        }
        //=======>深所L2 逐笔委托
        List<SSEL2_Index> snapList = new ArrayList<>();
        int ret = baseService.QuerySnap_SSEL2_Index(stockCode, snapList);
        if (QTSDataType.RetCode.Ret_Success != QTSDataType.RetCode.fetchByCode(ret)) {
            System.out.print("QuerySnap_SSEL2_Index error:" + ret+ " \n");
            return null;
        }
        List<IndexQuotation> indexQuotations = GtaBuilds.castIndexQuotationsSSE(snapList);
        System.out.print("QuerySnap_SSEL2_Index success : count=" + indexQuotations.size()+ " \n");
        return indexQuotations;
    }
    /**
     * 深交所 L2 指数快照
     * @param stockCode
     * @return
     */
    public static List<IndexQuotation> getSZSEL2Index(String stockCode) {
        if (baseService == null) {
            System.out.print("未启动引擎"+" \n");
            return null;
        }
        //=======>深所L2 指数快照
        List<SZSEL2_Index> snapList = new ArrayList<>();
        int ret = baseService.QuerySnap_SZSEL2_Index(stockCode, snapList);
        if (QTSDataType.RetCode.Ret_Success != QTSDataType.RetCode.fetchByCode(ret)) {
            System.out.print("QuerySnap_SZSEL2_Index error:" + ret+ " \n");
            return null;
        }
        List<IndexQuotation> indexQuotations = GtaBuilds.castIndexQuotationsSZSE(snapList);
        System.out.print("QuerySnap_SZSEL2_Index success : count=" + indexQuotations.size()+" \n");
        return  indexQuotations;
    }


    /**
     * 沪交所 L2 逐笔委托
     * @return
     */
    public static List<EntrustOrder> getSSEL2Order(String stockCode) {
        if (baseService == null) {
            System.out.print("未启动引擎"+" \n");
            return null;
        }
        //=======>沪所L2逐笔委托
        List<SSEL2_Order> snapList = new ArrayList<>();
        int ret = baseService.QuerySnap_SSEL2_Order(stockCode, snapList);
        if (QTSDataType.RetCode.Ret_Success != QTSDataType.RetCode.fetchByCode(ret)) {
            System.out.print("QuerySnap_SSEL2_Order error:" + ret+ " \n");
            return null;
        }
        List<EntrustOrder> entrustOrders = GtaBuilds.castStepOrdersSSE(snapList);
        System.out.print("QuerySnap_SSEL2_Order success : count=" + entrustOrders.size()+ " \n");
        return entrustOrders;
    }
    /**
     * 深交所 L2 逐笔委托
     * @return
     */
    public static List<EntrustOrder> getSZSEL2Order(String stockCode) {
        if (baseService == null) {
            System.out.print("未启动引擎"+" \n");
            return null;
        }
        //=======>深所L2 逐笔委托
        List<SZSEL2_Order> snapList = new ArrayList<>();
        int ret = baseService.QuerySnap_SZSEL2_Order(stockCode, snapList);
        if (QTSDataType.RetCode.Ret_Success != QTSDataType.RetCode.fetchByCode(ret)) {
            System.out.print("QuerySnap_SZSEL2_Order error:" + ret+ " \n");
            return null;
        }
        List<EntrustOrder> entrustOrders = GtaBuilds.castStepOrdersSZSE(snapList);
        System.out.print("QuerySnap_SZSEL2_Order success : count=" + entrustOrders.size()+ " \n");
        return entrustOrders;
    }


    /**
     * 沪交所 L2 逐笔成交
     * @return
     */
    public static List<StepTransaction> getSSEL2Transaction(String stockCode) {
        if (baseService == null) {
            System.out.print("未启动引擎"+" \n");
            return null;
        }
        //=======>沪所L2逐笔成交
        List<SSEL2_Transaction> snapList = new ArrayList<>();
        int ret = baseService.QuerySnap_SSEL2_Transaction(stockCode, snapList);
        if (QTSDataType.RetCode.Ret_Success != QTSDataType.RetCode.fetchByCode(ret)) {
            System.out.print("QuerySnap_SSEL2_Transaction error:" + ret+ " \n");
            return null;
        }
        List<StepTransaction> stepTransactions = GtaBuilds.castStepTransactionsSSE(snapList);
        System.out.print("QuerySnap_SSEL2_Transaction success : count=" + stepTransactions.size()+ " \n");
        return stepTransactions;
    }
    /**
     * 深交所 L2 逐笔成交
     * @return
     */
    public static List<StepTransaction> getSZSEL2Transaction(String stockCode) {
        if (baseService == null) {
            System.out.print("未启动引擎"+" \n");
            return null;
        }
        //=======>深所L2逐笔成交
        List<SZSEL2_Transaction> snapList = new ArrayList<>();
        int ret = baseService.QuerySnap_SZSEL2_Transaction(stockCode, snapList);
        if (QTSDataType.RetCode.Ret_Success != QTSDataType.RetCode.fetchByCode(ret)) {
            System.out.print("QuerySnap_SZSEL2_Transaction error:" + ret+ " \n");
            return null;
        }
        List<StepTransaction> stepTransactions = GtaBuilds.castStepTransactionsSZSE(snapList);
        System.out.print("QuerySnap_SZSEL2_Transaction success : count=" + stepTransactions.size()+ " \n");
        return stepTransactions;
    }



}
