package com.gildedrose;

public class Item {
    public String name;
    public SellIn sellIn;
    public Quality quality;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = new SellIn(sellIn);
        this.quality = new Quality(quality);
    }

    @Override
    public String toString() {
        return this.name + ", " + this.sellIn.getValue() + ", " + this.quality.getValue();
    }
}
