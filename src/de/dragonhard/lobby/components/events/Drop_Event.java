package de.dragonhard.lobby.components.events;


import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class Drop_Event implements Listener {

    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        Player p = e.getPlayer();

        e.setCancelled(true);}
}
