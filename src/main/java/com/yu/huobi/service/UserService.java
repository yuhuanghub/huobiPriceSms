package com.yu.huobi.service;

import com.yu.huobi.pojo.User;

import java.util.List;

/**
 * @Author: Gosin
 * @Date: 2019/6/11 20:32
 */

public interface UserService {

    Boolean addUser(User user);

    Boolean deleteUser(String name, Integer id);

    User findUserByName(String name);

    User findUserById(Integer id);

    List<User> findAllUser();

//    Boolean addSymbol(String name, String symbol);

//    Boolean deleteSymbol(String name, String symbol, boolean all);

//    List<String> findSymbolByUser(User user);

    Boolean updateUser(User user);

//    void openSub(String symbol);
}
