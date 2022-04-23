package de.dragonhard.lobby.manager;

import de.dragonhard.lobby.manager.other.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class Managers {

    public PlayerConfigManager getPlayerManager(){
        return new PlayerConfigManager();
    }

    public PluginWithlistManager getPluginWhitelistManager(){
        return new PluginWithlistManager();
    }

    public ChatBlockManager getChatBlockManager(){return new ChatBlockManager();}

    public InventoryManager getInventoryManager(){
        return new InventoryManager();
    }

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
