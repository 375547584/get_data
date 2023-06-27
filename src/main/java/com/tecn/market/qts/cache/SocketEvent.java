package com.tecn.market.qts.cache;

import java.util.concurrent.ConcurrentLinkedDeque;

public class SocketEvent {
    public static final String stockStatic = "stockStatic";
    public static final String indexQuotation = "indexQuotation";
    public static final String stockQuotation = "stockQuotation";
    public static final String stepTransaction = "stepTransaction";
    public static final String entrustOrder = "entrustOrder";

    public static final String heartBeat = "heartBeat";


    public static ConcurrentLinkedDeque<String> stockStaticSet = new ConcurrentLinkedDeque<>();
    public static ConcurrentLinkedDeque<String> indexQuotationSet = new ConcurrentLinkedDeque<>();
    public static ConcurrentLinkedDeque<String> stockQuotationSet = new ConcurrentLinkedDeque<>();
    public static ConcurrentLinkedDeque<String> stepTransactionSet = new ConcurrentLinkedDeque<>();
    public static ConcurrentLinkedDeque<String> entrustOrderSet = new ConcurrentLinkedDeque<>();
}
