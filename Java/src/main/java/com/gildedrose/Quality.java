package com.gildedrose;

public class Quality {
    private int value;
    private static final int MAX_QUALITY = 50;
    private static final int MIN_QUALITY = 0;

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

    public void decrease() {
        if (value > MIN_QUALITY) {
            value--;
        }
    }

    public void reset() {
        value = MIN_QUALITY;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
