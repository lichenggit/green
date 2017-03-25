package cn.cbbhy.schoolshare.logic.websocket;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * Created by duoyi on 17-3-23.
 */
public class WebsocketHandler extends TextWebSocketHandler {
    private Logger logger = LoggerFactory.getLogger(WebsocketHandler.class);
    private static final List<WebSocketSession> webSessions = new ArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session)
            throws Exception {
        // 与客户端完成连接后调用
        System.out.println("ConnectionEstablished");
        logger.debug("ConnectionEstablished");
        webSessions.add(session);
        session.sendMessage(new TextMessage("connect"));
        session.sendMessage(new TextMessage("new_msg"));
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("----" + message.getPayload());
        session.sendMessage(new TextMessage(new Date() + ""));
    }

    @Override
    public void handleTransportError(WebSocketSession session,
                                     Throwable exception) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
        webSessions.remove(session);
        logger.debug("handleTransportError" + exception.getMessage());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session,
                                      CloseStatus closeStatus) throws Exception {
        System.out.println("afterConnectionClosed");
        logger.debug("afterConnectionClosed" + closeStatus.getReason());
    }

    /**
     * 给所有在线用户发送消息
     *
     * @param message
     */
    public void sendMessageToUsers(TextMessage message) throws IOException {
        for (WebSocketSession session:webSessions) {
            System.out.println("当前用户是："+session.getAttributes().get("userId"));
            try {
                if (session.isOpen()) {
                    session.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
