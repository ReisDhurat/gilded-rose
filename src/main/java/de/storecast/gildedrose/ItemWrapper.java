package de.storecast.gildedrose;

public abstract class ItemWrapper implements CustomisedItem {

	protected Item item;

	public ItemWrapper(Item item) {
		this.item = item;
		
	}
}
