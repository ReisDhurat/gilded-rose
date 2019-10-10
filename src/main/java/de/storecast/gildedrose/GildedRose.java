package de.storecast.gildedrose;

import java.util.ArrayList;
import java.util.List;

class GildedRose {

    private static final int LOWEST_QUALITY_VALUE_POSSIBLE = 0;
    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASSES_TO_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final String CONJURED = "Conjured";
    
    public static final int MAX_QUALITY = 50;
    public static final int SULFURAS_QUALITY = 80;

    
    
    private CustomisedItemFactory customisedItemFactory = new CustomisedItemFactory(); 
    Item[] items;
    List<CustomisedItem> customizedItems = new ArrayList<>();

    public GildedRose(Item[] items) {
        this.items = items;
        for (int i = 0; i < items.length; i++) {
			customizedItems.add(customisedItemFactory.newCutomizedItem(items[i]));
		}
    }

    public Item[] getItems() {
        return items;
    }

    public void updateQuality() {
        for (CustomisedItem customizedItem : customizedItems) {
        	customizedItem.updateState();
            if (hasReachedLowestQualityValue(customizedItem.getItem())) {
                customizedItem.getItem().quality = LOWEST_QUALITY_VALUE_POSSIBLE;
            } else if (hasReachedHighestQualityValue(customizedItem.getItem())) {
            	customizedItem.getItem().quality = QualityValues.highestValuePossible(customizedItem.getItem());
            }
        }
    }

    private boolean hasReachedLowestQualityValue(Item item) {
        return item.quality < LOWEST_QUALITY_VALUE_POSSIBLE;
    }

    private boolean hasReachedHighestQualityValue(Item item) {
        return item.quality > QualityValues.highestValuePossible(item);
    }
}