package com.yu.huobi.service;

import com.alibaba.fastjson.JSONObject;
import com.huobi.client.SubscriptionClient;
import com.huobi.client.SubscriptionOptions;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * @author Yu
 * @create 2019/6/16
 * @since 1.0.0
 */
@Component
@ServerEndpoint(value = "/person/mainSocket")
public class SocketService {

    String uri = "wss://api.huobi.br.com";

    private Session session;
    private static CopyOnWriteArraySet<SocketService> webSocketSet = new CopyOnWriteArraySet<SocketService>();
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        //加入set中
        webSocketSet.add(this);
        HashMap<String, String> map = new HashMap<>();
        ArrayList<HashMap> list = new ArrayList<>();
        SubscriptionOptions options = new SubscriptionOptions();
        options.setUri(uri);
        SubscriptionClient client = SubscriptionClient.create("", "", options);
        client.subscribeTradeEvent("btcusdt,ltcusdt,eosusdt,ethusdt", (result) -> {
            try {
                BigDecimal price = result.getTradeList().get(0).getPrice();
                map.put(result.getSymbol(), String.format("%.2f", price));
                list.add(map);
                sendMessage(JSONObject.toJSONString(list));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    @OnError
    public void onError(Session session, Throwable error) {

    }

    @OnClose
    public void onClose(){
        webSocketSet.remove(this);
    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    public static void sendInfo(String message) throws IOException {
        for (SocketService item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                continue;
            }
        }
    }


}