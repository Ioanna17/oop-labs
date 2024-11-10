package com.gildedrose;

public class InventoryItem {

    protected Item item;

    public InventoryItem(Item item){
        this.item=item;
    }

    public static InventoryItem create(Item item) {
        if (item.name.equals(AgedBrie.NAME)){
            return new AgedBrie(item);
        }
        if (item.name.equals(BackstagePasses.NAME)){
            return new BackstagePasses(item);
        }
        if (item.name.equals(Sulfuras.NAME)){
            return new Sulfuras(item);
        }
        return new InventoryItem(item);
    }

    protected void increaseQuality() {
        if (item.quality < 50) {
            item.quality++;
        }
    }

    protected void decreaseQuality() {
        if (item.quality > 0) {
            item.quality--;
        }
    }

    protected void updeteExperation(){
        item.sellIn--;
    }

    protected void updateQualityItem() {
    decreaseQuality();
    }

    protected boolean isExpired() {
        return item.sellIn < 0;
    }

    protected void processExpired() {
    decreaseQuality();
    }

    public void dailyUpdate() {
        updateQualityItem();
        updeteExperation();
        if (isExpired()) {
        processExpired();
        }
    }
}

