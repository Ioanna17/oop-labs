package com.gildedrose;

public class InventoryItem {

    private Item item;

    public InventoryItem(Item item){
        this.item=item;
    }

    public static InventoryItem create(Item item) {
        if (item.name.equals(getAgedBrie)){
            return new AgedBrie(item);
        }
        return new InventoryItem(item);
    }

    protected void increaseQuality() {
        if (item.quality < 50) {
            item.quality = item.quality + 1;
        }
    }

    protected void decreaseQuality() {
        if (item.quality > 0) {
            item.quality = item.quality - 1;
        }
    }

    protected void updeteExperation(){
        if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
            return;
        }
        item.sellIn--;
    }

    protected void updateQualityItem() {
    if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")){
        increaseQuality();
        if (item.sellIn < 11) {
            increaseQuality();
        }

        if (item.sellIn < 6) {
            increaseQuality();
        }
    } else if (item.name.equals("Sulfuras, Hand of Ragnaros")) {
        return;
    }
    else decreaseQuality();
    }

    private static final String getAgedBrie ="Aged Brie";

    protected boolean isExpired() {
        return item.sellIn < 0;
    }

    protected void processExpired() {
    if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
        item.quality = 0;
    } else if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
        return;
    } else {
        decreaseQuality();
    }
    }

    public void dailyUpdate() {
        updateQualityItem();
        updeteExperation();
        if (isExpired()) {
        processExpired();
        }
    }
}

