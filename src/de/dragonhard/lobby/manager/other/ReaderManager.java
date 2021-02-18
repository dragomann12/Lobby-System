package de.dragonhard.lobby.manager.other;

import de.dragonhard.lobby.reader.*;

public class ReaderManager {

    public ConfigReader getConfigReader(){
        return new ConfigReader();
    }

    public GlobalWarpReader getGlobalWarpReader(){
        return new GlobalWarpReader();
    }

    public PluginConfigReader getPluginConfigReader(){
        return new PluginConfigReader();
    }

    public ShopItemReader getShopItemReader(){
        return new ShopItemReader();
    }

    public SpawnReader getSpawnReader(){
        return new SpawnReader();
    }

    public WarpListReader getWarpListReader(){
        return new WarpListReader();
    }

    public WarpReader getWarpReader(){
        return new WarpReader();
    }


}
