package com.yu.huobi.service.serviceImpl;

import com.huobi.client.SubscriptionClient;
import com.huobi.client.SubscriptionOptions;
import com.yu.huobi.service.HuobiService;
import com.yu.huobi.util.RedisUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Yu
 * @create 2019/6/16
 * @since 1.0.0
 */
@Component
public class HuobiServiceImpl implements HuobiService {

    @Autowired
    RedisUtil mRedisUtil;

    private SubscriptionOptions mOptions;
    private SubscriptionClient mClient;

    public HuobiServiceImpl() {
        mOptions = new SubscriptionOptions();
        mOptions.setUri("wss://api.huobi.br.com");
        mClient = SubscriptionClient.create("", "", mOptions);
    }

    @Override
    public void subscription(String symbol, boolean chang) {
        if (chang){
            mClient.unsubscribeAll();
            mClient.subscribeTradeEvent(symbol, (result) -> {
                mRedisUtil.hset("symbolMsg", result.getSymbol(), result.getTradeList().get(0).getPrice().toString());
            });
        } else {
            mClient.subscribeTradeEvent(symbol, (result) -> {
                mRedisUtil.hset("symbolMsg", result.getSymbol(), result.getTradeList().get(0).getPrice().toString());
            });
        }
    }

}