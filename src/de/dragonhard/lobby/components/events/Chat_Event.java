package de.dragonhard.lobby.components.events;

import de.dragonhard.lobby.components.PermissionList;
import de.dragonhard.lobby.manager.Managers;
import de.dragonhard.lobby.manager.other.MessageManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Chat_Event extends Managers implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e){
        Player p = e.getPlayer();
        String message = e.getMessage();


        if(message.startsWith(MessageManager.prefix) && p.hasPermission(PermissionList.getPermission("Message",0))) {
            e.setCancelled(true);
            this.getMessageManager().getFunktion(message, p);return;
        }

        e.setMessage(this.getChatBlockManager().stringReplacer(message));


    }

}
