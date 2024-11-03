package com.gildedrose;

import java.util.HashMap;
import java.util.Map;

class GildedRose {
    Item[] items;
    private final Map<String, ItemUpdater> itemUpdaters;

    public GildedRose(Item[] items) {
        this.items = items;
        this.itemUpdaters = new HashMap<>();
        initializeItemUpdaters();
    }

    private void initializeItemUpdaters() {
        itemUpdaters.put("Sulfuras, Hand of Ragnaros", new LegendaryItemUpdater());
        itemUpdaters.put("Aged Brie", new AgedBrieUpdater());
        itemUpdaters.put("Backstage passes to a TAFKAL80ETC concert", new BackstagePassUpdater());
        itemUpdaters.put("default", new RegularItemUpdater());
    }

    public void updateQuality() {
        for (Item item : items) {
            String itemName = item.name.toString();
            ItemUpdater updater = itemUpdaters.getOrDefault(itemName, itemUpdaters.get("default"));
            updater.update(item);
        }
    }
}

interface ItemUpdater {
    void update(Item item);
}

class LegendaryItemUpdater implements ItemUpdater {
    @Override
    public void update(Item item) {
        // Legendary items do not change
    }
}

class AgedBrieUpdater implements ItemUpdater {
    @Override
    public void update(Item item) {
        item.sellIn.decrease();
        item.quality.increase();
        if (item.sellIn.isExpired()) {
            item.quality.increase();
        }
    }
}

class BackstagePassUpdater implements ItemUpdater {
    @Override
    public void update(Item item) {
        item.sellIn.decrease();
        item.quality.increase();
        if (item.sellIn.isLessThan(10)) {
            item.quality.increase();
        }
        if (item.sellIn.isLessThan(5)) {
            item.quality.increase();
        }
        if (item.sellIn.isExpired()) {
            item.quality.reset();
        }
    }
}

class RegularItemUpdater implements ItemUpdater {
    @Override
    public void update(Item item) {
        item.sellIn.decrease();
        item.quality.decrease();
        if (item.sellIn.isExpired()) {
            item.quality.decrease();
        }
    }
}
