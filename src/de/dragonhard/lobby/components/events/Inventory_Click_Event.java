package de.dragonhard.lobby.components.events;

import de.dragonhard.lobby.components.PermissionList;
import de.dragonhard.lobby.manager.InventoryManager;
import de.dragonhard.lobby.manager.PluginWithlistManager;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class Inventory_Click_Event implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        PluginWithlistManager pwm = new PluginWithlistManager();
        InventoryManager im = new InventoryManager();
        Player p = (Player) e.getWhoClicked();
        if(e.getClick().isRightClick() && e.getCurrentItem() == null){return;}
        switch(e.getClick()){
            default:
                if(!e.getClickedInventory().getName().contains("§") && e.getSlot() == 22 && p.hasPermission(PermissionList.getPermission("external",0)) && pwm.isOwner(p)){
                p.playSound(p.getLocation(), Sound.ENDERMAN_DEATH, 1.0F, 1.0F);
                Bukkit.getServer().dispatchCommand(p, "controlpanel");}

                break;
        }

    }

}
