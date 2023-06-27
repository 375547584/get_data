package com.tecn.market.qts.socket;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.tecn.market.qts.cache.SocketEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SubscribeHandler {

    /**
     * 合约列表
     * @param client
     * @param request
     */
    @OnEvent("/indexQuotation")
    public void indexQuotation(SocketIOClient client, AckRequest request) {
        SocketEvent.stockStaticSet.add(client.getSessionId().toString());
    }
}
