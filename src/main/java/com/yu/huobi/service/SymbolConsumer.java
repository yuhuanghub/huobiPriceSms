package com.yu.huobi.service;

import com.yu.huobi.pojo.User;
import com.yu.huobi.util.RedisUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Yu
 * @create 2019/6/19
 * @since 1.0.0
 */
@Service
public class SymbolConsumer {

    @Autowired
    RedisUtil mRedisUtil;

    @Autowired
    UserService mUserService;

    private List<String> userSymbols;
    private String mUserPrice;
    private String mPrice;

    @JmsListener(destination = "symbol.queue")
    public void receiveMsg(Map<String, String> map) {

        // 1.查询所有用户的交易对
        List<User> allUser = mUserService.findAllUser();
        for (int i = 0; i < allUser.size(); i++) {
            for (String key : map.keySet()) {
                // 查看用户是否含有该交易对
                mUserPrice = (String)mRedisUtil.hget(allUser.get(i).getPhone(), key);
                if (mRedisUtil.hHasKey(allUser.get(i).getPhone(), key)){
                    // 2. 用户已订阅，判断价格预警
                    // 用户的预警价格
                    mPrice = map.get(key);
                    if (Math.abs(Float.valueOf(mUserPrice).intValue() - Float.valueOf(mPrice).intValue()) < 1.5){
                        // 达到预警价格附近
                        System.out.println(allUser.get(i).getPhone() + "用户的" + key + "达到预警价格 " + "预警价格为:" + mUserPrice);
                        sendSms(allUser.get(i).getPhone(), mUserPrice, mPrice, key);
                        break;
                    }
                } else {
                    break;
                }
            }
        }

    }

    @Async
    public void sendSms(String phone, String userPrice, String price, String symbol){
        // 移除用户交易对预警
        mRedisUtil.hdel(phone, symbol);
        System.out.println("发送短信预警");
    }

}