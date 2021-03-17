package com.baseknowledge.iostream;

import java.io.Serializable;

/**
 * @author miclefengzss
 */
public class Goods implements Serializable {

    private String goodId;
    private String goodName;
    private double price;

    public Goods(String goodId, String goodName, double price) {
        this.goodId = goodId;
        this.goodName = goodName;
        this.price = price;
    }

    public String getGoodId() {
        return goodId;
    }

    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "goodId='" + goodId + '\'' +
                ", goodName='" + goodName + '\'' +
                ", price=" + price +
                '}';
    }
}
