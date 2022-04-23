package de.dragonhard.lobby.manager.other;


import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class InventoryManager {
        String defaultColor = "§5";

    public void clearInv(Player p){ p.getInventory().clear(); }

    private Inventory getInv(Player p){

      Inventory inv = p.getInventory();
      inv.setMaxStackSize(1);
        return inv;
    }

    private ItemStack addItem(String title, Material material){

        ItemStack item = new ItemStack(material);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(defaultColor + title);
        item.setItemMeta(itemMeta);

        return item;
    }


}
