package com.gildedrose;

public class BackstagePasses extends InventoryItem {
    public static final String NAME = "Backstage passes to a TAFKAL80ETC concert";

    public BackstagePasses(Item item) {
        super(item);
    }

    @Override
    protected void updateQualityItem() {
        if (item.getSellIn() < 11) {
            increaseQuality();
        }

        if (item.getSellIn() < 6) {
            increaseQuality();
        }
    }

    @Override
    protected void processExpired() {
        item.setQuality(0);
    }
}
