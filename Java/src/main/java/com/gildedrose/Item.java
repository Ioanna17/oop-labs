package com.gildedrose;

public class Item {
    public String name;
    public ItemData itemData;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.itemData = new ItemData(new SellIn(sellIn), new Quality(quality));
    }

    @Override
    public String toString() {
        return this.name + ", " + this.itemData;
    }
}
