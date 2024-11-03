package com.gildedrose;

public class ItemData {
    public SellIn sellIn;
    public Quality quality;

    public ItemData(SellIn sellIn, Quality quality) {
        this.sellIn = sellIn;
        this.quality = quality;
    }

    @Override
    public String toString() {
        return "SellIn: " + sellIn + ", Quality: " + quality;
    }
}
