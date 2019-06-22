package org.slj.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slj.web.utils.json.MsgJson;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description: webSocket统筹中转消息
 * @create: 2019/5/25
 * @Author: SLJ
 */
@Component
@ServerEndpoint(value = "/client/{userId}")
public class WebSocketServer {

    /**
     * 日志工具
     */
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    /**
     * 管理员id
     */
    private static final String ADMIN_ID = "0";

    /**
     * 广播的用户id
     */
    private static final String ALL_USER = "all";

    /**
     * 发给管理员的重新渲染的信号
     */
    private static final String RENDER_SIGNAL = "render";

    /**
     * 发给管理员的消息的信号
     */
    private static final String MESSAGE_SIGNAL = "message";

    /**
     * 统计在线人数
     */
    private static int onlineCount = 0;

    /**
     * 存放每个客户端的webSocket对象，根据userId来获取对应的对象
     */
    private static ConcurrentHashMap<String,WebSocketServer> websocketList = new ConcurrentHashMap<>();

    /**
     * 与某个客户端的会话
     */
    private Session session;

    /**
     * 接收的用户id
     */
    private String userId;

    /**
     * 会话中的客端端是否是管理员
     */
    private boolean isAdmin = false;

    /**
     * 会话中的客户端是否是用户
     */
    private boolean isUser = false;

    /**
     * 建立连接
     * @param session 会话
     * @param userId 用户id
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        this.session = session;
        websocketList.put(userId, this);
        addOnlineCount();
        System.out.println("当前在线人数:" + getOnlineCount());
        this.userId = userId;
        if (ADMIN_ID.equals(userId)) {
            this.isAdmin = true;
            System.out.println("管理员上线了");
            rerenderAdminChat();
        }else {
            this.isUser = true;
            System.out.println("用户" + userId + "上线了");
            // 通知管理员有新用户上线，管理员重新渲染聊天室
            if (websocketList.containsKey(ADMIN_ID)) {
                rerenderAdminChat();
            }
        }

    }

    /**
     * 关闭连接
     */
    @OnClose
    public void onClose() {
        if (websocketList.get(this.userId) != null) {
            websocketList.remove(this.userId);
            subOnlineCount();
            if (isAdmin) {
                System.out.println("管理员下线");
            }
            if (isUser) {
                System.out.println("用户" + userId + "下线");
                if (websocketList.containsKey(ADMIN_ID)) {
                    rerenderAdminChat();
                }
            }
        }
    }

    /**
     * 收到客户端的消息
     * @param message 消息内容
     */
    @OnMessage
    public void onMessage(String message) {
        /*
        * 管理员发来消息
        * 管理员的消息为 message + ‘to’ + 要发给的用户的userId
        * */
        if (isAdmin) {
            // 解析消息体
            String userId = message.substring(message.lastIndexOf("to") + 2);
            String messageContent = message.substring(0, message.lastIndexOf("to"));
            try {
                if (ALL_USER.equals(userId)) {
                    sendMessageToAllUser(message);
                }
                sendMessageToOneUser(userId, messageContent);
            } catch (IOException e) {
                System.out.println("给用户发送消息异常：" + e.getMessage());
                e.printStackTrace();
            }
        }

        /*
         * 用户发来消息
         * 用户的消息为 message + ‘ ’ + 自身userId
         * */
        if (isUser) {
            try {
                sendMessageToAdmin(message);
            } catch (IOException e) {
                System.out.println("给管理员发送消息异常：" + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    /**
     * 出现异常
     * @param session 会话
     * @param error 错误
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误：" + error.getMessage());
    }

    /**
     * 给管理员发消息
     */
    private void sendMessageToAdmin(String message) throws IOException {
        if (websocketList.get(ADMIN_ID) != null) {
            Session adminSession = websocketList.get(ADMIN_ID).session;
            MsgJson msgJson = new MsgJson();
            msgJson.setCode(200)
                    .setSuccess(true)
                    .setMsg(MESSAGE_SIGNAL)
                    .setObj(message);
            adminSession.getBasicRemote().sendText(msgJson.toJson());
        }else {
            this.session.getBasicRemote().sendText("管理员当前不在线");
        }
    }

    /**
     * 给用户发消息
     */
    private void sendMessageToOneUser(String userId, String messageContent) throws IOException {
        if (websocketList.get(userId) != null) {
            Session userSession = websocketList.get(userId).session;
            userSession.getBasicRemote().sendText(messageContent);
        }else {
            this.session.getBasicRemote().sendText("用户当前不在线");
        }
    }

    /**
     * 给所有用户发广播
     */
    private void sendMessageToAllUser(String message) throws IOException {
        for (String userId  : websocketList.keySet()) {
            Session curUserSession = websocketList.get(userId).session;
            curUserSession.getBasicRemote().sendText(message);
        }
    }

    /**
     * 当有线上人员变更时，通知管理员重新渲染聊条室
     */
    private void rerenderAdminChat() {
        List<String> otherUserStNums = new ArrayList<>();
        for (String stNum : websocketList.keySet()) {
            if (!stNum.equals(ADMIN_ID)) {
                otherUserStNums.add(stNum);
            }
        }
        MsgJson msgJson = new MsgJson();
        msgJson.setCode(200)
                .setSuccess(true)
                .setMsg(RENDER_SIGNAL)
                .setObj(otherUserStNums);
        try {
            if (websocketList.get(ADMIN_ID) != null) {
                websocketList.get(ADMIN_ID).session.getBasicRemote().sendText(msgJson.toJson());
            }
        } catch (IOException e) {
            System.out.println("给管理员发送消息异常：" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 线程安全得到当前在线人数
     *
     * @return 当前在线人数
     */
    private static synchronized int getOnlineCount() {
        return onlineCount;
    }

    /**
     * 线程安全在线人数-1
     */
    private static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    /**
     * 线程安全在线人数+1
     */
    private static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
}
