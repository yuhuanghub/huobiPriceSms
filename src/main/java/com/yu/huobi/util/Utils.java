package com.yu.huobi.util;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @author Yu
 * @create 2019/6/13
 * @since 1.0.0
 */
@Component
public class Utils {

    public ArrayList<String> parseSymbol(String symbol){
        List<String> list = Arrays.asList(symbol.split(","));
        return new ArrayList(list);
    }

    public String listToString(List list, char separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)).append(separator);
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }

    public boolean equals(Set<?> set1, Set<?> set2){
        if(set1 == null || set2 ==null){
            return false;
        }
        if(set1.size()!=set2.size()){
            return false;
        }
        return set1.containsAll(set2);
    }
}