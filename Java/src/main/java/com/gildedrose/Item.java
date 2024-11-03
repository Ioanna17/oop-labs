package com.gildedrose;

class Item {
    private final ItemName name;
    private final ItemData itemData;

    public Item(String name, int sellIn, int quality) {
        this.name = new ItemName(name);
        this.itemData = new ItemData(new SellIn(sellIn), new Quality(quality));
    }

    public String getNameString() {
        return name.getValue();
    }

    public void decreaseSellIn() {
        itemData.decreaseSellIn();
    }

    public void increaseQuality() {
        itemData.increaseQuality();
    }

    public void decreaseQuality() {
        itemData.decreaseQuality();
    }

    public void resetQuality() {
        itemData.resetQuality();
    }

    public boolean isExpired() {
        return itemData.isSellInExpired();
    }

    public boolean sellInLessThan(int days) {
        return itemData.sellInLessThan(days);
    }

    @Override
    public String toString() {
        return name + ", " + itemData;
    }

    public String getName() {
        return name.getValue();
    }
}

class ItemName {
    private final String value;

    public ItemName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
