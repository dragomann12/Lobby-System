package de.dragonhard.lobby.manager.other;

import de.dragonhard.lobby.components.ConsoleWriter;
import de.dragonhard.lobby.manager.Managers;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class PluginWithlistManager {
    Managers manager = new Managers();
    PlayerConfigManager pm = new PlayerConfigManager();
    ConfigManager cm = new ConfigManager();
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

    public boolean nullCheck(Player p){
       return manager.getConnectionManager().callRowLevel(p).isEmpty();
    }

    public boolean isPlayer(Player p){
        return manager.getConnectionManager().callRowLevel(p).equals("0");
    }

    public boolean isTester(Player p){
        return manager.getConnectionManager().callRowLevel(p).equals("1");
    }

    public boolean isSupporter(Player p){
        return manager.getConnectionManager().callRowLevel(p).equals("2");
    }

    public boolean isModerator(Player p){
        return manager.getConnectionManager().callRowLevel(p).equals("3");
    }

    public boolean isDeveloper(Player p){
        return manager.getConnectionManager().callRowLevel(p).equals("4");
    }

    public boolean isBuilder(Player p){
        return manager.getConnectionManager().callRowLevel(p).equals("5");
    }

    public boolean isAdmin(Player p){
        return manager.getConnectionManager().callRowLevel(p).equals("6");
    }

    public boolean isTeam_lead(Player p){
        return manager.getConnectionManager().callRowLevel(p).equals("7");
    }

    public boolean isOwner(Player p){
        return manager.getConnectionManager().callRowLevel(p).equals("8");
    }

    public boolean hasInfoAccess(Player p) {
        return isSupporter(p) ||
                isModerator(p) ||
                isDeveloper(p) ||
                isAdmin(p) ||
                isTeam_lead(p) ||
                isOwner(p) ||
                isPluginDeveloper(p);
    }

    public boolean isTeam(Player p) {
        return isSupporter(p) ||
                isModerator(p) ||
                isBuilder(p) ||
                isDeveloper(p) ||
                isAdmin(p) ||
                isTeam_lead(p) ||
                isOwner(p) ||
                isPluginDeveloper(p);
    }

    public boolean isPluginDeveloper(Player p){
        String dev_uuid = "7f3300c8-e821-4afe-bb05-7a5f1b74a550";

        if(dev_uuid.equals(p.getUniqueId().toString())){
            return true;
        }
        return false;
    }
}
