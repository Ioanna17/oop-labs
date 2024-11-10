package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            updateItem(item);
        }
    }

    private void updateItem(Item item) {
        updateQualityItem(item);
        updeteExperation(item);
        if (isExpired(item)) {
        processExpired(item);
        }
    }

    private static boolean isExpired(Item item) {
        return item.sellIn < 0;
    }

    private void updateQualityItem(Item item) {
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
        else if (item.quality > 0) {
            item.quality = item.quality - 1;
        }

    }

    private void updeteExperation(Item item){
        if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
            return;
        }
        item.sellIn--;
    }

    private void processExpired(Item item){
        if (item.name.equals("Aged Brie")) {
            increaseQuality(item);
        } else {
            if (!item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                if (item.quality > 0) {
                    if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                        item.quality = item.quality - 1;
                    }
                }
            } else {
                item.quality = item.quality - item.quality;
            }
        }
    }

    private static void increaseQuality(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;
        }
    }
}
