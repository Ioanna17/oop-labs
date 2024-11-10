package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            InventoryItem InventoryItem = new InventoryItem(item);
            updateItem(item, InventoryItem);
        }
    }

    private void updateItem(Item item, InventoryItem inventoryItem) {
        inventoryItem.updateQualityItem(item);
        inventoryItem.updeteExperation(item);
        if (inventoryItem.isExpired(item)) {
        inventoryItem.processExpired(item);
        }
    }

}
