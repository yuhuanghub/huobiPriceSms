package com.yu.huobi.controller;

import com.yu.huobi.pojo.SymbolDto;
import com.yu.huobi.pojo.User;
import com.yu.huobi.service.UserService;
import com.yu.huobi.util.RedisUtil;
import com.yu.huobi.util.Result;
import com.yu.huobi.util.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import javax.servlet.http.HttpSession;

/**
 * @author Yu
 * @create 2019/6/15
 * @since 1.0.0
 */

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    RedisUtil mRedisUtil;

    @Autowired
    UserService mUserService;

    @Autowired
    Utils mUtils;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;


    /**
     * 将交易对和用户名放入redis
     * 将用户名和对应的预警价格放入redis
     * @param symbolDto
     * @return
     */
    @PostMapping("/addSymbol")
    public boolean addSymbol(@RequestBody SymbolDto symbolDto){
        try {
            mRedisUtil.hset(symbolDto.getName(), symbolDto.getSymbol(), symbolDto.getPrice());
            mRedisUtil.lSet(symbolDto.getSymbol(), symbolDto.getName());
            mRedisUtil.sSet("subSymbols", symbolDto.getSymbol());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @GetMapping("/initUser")
    public User initUser(HttpSession session){
        User user = (User) session.getAttribute("user");
        return mUserService.findUserByName(user.getName());
    }

    @GetMapping("/findSymbolByUser")
    public Map<Object, Object> findSymbolByUser(String name){
        Map<Object, Object> map = mRedisUtil.hmget(name);
        return map;
    }

    @GetMapping("/deleteSymbolByUser")
    public Result deleteSymbolByUser(String name, String symbol){
        try {
            redisTemplate.opsForHash().delete(name, symbol);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }

    @GetMapping("/logout")
    public Result logout(HttpSession session){
        session.invalidate();
        return new Result(true, "");
    }
}