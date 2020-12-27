package de.dragonhard.lobby.components.menu;

import de.dragonhard.lobby.manager.ConfigManager;
import de.dragonhard.lobby.manager.InventoryManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;

public class Lobby_Inventory {
    private String title = "";
    private int lineAmount = 1;
    private Inventory inventory;
    private InventoryManager im = new InventoryManager();

    public void setInventory(String title, int lineAmount){
        this.title = title;
        this.lineAmount = lineAmount;
        inventory = Bukkit.getServer().createInventory(null, lineAmount*9,title);
    }

    public Inventory getInventory(){
        return inventory;
    }

    private ItemStack createItem(String title, Material material, String colorTag){
        ItemStack item = new ItemStack(material);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(colorTag + title);
        item.setItemMeta(itemMeta);

        return item;
    }

    public String getName(){
        return this.title;
    }

    public String getGameTag(){
        return " §e[§bMiniSpiel§e]";
    }

    public String getModeTag(){
        return " §e[§bModus§e]";
    }

    public String getEffectTag(){
        return " §e[§bEffekt§e]";
    }

    public String getShopItemTag(){return " §e[§bItem§e]";}

    public String getExtraTag(){
        return " §e[§bExtra§e]";
    }

    public String getColorTag(){
        return " §e[§bDesign§e]";
    }

    public String getOnlineTag(){
        return " §e[§aOnline§e]";
    }

    public String getOfflineTag(){
        return " §e[§4Offline§e]";
    }

    public String getTag(String value){ return " §e[§b"+value+"§e]"; }

    public String getClosedBetaTestTag(){ return " §e[§bBeta-Test§e]";}

    public void addItemToInventory( String title, Material material, String colorTag, int slot){
      getInventory().setItem(slot, createItem(title,material,colorTag));
    }

    public void addSkullToInventory(Player p, String title, String colorTag, int slot){
        getInventory().setItem(slot, createSkull(p,title,colorTag));
    }

    public ItemStack createSkull(Player p,String title, String colorTag){
        ItemStack item = new ItemStack(Material.SKULL_ITEM,1,(byte)3);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(colorTag + title);
        item.setItemMeta(itemMeta);
        return item;
    }

    public void addWallItemToInventory(Player p, int slot){//light_gray 160:8
        ConfigManager cm = new ConfigManager();
        addColoredWallPane(p," ",slot,1, cm.getWallColor());
    }

    public void addColoredWallPane(Player p, String label, int slotID, int colorCode, int id){
            im.addColoredGlassPane(getInventory(),p," ",slotID,colorCode,id);
    }

    public void addGoldWallItemToInventory(Player p, int slot) {
        ConfigManager cm = new ConfigManager();
        addColoredWallPane(p," ",slot,1, cm.getInsideColor());
    }


    public void addBetaItemToInventory(String title, Material material, String colorTag, int slot){
        getInventory().setItem(slot, createItem(title + " §e[§4Beta§e]",material,colorTag));
    }

    public void addAlphaItemToInventory(String title, Material material, String colorTag, int slot){
        getInventory().setItem(slot, createItem(title + " §e[§4Alpha§e]",material,colorTag));
    }

    public void addSoonItemToInventory(String title, Material material, String colorTag, int slot){
        getInventory().setItem(slot, createItem(title + " §e[§4bald verfügbar§e]",material,colorTag));
    }

    public void addSoonItemToInventory(Material material, String colorTag, int slot){
        getInventory().setItem(slot, createItem("?" + " §e[§4bald verfügbar§e]",material,colorTag));
    }


    public void addEmptyItemToInventory(int slot){
        getInventory().setItem(slot, createItem("leer",Material.BARRIER,"§4"));
    }

}
