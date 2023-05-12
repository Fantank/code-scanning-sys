package com.fantank.handler;


import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;


import java.io.IOException;
import java.util.HashSet;

@Component
public class MyWebSocketHandler implements WebSocketHandler {
    public static HashSet<WebSocketSession> websocketSessions;

    static {
        websocketSessions = new HashSet<>();
    }

    public static void sendMessageToAll(TextMessage message) throws IOException {
        for (WebSocketSession session : websocketSessions) {
            System.out.println("Message updating");
            if (session.isOpen()) {
                System.out.println("sent to client "+session.getId());
                session.sendMessage(message);
            } else {
                websocketSessions.remove(session);
            }
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        System.out.println("A client connected~ " + webSocketSession.getUri());
        websocketSessions.add(webSocketSession);

    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        if (webSocketMessage.getPayloadLength() == 0) return;

        //sendMessageToAll(new TextMessage(new GsonBuilder().create().toJson(webSocketMessage)));
    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {

    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        System.out.println("A client disconnected " + webSocketSession.getUri());
        websocketSessions.remove(webSocketSession);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
