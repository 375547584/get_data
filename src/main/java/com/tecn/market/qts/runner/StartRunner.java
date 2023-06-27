package com.tecn.market.qts.runner;

import com.tecn.market.qts.GTAQTSManage;
import com.tecn.market.qts.async.AsyncManager;
import com.tecn.market.qts.config.QTSConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.TimerTask;

@Component
public class StartRunner implements ApplicationRunner {

    @Autowired
    protected QTSConfig qtsConfig;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("----------SpringBoot启动完成之后再做一些自己的业务-------------");
        try {
            if(qtsConfig.env.equals("pro")) {
                GTAQTSManage.setIsPro(true);
                System.out.println("----------正式账号-------------");
            }
            else {
                GTAQTSManage.setIsPro(false);
                System.out.println("----------测试账号-------------");
            }
        }
        catch (Exception e) {
            System.out.println("----------启动异常-------------");
        }


        AsyncManager.me().execute(new TimerTask() {
            @Override
            public void run() {
                GTAQTSManage.login();
            }
        });
    }
}
