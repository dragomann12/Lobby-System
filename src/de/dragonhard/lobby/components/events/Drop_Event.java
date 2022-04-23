package de.dragonhard.lobby.components.events;

import de.dragonhard.lobby.manager.Managers;
import de.dragonhard.lobby.manager.other.PlayerConfigManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class Drop_Event implements Listener {

    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        Player p = e.getPlayer();
        Managers managers = new Managers();

        if(Event_Blocker.isMenu()){
            e.setCancelled(true);
            return;
        }

        if(managers.getPlayerManager().isBuildModeEnabled(p)){
            e.setCancelled(false);
            return;
        }

        e.setCancelled(true);}

}
