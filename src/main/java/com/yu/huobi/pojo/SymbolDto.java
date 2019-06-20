package com.yu.huobi.pojo;

/**
 * @author Yu
 * @create 2019/6/15
 * @since 1.0.0
 */
public class SymbolDto {

    private String name;

    private String symbol;

    private String price;

    @Override
    public String toString() {
        return "SymbolDto{" +
                "name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}