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
            String itemName = item.getNameString();
            ItemUpdater updater = itemUpdaters.getOrDefault(itemName, itemUpdaters.get("default"));
            updater.update(item);
        }
    }

    public Item[] getItems() {
        return items.clone();
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
        item.decreaseSellIn();
        item.increaseQuality();
        if (item.isExpired()) {
            item.increaseQuality();
        }
    }
}

class BackstagePassUpdater implements ItemUpdater {
    @Override
    public void update(Item item) {
        item.decreaseSellIn();
        item.increaseQuality();

        if (item.sellInLessThan(10)) {
            item.increaseQuality();
        }

        if (item.sellInLessThan(5)) {
            item.increaseQuality();
        }

        if (item.isExpired()) {
            item.resetQuality();
        }
    }
}

class RegularItemUpdater implements ItemUpdater {
    @Override
    public void update(Item item) {
        item.decreaseSellIn();
        item.decreaseQuality();

        if (item.isExpired()) {
            item.decreaseQuality();
        }
    }
}
