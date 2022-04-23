package de.dragonhard.lobby.manager.other;

import de.dragonhard.lobby.manager.Managers;
import de.dragonhard.lobby.reader.ConfigReader;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class PlayerConfigManager extends ConfigReader {

    private static Managers manager = new Managers();
    private static final String defaultConfig = "config";

    @Deprecated
    public void setKey(Player p, String key){setStringOf(p,"AccessKey", key);}

    public int getIntegerOf(Player p, String item){
        this.setFile(p, defaultConfig);
        return this.getInteger(item);
    }

    public int getCurrentWarps(Player p){
        return manager.getConnectionManager().callRowWarpUsed(p);
    }

    public int getMaxWarps(Player p){
        return manager.getConnectionManager().callRowWarpMax(p);
    }

    public void setStringOf(Player p, String item, String value){
        this.setFile(p, defaultConfig);
        this.set(item, value);
    }

    public Boolean getBooleanOf(Player p, String item){
        this.setFile(p, defaultConfig);
        return this.getBoolean(item);
    }


    public Boolean isBuildModeEnabled(Player p){
        return manager.getConnectionManager().callRowBuildMode(p) == 1;
    }

    public boolean getHideStatus(Player p){
        return manager.getConnectionManager().callRowHideMode(p) == 1;
    }

    public boolean isWarpEnabled(Player p){
        return getBooleanOf(p,"WarpEnabled");
    }

    public boolean hasInf(Player p){
        return getBooleanOf(p,"hasInf");
    }


    public Material getHideStatusMaterial(Player p){

        if(getHideStatus(p)){
            return Material.BLAZE_ROD;
        }else{
            return  Material.STICK;
        }

    }

    public void delWarpFromCount(Player p){
        if(manager.getConnectionManager().callRowWarpUsed(p) != 0){
            manager.getConnectionManager().delRowWarp(p);
        }
    }

}
