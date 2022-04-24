package de.dragonhard.lobby.manager;

import de.dragonhard.lobby.manager.other.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class Managers {

    public WarpManager getWarpManager(){
        return new WarpManager();
    }

    public SpawnManager getSpawnManager(){
        return new SpawnManager();
    }

    public MenuManager getMenuManager(){return new MenuManager();}

    public PluginManager getPluginManager(){
        return Bukkit.getPluginManager();
    }

    public EventManager getEventManager(){return new EventManager();}
}
