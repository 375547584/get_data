package com.tecn.market.qts.cache;

import com.corundumstudio.socketio.SocketIOClient;
import com.tecn.market.qts.util.StringUtils;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class SessionCache {
    public static ConcurrentMap<String, SocketIOClient> socketClientMap = new ConcurrentHashMap<>();
    public static void disconnect(String sessionId) {
        socketClientMap.remove(sessionId);
    }

    public static void put(String uuid, SocketIOClient socketIOClient) {
        if(StringUtils.isEmpty(uuid) || socketIOClient == null) {
            return;
        }
        socketClientMap.put(uuid, socketIOClient);
    }

    public static SocketIOClient get(String uuid) {
        if(StringUtils.isEmpty(uuid)) {
            return null;
        }
        return socketClientMap.get(uuid);
    }
}
