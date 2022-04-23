package de.dragonhard.lobby.components.events;

import de.dragonhard.lobby.manager.Managers;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;

public class Hide_Event extends Managers implements Listener {

//@TODO fix hide-mode : broken after my sql database implementation
// @TODO fix Blacklist : broken after my sql database implementation

    ArrayList<String> hide = new ArrayList<String>();

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        Player p = (Player) e.getPlayer();


    }

}
