package com.gildedrose;

public class AgedBrie extends InventoryItem {
    public static final String NAME = "Aged Brie";

    public AgedBrie(Item item) {
        super(item);
    }

    @Override
    protected void updateQualityItem() {
    increaseQuality();
    }

    @Override
    protected void processExpired() {
        increaseQuality();
    }
}
