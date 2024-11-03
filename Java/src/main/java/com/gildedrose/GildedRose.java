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
            ItemUpdater defaultUpdater = itemUpdaters.get("default");
            ItemUpdater updater = itemUpdaters.getOrDefault(item.name, defaultUpdater);
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
        item.quality.increase();
        item.sellIn.decrease();
        if (item.sellIn.isExpired()) {
            item.quality.increase();
        }
    }
}

class BackstagePassUpdater implements ItemUpdater {
    @Override
    public void update(Item item) {
        item.quality.increase();
        if (item.sellIn.isLessThan(11)) {
            item.quality.increase();
        }
        if (item.sellIn.isLessThan(6)) {
            item.quality.increase();
        }
        item.sellIn.decrease();
        if (item.sellIn.isExpired()) {
            item.quality.reset();
        }
    }
}

class RegularItemUpdater implements ItemUpdater {
    @Override
    public void update(Item item) {
        item.quality.decrease();
        item.sellIn.decrease();
        if (item.sellIn.isExpired()) {
            item.quality.decrease();
        }
    }
}
