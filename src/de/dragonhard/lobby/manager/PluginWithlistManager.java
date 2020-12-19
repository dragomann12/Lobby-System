package de.dragonhard.lobby.manager;

import de.dragonhard.lobby.components.ConsoleWriter;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class PluginWithlistManager {
    PlayerConfigManager pm = new PlayerConfigManager();
    ConfigManager cm = new ConfigManager();
    ArrayList<String> player = new ArrayList<String>(),
                      tester = new ArrayList<String>(),
                      supporter = new ArrayList<String>(),
                      builder = new ArrayList<String>(),
                      moderator = new ArrayList<String>(),
                      developer = new ArrayList<String>(),
                      admin = new ArrayList<String>(),
                      team_lead = new ArrayList<String>(),
                      owner = new ArrayList<String>();

    public void onLoad(){
        ConfigManager cm = new ConfigManager();

        player.add(cm.getAccessLevelTag(0));
        tester.add(cm.getAccessLevelTag(1));
        supporter.add(cm.getAccessLevelTag(2));
        moderator.add(cm.getAccessLevelTag(3));
        developer.add(cm.getAccessLevelTag(4));
        builder.add(cm.getAccessLevelTag(5));
        admin.add(cm.getAccessLevelTag(6));
        team_lead.add(cm.getAccessLevelTag(7));
        owner.add(cm.getAccessLevelTag(8));
    }

    public boolean isPlayer(Player p){
        return player.contains(p.getName());
    }

    public boolean isTester(Player p){
        return tester.contains(p.getName());
    }

    public boolean isSupporter(Player p){
        return supporter.contains(p.getName());
    }

    public boolean isBuilder(Player p){
        return builder.contains(p.getName());
    }

    public boolean isModerator(Player p){
        return moderator.contains(p.getName());
    }

    public boolean isDeveloper(Player p){
        return developer.contains(p.getName());
    }

    public boolean isAdmin(Player p){
        return admin.contains(p.getName());
    }

    public boolean isTeam_lead(Player p){
        return team_lead.contains(p.getName());
    }

    public boolean isOwner(Player p){
       return owner.contains(p.getName());
    }

    public void removePlayerFromGroup(Player p){

        if(player.contains(cm.getAccessLevel(pm.getAccessLevel(p)))){player.remove(p.getName());return;}
        if(tester.contains(cm.getAccessLevel(pm.getAccessLevel(p)))){tester.remove(p.getName());return;}
        if(supporter.contains(cm.getAccessLevel(pm.getAccessLevel(p)))){supporter.remove(p.getName());return;}
        if(builder.contains(cm.getAccessLevel(pm.getAccessLevel(p)))){builder.remove(p.getName());return;}
        if(moderator.contains(cm.getAccessLevel(pm.getAccessLevel(p)))){moderator.remove(p.getName());return;}
        if(developer.contains(cm.getAccessLevel(pm.getAccessLevel(p)))){developer.remove(p.getName());return;}
        if(admin.contains(cm.getAccessLevel(pm.getAccessLevel(p)))){admin.remove(p.getName());return;}
        if(team_lead.contains(cm.getAccessLevel(pm.getAccessLevel(p)))){team_lead.remove(p.getName());return;}
        if(owner.contains(cm.getAccessLevel(pm.getAccessLevel(p)))){owner.remove(p.getName());return;}

        ConsoleWriter.writeWithTag("player "+ p.getName() + " with uuid " + p.getUniqueId() + "has been removed from the group " + cm.getAccessLevel(pm.getAccessLevel(p)));

    }

    public void addPlayerToGroup(Player p){

        if(player.contains(cm.getAccessLevel(pm.getAccessLevel(p)))){player.add(p.getName());return;}
        if(tester.contains(cm.getAccessLevel(pm.getAccessLevel(p)))){tester.add(p.getName());return;}
        if(supporter.contains(cm.getAccessLevel(pm.getAccessLevel(p)))){supporter.add(p.getName());return;}
        if(builder.contains(cm.getAccessLevel(pm.getAccessLevel(p)))){builder.add(p.getName());return;}
        if(moderator.contains(cm.getAccessLevel(pm.getAccessLevel(p)))){moderator.add(p.getName());return;}
        if(developer.contains(cm.getAccessLevel(pm.getAccessLevel(p)))){developer.add(p.getName());return;}
        if(admin.contains(cm.getAccessLevel(pm.getAccessLevel(p)))){admin.add(p.getName());return;}
        if(team_lead.contains(cm.getAccessLevel(pm.getAccessLevel(p)))){team_lead.add(p.getName());return;}
        if(owner.contains(cm.getAccessLevel(pm.getAccessLevel(p)))){owner.add(p.getName());return;}

        ConsoleWriter.writeWithTag("player "+ p.getName() + " with uuid " + p.getUniqueId() + "has been added to the group " + cm.getAccessLevel(pm.getAccessLevel(p)));

    }
}
