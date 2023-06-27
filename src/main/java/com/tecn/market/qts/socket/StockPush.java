package com.tecn.market.qts.socket;

import com.corundumstudio.socketio.SocketIOClient;
import com.tecn.market.qts.cache.SocketEvent;
import com.tecn.market.qts.cache.SessionCache;
import com.tecn.market.qts.entity.*;
import com.tecn.market.qts.util.GZIPUtils;
import com.tecn.market.qts.util.JSONUtils;
import com.tecn.market.qts.util.StringUtils;

import java.util.Base64;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class StockPush {
    public static void pushStockStatic(List<StockStatic> stockStatics) {
        if(StringUtils.isEmpty(stockStatics)) {
            return;
        }
        String dataStr = JSONUtils.toJson(stockStatics);
        String compress =  Base64.getEncoder().encodeToString(GZIPUtils.compress(dataStr));
        for (SocketIOClient socketIOClient : SessionCache.socketClientMap.values()) {
            System.out.print("pushStockStatic stockStatics " + stockStatics.size()+" \n");
            socketIOClient.sendEvent(SocketEvent.stockStatic, compress);
        }
    }

    public static void pushIndexQuotation(List<IndexQuotation> indexQuotations) {
        if(StringUtils.isEmpty(indexQuotations)) {
            return;
        }
        String dataStr = JSONUtils.toJson(indexQuotations);
        String compress =  Base64.getEncoder().encodeToString(GZIPUtils.compress(dataStr));
        for (SocketIOClient socketIOClient : SessionCache.socketClientMap.values()) {
            System.out.print("pushIndexQuotation indexQuotations " + indexQuotations.size()+" \n");
            socketIOClient.sendEvent(SocketEvent.indexQuotation, compress);
        }
    }

    public static void pushStockQuotation(List<StockQuotation> stockQuotations) {
        if(StringUtils.isEmpty(stockQuotations)) {
            return;
        }
        String dataStr = JSONUtils.toJson(stockQuotations);
        String compress =  Base64.getEncoder().encodeToString(GZIPUtils.compress(dataStr));
        for (SocketIOClient socketIOClient : SessionCache.socketClientMap.values()) {
            System.out.print("pushStockQuotation stockQuotations " + stockQuotations.size()+" \n");
            socketIOClient.sendEvent(SocketEvent.stockQuotation, compress);
        }
    }

    public static void pushStepTransaction(List<StepTransaction> stepTransactions) {
        if(StringUtils.isEmpty(stepTransactions)) {
            return;
        }
        String dataStr = JSONUtils.toJson(stepTransactions);
        String compress =  Base64.getEncoder().encodeToString(GZIPUtils.compress(dataStr));
        for (SocketIOClient socketIOClient : SessionCache.socketClientMap.values()) {
            System.out.print("pushStepTransaction map " + stepTransactions.size()+" \n");
            socketIOClient.sendEvent(SocketEvent.stepTransaction, compress);
        }
    }

    public static void pushEntrustOrder(List<EntrustOrder> entrustOrders) {
//        if(StringUtils.isEmpty(entrustOrders)) {
//            return;
//        }
//        String dataStr = JSONUtils.toJson(entrustOrders);
//        String compress =  Base64.getEncoder().encodeToString(GZIPUtils.compress(dataStr));
//        for (SocketIOClient socketIOClient : SessionCache.socketClientMap.values()) {
//            System.out.print("pushEntrustOrder map " + entrustOrders.size()+" \n");
//            socketIOClient.sendEvent(SocketEvent.entrustOrder, compress);
//        }
    }

    public static void pushHeartBeat() {
        for (SocketIOClient socketIOClient : SessionCache.socketClientMap.values()) {
            System.out.print("pushHeartBeat \n");
            socketIOClient.sendEvent(SocketEvent.heartBeat, 1);
        }
    }
}
