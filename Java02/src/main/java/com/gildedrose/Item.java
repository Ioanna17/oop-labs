package com.gildedrose;

public class Item {
    public String name;
    private ItemAttributes attributes;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.attributes = new ItemAttributes(sellIn, quality);
    }

    public int getSellIn() {
        return attributes.getSellIn();
    }

    public void setSellIn(int sellIn) {
        attributes.setSellIn(sellIn);
    }

    public int getQuality() {
        return attributes.getQuality();
    }

    public void setQuality(int quality) {
        attributes.setQuality(quality);
    }

    @Override
    public String toString() {
        return name + ", " + attributes.toString();
    }
}
