package com.gildedrose;

public class Quality {
    private int value;
    private static final int MAX_QUALITY = 80;
    private static final int MIN_QUALITY = 0;

    public Quality(int value) {
        this.value = Math.min(value, MAX_QUALITY);
    }

    public boolean isMax() {
        return value >= MAX_QUALITY;
    }

    public boolean isMin() {
        return value <= MIN_QUALITY;
    }

    public void increase() {
        if (!isMax()) {
            value++;
        }
    }

    public void decrease() {
        if (!isMin()) {
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
