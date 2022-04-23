package de.dragonhard.lobby.components.events;

import de.dragonhard.lobby.manager.other.PlayerConfigManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class Build_Event implements Listener {

    @EventHandler
    public void onBuild(PlayerPickupItemEvent e){
        Player p = e.getPlayer();
        PlayerConfigManager pm = new PlayerConfigManager();
       if(pm.isBuildModeEnabled(p)){
           e.setCancelled(false);
           return;
       }
        e.setCancelled(true);}

}
