package de.dragonhard.lobby.manager;

import com.sun.org.apache.xpath.internal.operations.Variable;
import de.dragonhard.lobby.components.ConsoleWriter;
import de.dragonhard.lobby.reader.MessageReader;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class ServerMessageManager extends MessageReader {

    ConfigManager cm = new ConfigManager();

    public void sendMessage(String name){

        String color = this.getString("color");
        String nameColor = this.getString("color");
        Boolean isImportant = this.getBoolean("isImportant");
        String messageName = this.getString("MessageName");
        String messageColor = this.getString("MessageColor");
        String message = "§" + messageColor + this.getString("Message");

        if(isImportant){color = "4";}

        String title = "§" + nameColor + messageName + " §" + color;

        for(Player p :Bukkit.getOnlinePlayers()){
            if(cm.isDebugMode()){
                ConsoleWriter.writeDebug("loading online Player ...");
            }
            
            PluginWithlistManager pwm = new PluginWithlistManager();
            if (pwm.isOwner(p) || pwm.isTeam_lead(p) || pwm.isDeveloper(p) || pwm.isBuilder(p)) {

                p.sendMessage(title);
                p.sendMessage(message);
            }
        }

    }

}
