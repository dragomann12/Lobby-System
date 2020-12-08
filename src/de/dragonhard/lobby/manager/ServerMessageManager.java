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
        Boolean isTeamMsg = this.getBoolean("isTeamMsg");
        String messageName = this.getString("MessageName");
        String messageColor = this.getString("MessageColor");
        String message = "§" + messageColor + this.getString("Message");

        if(isImportant){messageStart = "4";}

        String title = "§" + messageStart + "++ §" + nameColor + messageName + " §" + messageStart + "++";

        for(Player p : Bukkit.getOnlinePlayers()){

            if(cm.isDebugMode()){
                ConsoleWriter.writeDebug("loading online Player ...");
            }

            if(isTeamMsg(p, name)){

                if (isPlayerTeam(p)) {
                    p.sendMessage("§5Team >> " + title);
                    p.sendMessage(message);

                    return;
                }
                    p.sendMessage(title);
                    p.sendMessage(message);

                    return;
            }

                p.sendMessage(title);
                p.sendMessage(message);



        }

    }

    public boolean isPlayerTeam(Player p){
        PluginWithlistManager pwm = new PluginWithlistManager();

        if (pwm.isOwner(p) ||
                pwm.isTeam_lead(p) ||
                pwm.isDeveloper(p) ||
                pwm.isBuilder(p) ||
                pwm.isSupporter(p)) {
            return true;
        }
        return false;
    }

    public boolean isTeamMsg(Player p, String name){
        this.setFile(p, name);
        return this.getBoolean("isTeamMsg");
    }



    public void createMessage(Player p,String name, String messageStart, String titleColor, String messageColor, String message, boolean isImportant, boolean isTeamMsg){
        this.setFile(p,name);

        this.setDefault("StartColor", messageStart);
        this.setDefault("TitleColor", titleColor);

        this.setDefault("MessageColor", messageColor);
        this.setDefault("MessageName", name);

        this.setDefault("isImportant", isImportant);
        this.setDefault("Message", message);

        this.setDefault("isTeamMsg", isTeamMsg);

    }

    public boolean messageExists(String msgName){
        File f = this.getFile(msgName);

        if(f.exists()){return true;}
        return false;
    }

    public String getJoinMessage(){
        ConfigManager cm = new ConfigManager();

        return "";
    }

}
