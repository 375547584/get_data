package com.tecn.market;

import com.tecn.market.qts.GTAQTSManage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.IOException;

@Slf4j
@EnableTransactionManagement
@SpringBootApplication
@EnableScheduling
public class QTSServerApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        try {
            GTAQTSManage.init();
            log.error("初始化 GTAQTSManage 成功");
        } catch (IOException e) {
            log.error("初始化 GTAQTSManage 失败", e);
        }
        SpringApplication.run(QTSServerApplication.class);
    }
}
