package com.tecn.market.qts.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class QTSConfig {

    @Value("${spring.profiles.active}")
    public String env;

    @Value("${qts.holidays}")
    public String holidays;
}
