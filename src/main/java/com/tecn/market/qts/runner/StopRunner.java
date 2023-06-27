package com.tecn.market.qts.runner;

import com.tecn.market.qts.GTAQTSManage;
import com.tecn.market.qts.async.AsyncManager;
import com.tecn.market.qts.queue.AutoWaitHandleQueue;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

@Component
public class StopRunner implements DisposableBean {

    @Override
    public void destroy() {
        System.out.println("----------SpringBoot停止前释放资源-------------");

        AutoWaitHandleQueue.release();

        AsyncManager.me().shutdown();

        GTAQTSManage.release();
    }
}
