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
        if (item.getQuality() < 50) {
            item.setQuality(item.getQuality() + 1);
        }
    }

    protected void decreaseQuality() {
        if (item.getQuality() > 0) {
            item.setQuality(item.getQuality() - 1);
        }
    }

    protected void updeteExperation(){
        item.setSellIn(item.getSellIn() - 1);
    }

    protected void updateQualityItem() {
    decreaseQuality();
    }

    protected boolean isExpired() {
        return item.getSellIn() < 0;
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

