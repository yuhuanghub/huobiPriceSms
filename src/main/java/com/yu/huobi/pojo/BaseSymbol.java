package com.yu.huobi.pojo;

import java.io.Serializable;

/**
 * @author Yu
 * @create 2019/6/16
 * @since 1.0.0
 */
public class BaseSymbol implements Serializable {

    String symbol;

    String currentPrice;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(String currentPrice) {
        this.currentPrice = currentPrice;
    }

    @Override
    public String toString() {
        return "BaseSymbol{" +
                "symbol='" + symbol + '\'' +
                ", currentPrice='" + currentPrice + '\'' +
                '}';
    }
}