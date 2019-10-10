package de.storecast.gildedrose;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class CustomisedItemFactory {

	private final static Map<String, CustomisedItem> ITEM_TYPES_LIST = new HashMap<>();
    public final static String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public final static String BRIE = "Aged Brie";
    public final static String BACKSTAGE_PASSES_ITEM = "Backstage passes to a TAFKAL80ETC concert";
    public final static String CONJURED_ITEM = "Conjured";

    @SuppressWarnings("rawtypes")
	private Map<String, Class> itemTypes = new HashMap<>();
    
    public CustomisedItemFactory() {
    	itemTypes.put(SULFURAS, Sulfuras.class);
    	itemTypes.put(BRIE, AgedBrie.class);
    	itemTypes.put(BACKSTAGE_PASSES_ITEM, BackstagePassesItem.class);
    	itemTypes.put(CONJURED_ITEM, ConjuredItem.class);
    }

    @SuppressWarnings("unchecked")
	public CustomisedItem newCutomizedItem(Item item) {
        if (isStandardItem(item)) {
            return new StandardItem(item);
        }
        CustomisedItem customizedItem = null;
        try {
			customizedItem = (CustomisedItem)itemTypes.get(item.name).getConstructor(Item.class).newInstance(item);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return customizedItem;
    }
    
    private boolean isStandardItem(Item item) {
        return !ITEM_TYPES_LIST.keySet().contains(item.name);
    }
}
