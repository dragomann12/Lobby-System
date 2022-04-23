package de.dragonhard.lobby.components.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;

public class Health_Event implements Listener {
    @EventHandler
    public void onHealth(EntityRegainHealthEvent e){
        e.setAmount(20);
        e.setCancelled(true);
    }

}
