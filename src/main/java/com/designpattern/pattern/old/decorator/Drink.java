package com.designpattern.pattern.old.decorator;

/**
 * @author miclefengzss
 */
public abstract class Drink {

    private String description;
    private float price;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public abstract float coast();
}
