
package com.tecn.market.qts.queue;

import com.tecn.market.qts.GTAQTSManage;
import com.tecn.market.qts.entity.StockStatic;
import com.tecn.market.qts.socket.StockPush;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.*;

@Slf4j
@Component
public class AutoWaitHandleQueue {
    private static BlockingQueue<String> QUEUE = new LinkedBlockingQueue<>();
    private static ScheduledExecutorService executors;


    @PostConstruct
    public void init() {
        if(executors == null) {
            executors = Executors.newScheduledThreadPool(1);
        }
        if(QUEUE == null) {
            QUEUE = new LinkedBlockingQueue<>();
        }
        AutoWaitHandleQueue.executors.scheduleAtFixedRate(() -> {
            try {
                String queueEvent = AutoWaitHandleQueue.QUEUE.take();
                log.info("更新开始: {}", queueEvent);
                if (Event.qtsReLogin.equals(queueEvent)) {
                    GTAQTSManage.login();
                }
                else if (Event.refreshStatic.equals(queueEvent)) {
                    List<StockStatic> stockStatics =  GTAQTSManage.getSSEL2Static(null);
                    StockPush.pushStockStatic(stockStatics);
                    stockStatics =  GTAQTSManage.getSZSEL2Static(null);
                    StockPush.pushStockStatic(stockStatics);
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                e.printStackTrace();
            }
        }, 60*1000, 5000, TimeUnit.MILLISECONDS);
    }

    public static void produce(String queueEvent) {
        if(!QUEUE.contains(queueEvent)) {
            QUEUE.add(queueEvent);
        }
    }

    public static void release() {
        executors.shutdown();
        executors = null;
    }
}
