package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (!item.name.equals("Aged Brie") && !item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                if (item.quality.getValue() > 0) {
                    if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                        item.quality.decrease();
                    }
                }
            } else {
                if (item.quality.getValue() < 50) {
                    item.quality.increase();

                    if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (item.sellIn.isLessThan(11)) {
                            item.quality.increase();
                        }

                        if (item.sellIn.isLessThan(6)) {
                            item.quality.increase();
                        }
                    }
                }
            }

            if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                item.sellIn.decrease();
            }

            if (item.sellIn.isExpired()) {
                if (!item.name.equals("Aged Brie")) {
                    if (!item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (item.quality.getValue() > 0) {
                            if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                                item.quality.decrease();
                            }
                        }
                    } else {
                        item.quality.reset();
                    }
                } else {
                    if (item.quality.getValue() < 50) {
                        item.quality.increase();
                    }
                }
            }
        }
    }
}
