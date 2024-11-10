package com.gildedrose;

public class InventoryItem {

    protected Item item;

    private static final String getBackstagePasses = "Backstage passes to a TAFKAL80ETC concert";

    private static final String getAgedBrie = "Aged Brie";

    public InventoryItem(Item item){
        this.item=item;
    }

    public static InventoryItem create(Item item) {
        if (item.name.equals(getAgedBrie)){
            return new AgedBrie(item);
        }
        if (item.name.equals(getBackstagePasses)){
            return new BackstagePasses(item);
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
    if (item.name.equals("Sulfuras, Hand of Ragnaros")) {
        return;
    }
    else decreaseQuality();
    }

    protected boolean isExpired() {
        return item.sellIn < 0;
    }

    protected void processExpired() {
    if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
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

