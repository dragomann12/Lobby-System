package de.dragonhard.lobby.components.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryDragEvent;

public class Drag_Event implements Listener {

    @EventHandler
    public void onDrag(InventoryDragEvent e){
        Player p = (Player) e.getWhoClicked();


         e.setCancelled(true);
    }

}
