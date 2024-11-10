package com.gildedrose;

public class InventoryItem {

    private Item item;

    public InventoryItem(Item item){
        this.item=item;
    }

    protected void increaseQuality(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;
        }
    }

    protected void decreaseQuality(Item item) {
        if (item.quality > 0) {
            item.quality = item.quality - 1;
        }
    }

    protected void updeteExperation(Item item){
        if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
            return;
        }
        item.sellIn--;
    }

    protected void updateQualityItem(Item item) {
        if (item.name.equals("Aged Brie")){
            increaseQuality(item);
        }
        else if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")){
            increaseQuality(item);
            if (item.sellIn < 11) {
                increaseQuality(item);
            }

            if (item.sellIn < 6) {
                increaseQuality(item);
            }
        } else if (item.name.equals("Sulfuras, Hand of Ragnaros")) {
                return;
        }
        else decreaseQuality(item);

    }

    protected boolean isExpired(Item item) {
        return item.sellIn < 0;
    }

    protected void processExpired(Item item) {
        if (item.name.equals("Aged Brie")) {
            increaseQuality(item);
        } else if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            item.quality = 0;
        } else if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
            return;
        } else {
            decreaseQuality(item);
        }
    }

    public void dailyUpdate(Item item) {
        updateQualityItem(item);
        updeteExperation(item);
        if (isExpired(item)) {
        processExpired(item);
        }
    }
}

