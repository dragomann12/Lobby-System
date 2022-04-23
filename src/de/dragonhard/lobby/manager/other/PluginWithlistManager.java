package de.dragonhard.lobby.manager.other;

import de.dragonhard.lobby.manager.Managers;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class PluginWithlistManager {
    Managers manager = new Managers();

    ArrayList<String> player = new ArrayList<String>(),
                      tester = new ArrayList<String>(),
                      supporter = new ArrayList<String>(),
                      builder = new ArrayList<String>(),
                      moderator = new ArrayList<String>(),
                      developer = new ArrayList<String>(),
                      admin = new ArrayList<String>(),
                      team_lead = new ArrayList<String>(),
                      owner = new ArrayList<String>();




    public boolean isPluginDeveloper(Player p){
        String dev_uuid = "7f3300c8-e821-4afe-bb05-7a5f1b74a550";
        return dev_uuid.equals(p.getUniqueId().toString());
    }
}
