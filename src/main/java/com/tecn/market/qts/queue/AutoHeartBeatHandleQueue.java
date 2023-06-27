
package com.tecn.market.qts.queue;

import com.tecn.market.qts.socket.StockPush;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class AutoHeartBeatHandleQueue {

    private static ScheduledExecutorService executors;
    @PostConstruct
    public void init() {
        if(executors == null) {
            executors = Executors.newScheduledThreadPool(1);
        }

        AutoHeartBeatHandleQueue.executors.scheduleAtFixedRate(() -> {
            StockPush.pushHeartBeat();
        }, 100, 1000, TimeUnit.MILLISECONDS);
    }

    public static void release() {
        executors.shutdown();
        executors = null;
    }
}
