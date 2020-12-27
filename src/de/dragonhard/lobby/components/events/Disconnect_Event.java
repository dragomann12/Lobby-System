package de.dragonhard.lobby.components.events;

import de.dragonhard.lobby.manager.ConfigManager;
import de.dragonhard.lobby.manager.PluginWithlistManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class Disconnect_Event implements Listener {

    @EventHandler
    public void onDisconnect(PlayerQuitEvent e){
        ConfigManager cm = new ConfigManager();
        Player p = e.getPlayer();
        if(cm.isQuitMessageEnabled()){
            String message = p.getName() + " " + cm.getQuitMessage().replaceAll("/c","§");
            e.setQuitMessage(message);
        }
        PluginWithlistManager pwm = new PluginWithlistManager();


        if(pwm.isOwner(p) ||
        pwm.isTeam_lead(p) ||
        pwm.isDeveloper(p) ||
        pwm.isModerator(p) ||
        pwm.isSupporter(p) ||
        pwm.isPlayer(p)){
            pwm.removePlayerFromGroup(p);
        }


    }
}
