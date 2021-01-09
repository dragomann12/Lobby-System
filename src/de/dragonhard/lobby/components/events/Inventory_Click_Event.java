package de.dragonhard.lobby.components.events;

import de.dragonhard.lobby.components.PermissionList;
import de.dragonhard.lobby.manager.other.InventoryManager;
import de.dragonhard.lobby.manager.other.PluginWithlistManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class Inventory_Click_Event implements Listener {


    public void onInventoryClick(InventoryClickEvent e){
        PluginWithlistManager pwm = new PluginWithlistManager();
        InventoryManager im = new InventoryManager();
        Player p = (Player) e.getWhoClicked();
        if(e.getClick().isRightClick() && e.getCurrentItem() == null){return;}
        switch(e.getClick()){
            default:
                if(p.hasPermission(PermissionList.getPermission("external",0)) && pwm.isOwner(p) || pwm.isPluginDeveloper(p)){
                Bukkit.getServer().dispatchCommand(p, "controlpanel");}

                break;
        }

    }

}
