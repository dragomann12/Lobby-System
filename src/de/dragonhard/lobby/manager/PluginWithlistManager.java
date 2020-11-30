package de.dragonhard.lobby.manager;

import de.dragonhard.lobby.components.ConsoleWriter;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class PluginWithlistManager {

    ArrayList<String> player = new ArrayList<String>(),
                      supporter = new ArrayList<String>(),
                      moderator = new ArrayList<String>(),
                      developer = new ArrayList<String>(),
                      team_lead = new ArrayList<String>(),
                      owner = new ArrayList<String>();

    public void onLoad(){
        ConfigManager cm = new ConfigManager();

        player.add(cm.getAccessLevelTag(0));
        supporter.add(cm.getAccessLevelTag(1));
        moderator.add(cm.getAccessLevelTag(2));
        developer.add(cm.getAccessLevelTag(3));
        team_lead.add(cm.getAccessLevelTag(4));
        owner.add(cm.getAccessLevelTag(5));
    }

    public boolean isPlayer(Player p){
        if(player.contains(p.getName())){return true;}return false;
    }

    public boolean isSupporter(Player p){
        if(supporter.contains(p.getName())){return true;}return false;
    }

    public boolean isModerator(Player p){
        if(moderator.contains(p.getName())){return true;}return false;
    }

    public boolean isDeveloper(Player p){
        if(developer.contains(p.getName())){return true;}return false;
    }

    public boolean isTeam_lead(Player p){
        if(team_lead.contains(p.getName())){return true;}return false;
    }

    public boolean isOwner(Player p){
        if(owner.contains(p.getName())){return true;}return false;
    }

    public void removePlayerFromGroup(Player p){
        PlayerConfigManager pm = new PlayerConfigManager();
        ConfigManager cm = new ConfigManager();
        int i = 0;
        for(i=0; i > cm.getAccessLevelCount(); i++){
            if(pm.getAccessLevel(p) == i){
                if(player.contains(cm.getAccessLevelTag(i))){
                    player.remove(p.getName());
                }else if(supporter.contains(cm.getAccessLevelTag(i))){
                    supporter.remove(p.getName());
                }else if(moderator.contains(cm.getAccessLevelTag(i))){
                    moderator.remove(p.getName());
                }else if(developer.contains(cm.getAccessLevelTag(i))){
                    developer.remove(p.getName());
                }else if(team_lead.contains(cm.getAccessLevelTag(i))){
                    team_lead.remove(p.getName());
                }else if(owner.contains(cm.getAccessLevelTag(i))){
                    owner.remove(p.getName());
                }else{
                    ConsoleWriter.writeWithTag("can not remove the group from player "+ p.getName() + " with uuid " + p.getUniqueId());return;
                }
            }
        }
        ConsoleWriter.writeWithTag("player "+ p.getName() + " with uuid " + p.getUniqueId() + "has been removed from the group " + cm.getAccessLevel(i));

    }

    public void addPlayerToGroup(Player p){
        PlayerConfigManager pm = new PlayerConfigManager();
            ConfigManager cm = new ConfigManager();
        int i = 0;
        for(i=0; i > cm.getAccessLevelCount(); i++){
            if(pm.getAccessLevel(p) == i){
                if(player.contains(cm.getAccessLevelTag(i))){
                    player.add(p.getName());
                }else if(supporter.contains(cm.getAccessLevelTag(i))){
                    supporter.add(p.getName());
                }else if(moderator.contains(cm.getAccessLevelTag(i))){
                    moderator.add(p.getName());
                }else if(developer.contains(cm.getAccessLevelTag(i))){
                    developer.add(p.getName());
                }else if(team_lead.contains(cm.getAccessLevelTag(i))){
                    team_lead.add(p.getName());
                }else if(owner.contains(cm.getAccessLevelTag(i))){
                    owner.add(p.getName());
                }else{
                    ConsoleWriter.writeWithTag("can not assign group to player "+ p.getName() + " with uuid " + p.getUniqueId());return;
                }
            }
        }
        ConsoleWriter.writeWithTag("player "+ p.getName() + " with uuid " + p.getUniqueId() + "has been added to the group " + cm.getAccessLevel(i));

    }
}
