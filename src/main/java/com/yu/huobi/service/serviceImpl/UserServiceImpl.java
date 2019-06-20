package com.yu.huobi.service.serviceImpl;

import com.huobi.client.SubscriptionClient;
import com.huobi.client.SubscriptionOptions;
import com.yu.huobi.dao.UserMapper;
import com.yu.huobi.pojo.User;
import com.yu.huobi.pojo.UserExample;
import com.yu.huobi.service.UserService;
import com.yu.huobi.util.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Yu
 * @create 2019/6/11
 * @since 1.0.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper mUserMapper;

    @Autowired
    Utils mUtils;

    @Override
    public Boolean addUser(User user) {
        try {
            if (findUserByName(user.getName()) == null){
                if (user.getSymbol() == null){
                    user.setSymbol("btcusdt");
                }
                mUserMapper.insert(user);
            } else {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean deleteUser(String name, Integer id) {
        try {
            if (id != null){
                mUserMapper.deleteByPrimaryKey(id);
                return true;
            } else {
                UserExample example = new UserExample();
                example.createCriteria().andNameEqualTo(name);
                mUserMapper.deleteByExample(example);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public User findUserByName(String name) {
        UserExample example = new UserExample();
        example.createCriteria().andNameEqualTo(name);
        List<User> user = mUserMapper.selectByExample(example);
        if (user.size() == 0){
            return null;
        } else {
            return user.get(0);
        }
    }

    @Override
    public User findUserById(Integer id) {
        UserExample example = new UserExample();
        example.createCriteria().andIdEqualTo(id);
        List<User> user = mUserMapper.selectByExample(example);
        if (user.size() == 0){
            return null;
        } else {
            return user.get(0);
        }
    }

    @Override
    public List<User> findAllUser() {
        return mUserMapper.selectByExample(null);
    }

//    @Override
//    public Boolean deleteSymbol(String name, String symbol, boolean all) {
//
//        boolean flag = false;
//
//        try {
//            User user = findUserByName(name);
//            List<String> symbols = mUtils.parseSymbol(user.getSymbol());
//            if (all){
//                user.setSymbol("");
//                updateUser(user);
//            } else {
//                for (int i = 0; i < symbols.size(); i++) {
//                    if (symbols.get(i).equals(symbol)){
//                        symbols.remove(i);
//                        flag = true;
//                    }
//                }
//                user.setSymbol(mUtils.listToString(symbols, ','));
//                updateUser(user);
//                return flag;
//            }
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }


//    @Override
//    public List<String> findSymbolByUser(User user) {
//        return mUtils.parseSymbol(findUserByName(user.getName()).getSymbol());
//    }

    @Override
    public Boolean updateUser(User user) {
        try {
            mUserMapper.updateByPrimaryKeySelective(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

//    // 开启订阅
//    @Override
//    public void openSub(String symbol) {
//
//        String uri = "wss://api.huobi.br.com";
//        SubscriptionOptions options = new SubscriptionOptions();
//        options.setUri(uri);
//        SubscriptionClient client = SubscriptionClient.create("", "", options);
//    }

}