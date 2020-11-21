package de.dragonhard.lobby.components.events;

import de.dragonhard.lobby.components.MenuInventory;
import de.dragonhard.lobby.components.PermissionList;
import de.dragonhard.lobby.manager.InventoryManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class Inventory_Click_Event implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        InventoryManager im = new InventoryManager();
        Player p = (Player) e.getWhoClicked();

        switch(e.getClick()){
            default:
                if(!e.getClickedInventory().getName().contains("§") && e.getSlot() == 22 && p.hasPermission(PermissionList.getPermission("external",0))){
                p.playSound(p.getLocation(), Sound.ENDERMAN_DEATH, 1.0F, 1.0F);
                Bukkit.getServer().dispatchCommand(p, "controlpanel");}

                break;
        }

    }

}
