package de.dragonhard.lobby.manager;

import de.dragonhard.lobby.reader.MessageReader;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class ServerMessageManager extends MessageReader {

    public void sendMessage(String message, boolean isImportant){
        this.setFile();
        String color = "";

        if(isImportant){color = "4";} else{color = "b";}

        String msg_color = "§" + color;

        String msg_title = msg_color + this.getString("Title");



        for(Player p :Bukkit.getOnlinePlayers()){
            PluginWithlistManager pwm = new PluginWithlistManager();
            if (pwm.isOwner(p) || pwm.isTeam_lead(p) || pwm.isDeveloper(p) || pwm.isBuilder(p)) {
                p.sendMessage("");
            }
        }

    }

}
