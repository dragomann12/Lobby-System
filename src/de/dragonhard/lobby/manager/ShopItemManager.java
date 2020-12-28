package de.dragonhard.lobby.manager;

import de.dragonhard.lobby.reader.ShopItemReader;

public class ShopItemManager extends ShopItemReader {

    public void addItemToShop(String shop, String category, String description, String name, String tag, String flag, int slotID, int price){

        this.setFile(getName());
        this.set("Shops."+shop+"."+category+".Items.Item_"+tag+".Name",name);
        this.set("Shops."+shop+"."+category+".Items.Item_"+tag+".Flag",flag);
        this.set("Shops."+shop+"."+category+".Items.Item_"+tag+".Description",description);
        this.set("Shops."+shop+"."+category+".Items.Item_"+tag+".SlotID",slotID);
        this.set("Shops."+shop+"."+category+".Items.Item_"+tag+".Price",price);

    }

    public String getDisplayNameOfItem(String shop, String category, String tag){
        this.setFile(getName());
        return getString("Shops."+shop+"."+category+".Items.Item_"+tag+".Name");
    }

    public String getDescriptionOfItem(String shop, String category, String tag){
        this.setFile(getName());
        return getString("Shops."+shop+"."+category+".Items.Item_"+tag+".Description");
    }

    public int getPriceOfItem(String shop, String category, String tag){
        this.setFile(getName());
        return getInteger("Shops."+shop+"."+category+".Items.Item_"+tag+".Price");
    }

    public String getFlagOfItem(String shop, String category, String tag){
        this.setFile(getName());
        return getString("Shops."+shop+"."+category+".Items.Item_"+tag+".Flag");
    }

    public int getSlotIDOfItem(String shop, String category, String tag){
        this.setFile(getName());
        return getInteger("Shops."+shop+"."+category+".Items.Item_"+tag+".SlotID");
    }

    private String getName(){
        return "Shop_Item_List";
    }

}
