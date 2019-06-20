package com.yu.huobi.service;

/**
 * @author Yu
 * @create 2019/6/7
 * @since 1.0.0
 */
public interface HuobiService {

//    @Value("${uri.host}")
//    String url;
//
//    public void subscription(String symbol){
//        SubscriptionOptions options = new SubscriptionOptions();
//        options.setUri("wss://api.huobi.pro");
//        SubscriptionClient subscriptionClient = SubscriptionClient.create("", "", options);
//        subscriptionClient.subscribe24HTradeStatisticsEvent(symbol, new SubscriptionListener<TradeStatisticsEvent>() {
//            @Override
//            public void onReceive(TradeStatisticsEvent tradeStatisticsEvent) {
//                System.out.println(tradeStatisticsEvent.getData().getTimestamp());
//            }
//        });
//    }

    void subscription(String symbol, boolean chang);


}