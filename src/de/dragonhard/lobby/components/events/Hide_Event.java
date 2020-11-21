package de.dragonhard.lobby.components.events;

import de.dragonhard.lobby.manager.PlayerConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;

public class Hide_Event implements Listener {

    ArrayList<String> hide = new ArrayList<String>();

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        PlayerConfigManager pm = new PlayerConfigManager();
        Player p = (Player) e.getPlayer();

        if(pm.getHideStatus(p)){

                hide.remove(p.getName());
                for (Player players : Bukkit.getOnlinePlayers()) {
                    p.showPlayer(players);
                }

        }else {
            hide.add(p.getName());
            for (Player players : Bukkit.getOnlinePlayers()) {
                p.hidePlayer(players);
            }

        }


    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = (Player) e.getPlayer();

        hide.add(p.getName());
        for (Player players : Bukkit.getOnlinePlayers()) {
            p.hidePlayer(players);
        }

    }

}
