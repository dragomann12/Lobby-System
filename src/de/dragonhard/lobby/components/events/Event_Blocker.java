package de.dragonhard.lobby.components.events;

import de.dragonhard.lobby.manager.Managers;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;

public class Event_Blocker implements Listener {
private static boolean isMenu;
    @EventHandler
    public void onInvOpen(InventoryOpenEvent e){
        Player p = (Player) e.getPlayer();

    }


}
