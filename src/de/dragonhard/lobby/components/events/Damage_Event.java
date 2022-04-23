package de.dragonhard.lobby.components.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class Damage_Event implements Listener {

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent e){
        e.setDamage(0);
        e.setCancelled(false);
    }


}
