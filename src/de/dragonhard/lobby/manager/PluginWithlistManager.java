package de.dragonhard.lobby.manager;

import de.dragonhard.lobby.components.ConsoleWriter;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class PluginWithlistManager {
    PlayerConfigManager pm = new PlayerConfigManager();
    ConfigManager cm = new ConfigManager();
   private static String[] developer_list = {"Dragonhard117","SLINIcraftet204"};
    ArrayList<String> plugin_developer = new ArrayList<String>();
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
        if(pm.getAccessLevel(p) == 0){ return true;}return false;
    }

    public boolean isTester(Player p){
        if(pm.getAccessLevel(p) == 1){ return true;}return false;
    }

    public boolean isSupporter(Player p){
        if(pm.getAccessLevel(p) == 2){ return true;}return false;
    }

    public boolean isModerator(Player p){
        if(pm.getAccessLevel(p) == 3){ return true;}return false;
    }

    public boolean isDeveloper(Player p){
        if(pm.getAccessLevel(p) == 4){ return true;}return false;
    }

    public boolean isBuilder(Player p){
        if(pm.getAccessLevel(p) == 5){ return true;}return false;
    }

    public boolean isAdmin(Player p){
        if(pm.getAccessLevel(p) == 6){ return true;}return false;
    }

    public boolean isTeam_lead(Player p){
        if(pm.getAccessLevel(p) == 7){ return true;}return false;
    }

    public boolean isOwner(Player p){
        if(pm.getAccessLevel(p) == 8){ return true;}return false;
    }

    public boolean isPluginDeveloper(Player p){
        for (int i = 0; i <= developer_list.length; i++){
            if(developer_list[i].equals(p.getName())){
                return true;
            }else{
                break;
            }

        }
        return false;
    }

    public void removePlayerFromGroup(Player p){

        if(player.contains(cm.getAccessLevel(pm.getAccessLevel(p)))){player.remove(p.getName()); ConsoleWriter.writeWithTag("player "+ p.getName() + " with uuid " + p.getUniqueId() + "has been removed from the group " + cm.getAccessLevel(pm.getAccessLevel(p)));return;}
        if(tester.contains(cm.getAccessLevel(pm.getAccessLevel(p)))){tester.remove(p.getName()); ConsoleWriter.writeWithTag("player "+ p.getName() + " with uuid " + p.getUniqueId() + "has been removed from the group " + cm.getAccessLevel(pm.getAccessLevel(p)));return;}
        if(supporter.contains(cm.getAccessLevel(pm.getAccessLevel(p)))){supporter.remove(p.getName()); ConsoleWriter.writeWithTag("player "+ p.getName() + " with uuid " + p.getUniqueId() + "has been removed from the group " + cm.getAccessLevel(pm.getAccessLevel(p)));return;}
        if(builder.contains(cm.getAccessLevel(pm.getAccessLevel(p)))){builder.remove(p.getName()); ConsoleWriter.writeWithTag("player "+ p.getName() + " with uuid " + p.getUniqueId() + "has been removed from the group " + cm.getAccessLevel(pm.getAccessLevel(p)));return;}
        if(moderator.contains(cm.getAccessLevel(pm.getAccessLevel(p)))){moderator.remove(p.getName()); ConsoleWriter.writeWithTag("player "+ p.getName() + " with uuid " + p.getUniqueId() + "has been removed from the group " + cm.getAccessLevel(pm.getAccessLevel(p)));return;}
        if(developer.contains(cm.getAccessLevel(pm.getAccessLevel(p)))){developer.remove(p.getName()); ConsoleWriter.writeWithTag("player "+ p.getName() + " with uuid " + p.getUniqueId() + "has been removed from the group " + cm.getAccessLevel(pm.getAccessLevel(p)));return;}
        if(admin.contains(cm.getAccessLevel(pm.getAccessLevel(p)))){admin.remove(p.getName()); ConsoleWriter.writeWithTag("player "+ p.getName() + " with uuid " + p.getUniqueId() + "has been removed from the group " + cm.getAccessLevel(pm.getAccessLevel(p)));return;}
        if(team_lead.contains(cm.getAccessLevel(pm.getAccessLevel(p)))){team_lead.remove(p.getName()); ConsoleWriter.writeWithTag("player "+ p.getName() + " with uuid " + p.getUniqueId() + "has been removed from the group " + cm.getAccessLevel(pm.getAccessLevel(p)));return;}
        if(owner.contains(cm.getAccessLevel(pm.getAccessLevel(p)))){owner.remove(p.getName()); ConsoleWriter.writeWithTag("player "+ p.getName() + " with uuid " + p.getUniqueId() + "has been removed from the group " + cm.getAccessLevel(pm.getAccessLevel(p)));return;}
        if(plugin_developer.contains(cm.getAccessLevel(pm.getAccessLevel(p)))){plugin_developer.remove(p.getName()); ConsoleWriter.writeWithTag("player "+ p.getName() + " with uuid " + p.getUniqueId() + "has been removed from the group " + cm.getAccessLevel(pm.getAccessLevel(p)));return;}

    }

    public void addPlayerToGroup(Player p){

        if(player.contains(cm.getAccessLevel(pm.getAccessLevel(p)))){player.add(p.getName()); ConsoleWriter.writeWithTag("player "+ p.getName() + " with uuid " + p.getUniqueId() + "has been added to the group " + cm.getAccessLevel(pm.getAccessLevel(p)));return;}
        if(tester.contains(cm.getAccessLevel(pm.getAccessLevel(p)))){tester.add(p.getName()); ConsoleWriter.writeWithTag("player "+ p.getName() + " with uuid " + p.getUniqueId() + "has been added to the group " + cm.getAccessLevel(pm.getAccessLevel(p)));return;}
        if(supporter.contains(cm.getAccessLevel(pm.getAccessLevel(p)))){supporter.add(p.getName()); ConsoleWriter.writeWithTag("player "+ p.getName() + " with uuid " + p.getUniqueId() + "has been added to the group " + cm.getAccessLevel(pm.getAccessLevel(p)));return;}
        if(builder.contains(cm.getAccessLevel(pm.getAccessLevel(p)))){builder.add(p.getName());  ConsoleWriter.writeWithTag("player "+ p.getName() + " with uuid " + p.getUniqueId() + "has been added to the group " + cm.getAccessLevel(pm.getAccessLevel(p)));return;}
        if(moderator.contains(cm.getAccessLevel(pm.getAccessLevel(p)))){moderator.add(p.getName()); ConsoleWriter.writeWithTag("player "+ p.getName() + " with uuid " + p.getUniqueId() + "has been added to the group " + cm.getAccessLevel(pm.getAccessLevel(p)));return;}
        if(developer.contains(cm.getAccessLevel(pm.getAccessLevel(p)))){developer.add(p.getName()); ConsoleWriter.writeWithTag("player "+ p.getName() + " with uuid " + p.getUniqueId() + "has been added to the group " + cm.getAccessLevel(pm.getAccessLevel(p)));return;}
        if(admin.contains(cm.getAccessLevel(pm.getAccessLevel(p)))){admin.add(p.getName()); ConsoleWriter.writeWithTag("player "+ p.getName() + " with uuid " + p.getUniqueId() + "has been added to the group " + cm.getAccessLevel(pm.getAccessLevel(p)));return;}
        if(team_lead.contains(cm.getAccessLevel(pm.getAccessLevel(p)))){team_lead.add(p.getName()); ConsoleWriter.writeWithTag("player "+ p.getName() + " with uuid " + p.getUniqueId() + "has been added to the group " + cm.getAccessLevel(pm.getAccessLevel(p)));return;}
        if(owner.contains(cm.getAccessLevel(pm.getAccessLevel(p)))){owner.add(p.getName()); ConsoleWriter.writeWithTag("player "+ p.getName() + " with uuid " + p.getUniqueId() + "has been added to the group " + cm.getAccessLevel(pm.getAccessLevel(p)));return;}
        if(plugin_developer.contains(cm.getAccessLevel(pm.getAccessLevel(p)))){plugin_developer.add(p.getName()); ConsoleWriter.writeWithTag("player "+ p.getName() + " with uuid " + p.getUniqueId() + "has been added to the group " + cm.getAccessLevel(pm.getAccessLevel(p)));return;}

    }
}
