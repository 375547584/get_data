package com.tecn.market.qts.jobs;

import com.tecn.market.qts.queue.AutoWaitHandleQueue;
import com.tecn.market.qts.queue.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@Configuration
public class MarketJobs {
    /**
     * CRON：0 0 8 ? * MON-FRI
     * 每天早上9-10点每分钟更新一次基本信息
     */
    @Scheduled(cron = "0 0/1 9,10 ? * MON-FRI")
    public void updateCacheJob() {
        AutoWaitHandleQueue.produce(Event.refreshStatic);
    }
}
