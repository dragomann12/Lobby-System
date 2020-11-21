package de.dragonhard.lobby.manager;

import org.bukkit.entity.Player;

public class AcessManager extends PlayerConfigManager {

    public void addPlayerToBlackList(Player p, Player target){
        this.setFile(target,"config");
        if(this.getBoolean("isBlacklisted")){
            p.sendMessage("§4Dieser Spieler ist bereits auf der Blacklist!");
        }else{
            this.set("isBlacklisted", true);
        }
    }

    public void delPlayerToBlackList(Player p, Player target){
        this.setFile(target,"config");
        if(!this.getBoolean("isBlacklisted")){
            p.sendMessage("§4Dieser Spieler ist nicht auf der Blacklist!");
        }else{
            this.set("isBlacklisted", false);
        }
    }

    public boolean isBlacklisted(Player p){
        this.setFile(p,"config");
        return this.getBoolean("isBlacklisted");
    }

}
