package de.dragonhard.lobby.manager.other;


import de.dragonhard.lobby.manager.Managers;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CommandActionManager extends Managers {


    public void Action_clearChat(Player p){

        for(int i = 0; i < 100; i++){
            Bukkit.broadcastMessage("");
        }

        Bukkit.broadcastMessage("§e" + p.getName() + " §bhat den Chat geleert!");

    }

}
