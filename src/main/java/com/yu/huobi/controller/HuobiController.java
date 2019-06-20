package com.yu.huobi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yu
 * @create 2019/6/7
 * @since 1.0.0
 */
@RestController
@RequestMapping("/person/huobi")
public class HuobiController {


//
//    @Autowired
//    HuobiService mHuobiService;
//
//    @GetMapping("test")
//    public String test(){
//        SubscriptionOptions options = new SubscriptionOptions();
//        options.setUri("wss://api.huobi.br.com");
//        SubscriptionClient subscriptionClient = SubscriptionClient.create("", "", options);
//        subscriptionClient.subscribe24HTradeStatisticsEvent("ltcusdt,btcusdt", (statisticsEvent) -> {
//            System.out.println();
//            System.out.println("Timestamp: " + statisticsEvent.getData().getTimestamp());
//            System.out.println("High: " + statisticsEvent.getData().getHigh());
//            System.out.println("Low: " + statisticsEvent.getData().getLow());
//            System.out.println("Open: " + statisticsEvent.getData().getOpen());
//            System.out.println("Close: " + statisticsEvent.getData().getClose());
//            System.out.println("Volume: " + statisticsEvent.getData().getVolume());
//        });
//
//        return "ok";
//    }

}