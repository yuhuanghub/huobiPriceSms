package com.yu.huobi.service;

import com.yu.huobi.util.RedisUtil;
import com.yu.huobi.util.Utils;

import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Set;

/**
 * @author Yu
 * @create 2019/6/18
 * @since 1.0.0
 */
@Component
public class ScheduleTask {

    @Autowired
    RedisUtil mRedisUtil;

    @Autowired
    HuobiService mHuobiService;

    @Autowired
    SymbolProducer mSymbolProducer;

    @Autowired
    Utils mUtils;


    private static Set<Object> symbols = null;
    private static Set<Object> mSymbolsFormRedis;
    private static String[] mStrings;
    private HashMap<String, String> mMap;


    @Async
    @Scheduled(fixedDelay = 3000)
    public void subSymbol(){
        mSymbolsFormRedis = mRedisUtil.sGet("subSymbols");
        if (!mUtils.equals(symbols, mSymbolsFormRedis)){
            symbols = mSymbolsFormRedis;
            mStrings = symbols.stream().toArray(String[]::new);
            String symbol = StringUtils.join(mStrings);
            mHuobiService.subscription(symbol, true);
        }
    }

    @Async
    @Scheduled(fixedDelay = 2000)
    public void sendSymbol(){
        mMap = new HashMap<String, String>();
        if (mStrings != null){
            for (int i = 0; i < mStrings.length; i++) {
                String price = (String)mRedisUtil.hget("symbolMsg", mStrings[i]);
                mMap.put(mStrings[i], price);
            }
            mSymbolProducer.sendMsg("symbol.queue", mMap);
        }
    }

}