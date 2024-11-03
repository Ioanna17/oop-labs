package com.gildedrose;

class GildedRose {
    private final ItemCollection itemCollection;

    public GildedRose(Item[] items) {
        this.itemCollection = new ItemCollection(items);
    }

    public void updateQuality() {
        itemCollection.updateAllItems();
    }

    private static class ItemCollection {
        private final Item[] items;

        public ItemCollection(Item[] items) {
            this.items = items;
        }

        public void updateAllItems() {
            for (Item item : items) {
//                item.updateQuality();
            }
        }
    }
}
