package de.dragonhard.lobby.components.player;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Hotbar {

    public static void addItem(Player player, String title,String color, Material material, int amount, int slot){
        Inventory inv = player.getInventory();

        ItemStack item = new ItemStack(material,amount);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName("§" + color + title);
        item.setItemMeta(itemMeta);
        inv.setItem(slot,item);
    }

    private static String getSoonTag(){
        return " §e[§4Available Soon§e]";
    }

    public static void addDefaultInventory(Player player){

        addItem(player,"Backpack" + getSoonTag() ,"5",Material.BARRIER,1,0);
        addItem(player,"Jump" + getSoonTag(),"5",Material.BARRIER,1,2);
        addItem(player,"Friends" + getSoonTag(),"5",Material.BARRIER,1,5);
        addItem(player,"Group" + getSoonTag(),"5",Material.BARRIER,1,8);


    }

}
