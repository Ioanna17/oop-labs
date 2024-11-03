package com.gildedrose;

class ItemData {
    private final SellIn sellIn;
    private final Quality quality;

    public ItemData(SellIn sellIn, Quality quality) {
        this.sellIn = sellIn;
        this.quality = quality;
    }

    public void decreaseSellIn() {
        sellIn.decrease();
    }

    public void increaseQuality() {
        quality.increase();
    }

    public void decreaseQuality() {
        quality.decrease();
    }

    public void resetQuality() {
        quality.reset();
    }

    public boolean isSellInExpired() {
        return sellIn.isExpired();
    }

    public boolean sellInLessThan(int days) {
        return sellIn.isLessThan(days);
    }

    @Override
    public String toString() {
        return "SellIn: " + sellIn + ", Quality: " + quality;
    }
}
