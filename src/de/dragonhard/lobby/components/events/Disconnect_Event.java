package de.dragonhard.lobby.components.events;

import de.dragonhard.lobby.manager.PluginWithlistManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class Disconnect_Event implements Listener {

    @EventHandler
    public void onDisconnect(PlayerQuitEvent e){
        e.setQuitMessage("§akomm §ebald §5wieder :)");
        PluginWithlistManager pwm = new PluginWithlistManager();
        Player p = e.getPlayer();

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
