package com.gildedrose;

public class BackstagePasses extends InventoryItem {
    public BackstagePasses(Item item) {
        super(item);
    }

    @Override
    protected void updateQualityItem() {
        if (item.sellIn < 11) {
            increaseQuality();
        }

        if (item.sellIn < 6) {
            increaseQuality();
        }
    }

    @Override
    protected void processExpired() {
        item.quality = 0;
    }
}
