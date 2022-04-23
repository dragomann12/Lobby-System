package de.dragonhard.lobby.manager;

import de.dragonhard.lobby.manager.database.ConnectionManager;
import de.dragonhard.lobby.manager.database.ConnectionStateManager;
import de.dragonhard.lobby.manager.database.MySQLManager;
import de.dragonhard.lobby.manager.other.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class Managers {

    public ConfigManager getConfigManager(){
        return new ConfigManager();
    }

    public PlayerConfigManager getPlayerManager(){
        return new PlayerConfigManager();
    }

    public AcessManager getAccessManager(){
        return new AcessManager();
    }

    public ConnectionManager getConnectionManager(){
        return new ConnectionManager();
    }

    public CommandActionManager getCommandActionManager(){
        return new CommandActionManager();
    }

    public PluginWithlistManager getPluginWhitelistManager(){
        return new PluginWithlistManager();
    }

    public ReaderManager getReaderManager(){
        return new ReaderManager();
    }

    public ChatBlockManager getChatBlockManager(){return new ChatBlockManager();}

    public DateManager getDateManager(){
        return new DateManager();
    }

    public InventoryManager getInventoryManager(){
        return new InventoryManager();
    }

    public WarpManager getWarpManager(){
        return new WarpManager();
    }

    public SpawnManager getSpawnManager(){
        return new SpawnManager();
    }

    public GlobalWarpManager getGlobalWarpManager(){return new GlobalWarpManager();}

    public MenuManager getMenuManager(){return new MenuManager();}

    public PluginManager getPluginManager(){
        return Bukkit.getPluginManager();
    }

    public ConnectionStateManager getConnectionStateManager(){return new ConnectionStateManager();}

    public MySQLManager getMySqlManager(){return new MySQLManager();}

    public EventManager getEventManager(){return new EventManager();}
}
