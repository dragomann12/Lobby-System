package de.dragonhard.lobby.manager;

import de.dragonhard.lobby.manager.database.ConnectionManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class Managers {

    public ConfigManager getConfigManager(){
        return new ConfigManager();
    }

    public CoinManager getCoinManager(){
        return new CoinManager();
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

    public MessageManager getMessageManager(){
        return new MessageManager();
    }

    public PluginWithlistManager getPluginWhitelistManager(){
        return new PluginWithlistManager();
    }

    public DateManager getDateManager(){
        return new DateManager();
    }

    public InventoryManager getInventoryManager(){
        return new InventoryManager();
    }

    public ShopItemManager getShopManager(){
        return new ShopItemManager();
    }

    public WarpManager getWarpManager(){
        return new WarpManager();
    }

    public SpawnManager getSpawnManager(){
        return new SpawnManager();
    }

    public SoundManager getSoundManager(){
        return new SoundManager();
    }

    public PluginComunicationManager getCommunicationManager(){
        return new PluginComunicationManager();
    }

    public PluginManager getPluginManager(){
        return Bukkit.getPluginManager();
    }

}