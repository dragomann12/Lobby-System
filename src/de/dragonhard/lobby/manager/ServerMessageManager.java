package de.dragonhard.lobby.manager;

import com.sun.org.apache.xpath.internal.operations.Variable;
import de.dragonhard.lobby.components.ConsoleWriter;
import de.dragonhard.lobby.reader.MessageReader;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;

public class ServerMessageManager extends MessageReader {

    ConfigManager cm = new ConfigManager();

    public void sendMessage(String name){

        String messageStart = this.getString("StartColor");
        String nameColor = this.getString("TitleColor");
        Boolean isImportant = this.getBoolean("isImportant");
        String messageName = this.getString("MessageName");
        String messageColor = this.getString("MessageColor");
        String message = "§" + messageColor + this.getString("Message");

        if(isImportant){messageStart = "4";}

        String title = "§" + messageStart + "++ §" + nameColor + messageName + " §" + messageStart + "++";

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

    public void createMessage(Player p,String name, String messageStart, String titleColor, String messageColor, String message, boolean isImportant){
        if(messageExists(name)){p.sendMessage("§4");}
        this.setFile(p,name);

        this.setDefault("StartColor", messageStart);
        this.setDefault("TitleColor", titleColor);

        this.setDefault("MessageColor", messageColor);
        this.setDefault("MessageName", name);

        this.setDefault("isImportant", isImportant);
        this.setDefault("Message", message);

    }

    public boolean messageExists(String msgName){
        File f = this.getFile(msgName);

        if(f.exists()){return true;}
        return false;
    }

}
