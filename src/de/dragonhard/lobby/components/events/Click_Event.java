package de.dragonhard.lobby.components.events;

import de.dragonhard.lobby.manager.PlayerConfigManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class Click_Event implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e){
        PlayerConfigManager pm = new PlayerConfigManager();
        Player p = (Player) e.getWhoClicked();

        if(Event_Blocker.isMenu()){
            e.setCancelled(true);
            return;
        }

        if(pm.isBuildModeEnabled(p)){
            e.setCancelled(false);
            return;
        }

        e.setCancelled(true);}


}
