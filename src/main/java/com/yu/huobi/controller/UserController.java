package com.yu.huobi.controller;

import com.alibaba.fastjson.JSONObject;
import com.yu.huobi.pojo.User;
import com.yu.huobi.service.UserService;
import com.yu.huobi.util.MD5Utils;
import com.yu.huobi.util.RedisUtil;
import com.yu.huobi.util.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Yu
 * @create 2019/6/15
 * @since 1.0.0
 */
@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    UserService mUserService;

    @Autowired
    RedisUtil mRedisUtil;


    @ResponseBody
    @PostMapping("/add")
    public Result add(@RequestBody JSONObject object){
        try {
            String phone = object.getString("phone");
            String password = object.getString("password");
            User user = new User();
            user.setName(phone);
            user.setPhone(phone);
            user.setPassword(MD5Utils.md5Encode(password, "utf8"));
            if (mUserService.addUser(user)){
                return new Result(true, "注册成功");
            } else {
                return new Result(false, "用户已存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "注册失败");
        }
    }

    @ResponseBody
    @PostMapping("/login")
    public Result login(@RequestBody JSONObject object, HttpServletRequest request){
        try {
            String phone = object.getString("phone");
            String password = object.getString("password");
            User user = mUserService.findUserByName(phone);
            if (user != null){
                if (MD5Utils.md5Encode(password, "utf8").equals(user.getPassword())){
                    request.getSession().setAttribute("user", user);
                    return new Result(true, phone);
                } else {
                    // 密码匹配失败
                    return new Result(false, "密码错误");
                }
            } else {
                return new Result(false, "用户不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "登录异常");
        }
    }


    @GetMapping("/findSymbolByUser")
    public List<Object> findSymbolByUser(String name){
        Object o = mRedisUtil.get(name);
        String s = String.valueOf(o);
        System.out.println(s);
        return null;
    }

}