package de.dragonhard.lobby.components.events;

import de.dragonhard.lobby.manager.PlayerConfigManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryDragEvent;

public class Drag_Event implements Listener {

    @EventHandler
    public void onDrag(InventoryDragEvent e){
        Player p = (Player) e.getWhoClicked();
        PlayerConfigManager pm = new PlayerConfigManager();

        if(Event_Blocker.isMenu()){
            e.setCancelled(true);
            return;
        }

        if(pm.isBuildModeEnabled(p)){
            e.setCancelled(false);
            return;
        }

         e.setCancelled(true);
    }

}
