package de.dragonhard.lobby.components.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class Damage_Event implements Listener {

    public void onPlayerDamage(EntityDamageEvent e){
        if(e instanceof Player){
            Player p = (Player) e.getEntity();
            e.setCancelled(true);
        }


    }
}
