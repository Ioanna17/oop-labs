package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (isLegendary(item)) {
                continue;
            }
            if (isAgedBrie(item)) {
                updateAgedBrie(item);
                continue;
            }
            if (isBackstagePass(item)) {
                updateBackstagePass(item);
                continue;
            }
            updateRegularItem(item);
        }
    }

    private boolean isLegendary(Item item) {
        return item.name.equals("Sulfuras, Hand of Ragnaros");
    }

    private boolean isAgedBrie(Item item) {
        return item.name.equals("Aged Brie");
    }

    private boolean isBackstagePass(Item item) {
        return item.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }

    private void updateAgedBrie(Item item) {
        if (item.quality.getValue() < 50) {
            item.quality.increase();
        }
        item.sellIn.decrease();
        if (item.sellIn.isExpired() && item.quality.getValue() < 50) {
            item.quality.increase();
        }
    }

    private void updateBackstagePass(Item item) {
        if (item.quality.getValue() < 50) {
            item.quality.increase();
            if (item.sellIn.isLessThan(11) && item.quality.getValue() < 50) {
                item.quality.increase();
            }
            if (item.sellIn.isLessThan(6) && item.quality.getValue() < 50) {
                item.quality.increase();
            }
        }
        item.sellIn.decrease();
        if (item.sellIn.isExpired()) {
            item.quality.reset();
        }
    }

    private void updateRegularItem(Item item) {
        if (item.quality.getValue() > 0) {
            item.quality.decrease();
        }
        item.sellIn.decrease();
        if (item.sellIn.isExpired() && item.quality.getValue() > 0) {
            item.quality.decrease();
        }
    }
}
