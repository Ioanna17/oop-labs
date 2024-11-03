package com.gildedrose;

public class SellIn {
    private int value;

    public SellIn(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void decrease() {
        value--;
    }

    public boolean isExpired() {
        return value < 0;
    }
}
