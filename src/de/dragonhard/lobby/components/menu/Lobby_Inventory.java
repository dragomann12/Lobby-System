package de.dragonhard.lobby.components.menu;

import de.dragonhard.lobby.manager.other.InventoryManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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

    public String getTag(String value){ return " §e[§b"+value+"§e]"; }

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


}
