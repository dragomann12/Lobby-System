package de.dragonhard.lobby.components.events;

import de.dragonhard.lobby.components.PermissionList;
import de.dragonhard.lobby.manager.MessageManager;
import de.dragonhard.lobby.manager.PlayerConfigManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Chat_Event implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e){
        Player p = e.getPlayer();
        String message = e.getMessage();
        PlayerConfigManager pm = new PlayerConfigManager();

        if(message.startsWith(MessageManager.prefix) && p.hasPermission(PermissionList.getPermission("Message",0))) {
            e.setCancelled(true);
            MessageManager.getFunktion(message, p);return;
        }

        if(pm.getChatStyleEnabled(p)) {
            e.setCancelled(true);
            MessageManager.getChatFunktion(message, p);return;
        }

        e.setCancelled(false);

    }

}
