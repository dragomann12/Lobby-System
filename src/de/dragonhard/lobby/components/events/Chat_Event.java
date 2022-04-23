package de.dragonhard.lobby.components.events;

import de.dragonhard.lobby.manager.Managers;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Chat_Event extends Managers implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e){
        Player p = e.getPlayer();
        String message = e.getMessage();


        e.setMessage(this.getChatBlockManager().stringReplacer(message));


    }

}
