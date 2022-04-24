package de.dragonhard.lobby.components.events;

import de.dragonhard.lobby.components.player.Hotbar;
import de.dragonhard.lobby.manager.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;



public class Join_Event extends Managers implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        e.setJoinMessage("");

        Hotbar.addDefaultInventory(p);
    }

}
