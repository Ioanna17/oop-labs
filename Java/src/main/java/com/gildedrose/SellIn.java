package com.gildedrose;

class SellIn {
    private int value;

    public SellIn(int value) {
        this.value = value;
    }

    public void decrease() {
        value--;
    }

    public boolean isExpired() {
        return value < 0;
    }

    public boolean isLessThan(int days) {
        return value < days;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
