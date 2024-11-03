package com.gildedrose;

public class Quality {
    private int value;
    private static final int MAX_QUALITY = 50;

    public Quality(int value) {
        this.value = Math.min(value, MAX_QUALITY);
    }

    public int getValue() {
        return value;
    }

    public void increase() {
        if (value < MAX_QUALITY) {
            value++;
        }
    }
}
