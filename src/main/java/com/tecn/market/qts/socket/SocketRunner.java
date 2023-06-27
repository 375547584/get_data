package com.tecn.market.qts.socket;


import ch.qos.logback.core.net.server.ServerRunner;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.tecn.market.qts.cache.SessionCache;
import com.tecn.market.qts.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@Component
public class SocketRunner implements CommandLineRunner {

    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(ServerRunner.class);

    private SocketIOServer socketIOServer;

    @Autowired
    public SocketRunner(SocketIOServer socketIoServer) {
        this.socketIOServer = socketIoServer;
    }

    @Override
    public void run(String... args) {
        logger.info("---------- NettySocket通知服务开始启动 ----------");
        socketIOServer.start();
        StockPush.pushHeartBeat();
        logger.info("---------- NettySocket通知服务启动成功 ----------");
    }

    /**
     * 当客户端发起连接时调用
     *
     * @param socketIOClient
     */
    @OnConnect
    public void onConnect(SocketIOClient socketIOClient) {
        String clientNo = getParamsByClient(socketIOClient);
        String clientIp = getIpByClient(socketIOClient);
        String sessionId = socketIOClient.getSessionId().toString();
        String uuid = String.format("%s_%s",clientNo, clientIp);
        logger.info("连接成功 uuid:{} SessionId：{}" , uuid, socketIOClient.getSessionId().toString());
//        SocketIOClient disSocket = SessionCache.get(uuid);
//        if(disSocket!= null) {
//            String disSocketSessionId = disSocket.getSessionId().toString();
//             if(StringUtils.isNotEmpty(sessionId) && StringUtils.isNotEmpty(disSocketSessionId) && !disSocketSessionId.equals(sessionId))
//            disSocket.disconnect();
//        }
        SessionCache.put(uuid, socketIOClient);
        StockPush.pushHeartBeat();
    }

    /**
     * 客户端断开连接时调用，刷新客户端信息
     *
     * @param socketIOClient
     */
    @OnDisconnect
    public void onDisConnect(SocketIOClient socketIOClient) {
        String clientNo = getParamsByClient(socketIOClient);
        String clientIp = getIpByClient(socketIOClient);
        String uuid = String.format("%s_%s",clientNo, clientIp);
        logger.info("断开连接成功 uuid:{} SessionId：{}" , uuid, socketIOClient.getSessionId().toString());
        SessionCache.disconnect(uuid);
    }

    /**
     * 获取客户端url中的userId参数（这里根据个人需求和客户端对应修改即可）
     *
     * @param client: 客户端
     * @return: java.lang.String
     */
    private String getParamsByClient(SocketIOClient client) {
        // 获取客户端url参数（这里的userId是唯一标识）
        Map<String, List<String>> params = client.getHandshakeData().getUrlParams();
        List<String> userIdList = params.get("clientNo");
        if (!StringUtils.isEmpty(userIdList)) {
            return userIdList.get(0);
        }
        return null;
    }

    /**
     * 获取连接的客户端ip地址
     *
     * @param client: 客户端
     * @return: java.lang.String
     */
    private String getIpByClient(SocketIOClient client) {
        String sa = client.getRemoteAddress().toString();
        String clientIp = sa.substring(1, sa.indexOf(":"));
        return clientIp;
    }
}
