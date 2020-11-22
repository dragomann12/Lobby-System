package de.dragonhard.lobby.manager;

import de.dragonhard.lobby.reader.ConfigReader;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.io.File;

public class PlayerConfigManager extends ConfigReader {

    public void checkPlayer(Player p){

    if(!this.exists(p, "config")){getDefaultPlayerConfig(p);}

    }

    private void getDefaultPlayerConfig(Player p){
        this.setFile(p,"config");

        this.setDefault("usedWarps",0);
        this.setDefault("maxWarps",5);
        this.setDefault("InventorySlots", 10);
        this.setDefault("hasInf",false);
        this.setDefault("AutoWarp",false);
        this.setDefault("FirstTime",true);
        this.setDefault("WarpEnabled",true);
        this.setDefault("AutoWarpLocation", "non");
        this.setDefault("DBVersion",1);
        this.setDefault("ChatStyle",false);
        this.setDefault("ChatNameColor","4");
        this.setDefault("ChatSeperatorColor","5");
        this.setDefault("isBlacklisted", false);
        this.setDefault("isBuildModeEnabled",false);
        this.setDefault("hide",false);
        this.setDefault("updateMenu",false);
    }

    public void toggleUpdate(Player p){
        this.setFile(p,"config");
        if(isUpdate(p)){
            this.set("updateMenu",false);
        }else{
            this.set("updateMenu",true);
        }
    }

    public boolean isUpdate(Player p){
        this.setFile(p,"config");
        return this.getBoolean("updateMenu");
    }

    public boolean getHideStatus(Player p){
        this.setFile(p,"config");
        return this.getBoolean("hide");
    }

    public Material getHideStatusMaterial(Player p){

        if(getHideStatus(p)){
            return Material.BLAZE_ROD;
        }else{
            return  Material.STICK;
        }

    }

    public void toggleHideStatus(Player p){
        this.setFile(p,"config");
        if(getHideStatus(p)){
            this.set("hide",false);
            p.sendMessage("§aEs werden alle Spieler angezeigt!");
        }else{
            this.set("hide",true);
            p.sendMessage("§4Es werden keine Spieler angezeigt!");
        }
    }

    public String getChatSeperatorColor(Player p){
        this.setFile(p,"config");
        return this.getString("ChatSeperatorColor");
    }

    public String getChatNameColor(Player p){
        this.setFile(p,"config");
        return this.getString("ChatNameColor");
    }

    public Boolean getChatStyleEnabled(Player p){
        this.setFile(p,"config");
        return this.getBoolean("ChatStyle");
    }

    public Boolean isBuildModeEnabled(Player p){
        this.setFile(p,"config");
        return this.getBoolean("isBuildModeEnabled");
    }

    public void toggleBuildMode(Player p){
        this.setFile(p,"config");
        if(this.getBoolean("isBuildModeEnabled")){
            this.set("isBuildModeEnabled", false);
            p.setGameMode(GameMode.SURVIVAL);
            p.sendMessage("§4Du bist nun nicht mehr im Bau-Modus!");
        }else{
            this.set("isBuildModeEnabled", true);
            p.setGameMode(GameMode.CREATIVE);
            p.sendMessage("§aDu bist nun im Bau-Modus!");
        }
    }

    public boolean getAutoWarpStatus(Player p){
        this.setFile(p,"config");
        return this.getBoolean("AutoWarp");
    }

    public boolean isFirstJoin(Player p){
        this.setFile(p,"config");
        return this.getBoolean("FirstTime");
    }

    public void toggleFirstJoin(Player p){
        this.setFile(p,"config");
        if(this.getBoolean("FirstTime")){
            this.set("FirstTime", false);
        }
    }

    public int getPlayerDBVersion(Player p){
        this.setFile(p,"config");
            return this.getInteger("DBVersion");
    }

    public boolean isMatching(Player p){
        ConfigManager cm = new ConfigManager();

        int currentDBVersion = cm.getDBVersion();

        if(currentDBVersion == getPlayerDBVersion(p)){
            return true;
        }
        return false;

    }

    public boolean isWarpEnabled(Player p){
        this.setFile(p,"config");
        return this.getBoolean("WarpEnabled");
    }

    public void setWarpEnabled(Player p, boolean status){
        this.setFile(p,"config");

        this.set("WarpEnabled", status);

    }

    public void toggleAutoWarp(Player p){
        this.setFile(p,"config");
        if(hasAutoWarp(p)){
            this.set("AutoWarp", false);
        }else {
            this.set("AutoWarp", true);
        }
    }

    public String getAutoWarpLocation(Player p){
        this.setFile(p,"config");
        return this.getString("AutoWarpLocation");
    }

    public void setAutoWarpLocation(Player p, String warp){
        this.setFile(p,"config");
        if(warp != "" && warp != " "){
            WarpManager wm = new WarpManager();
            if(wm.exists(p,warp)) {
                this.set("AutoWarpLocation", warp);
                p.sendMessage("§ader Warp wurde gesetzt");
            }else{
                p.sendMessage("§4Es muss ein gültiger Warp angegeben werden!");
            }
        }else{
            p.sendMessage("§4Es muss ein gültiger Warp angegeben werden!");
        }

    }

    public boolean hasAutoWarp(Player p){
        this.setFile(p,"config");
        return this.getBoolean("AutoWarp");
    }

    public boolean hasInf(Player p){
        this.setFile(p, "config");
        return this.getBoolean("hasInf");
    }

    public void setInf(Player p, String status){
        this.setFile(p, "config");

        if(status == "enable"){
            this.set("hasInf", true);
        }else{
            this.set("hasInf", false);
        }

    }

    public int getCurrentWarps(Player p){
        this.setFile(p, "config");
        return this.getInteger("usedWarps");
    }
    
    public int getMaxWarps(Player p){
        this.setFile(p, "config");
        return this.getInteger("maxWarps");
    }

    public void setMaxWarps(Player p, int warpCount){
        this.setFile(p,"config");
        this.set("maxWarps", warpCount);

        if(getMaxWarps(p) == warpCount){
            p.sendMessage("§aDeine Warps wurden auf §e" + getMaxWarps(p) + " §agesetzt");
        }
    }

    public void addWarpToCount(Player p){
        this.setFile(p, "config");
        if(this.getInteger("usedWarps") <= this.getInteger("maxWarps")){
            int Ammount = getCurrentWarps(p) + 1;
            this.set("usedWarps", Ammount);
        }
    }

    public void delWarpFromCount(Player p){
        this.setFile(p, "config");
        if(this.getInteger("usedWarps") <= this.getInteger("maxWarps")){
            int Ammount = getCurrentWarps(p) - 1;
            this.set("usedWarps", Ammount);
        }
    }

    public boolean isMax(Player p){
        this.setFile(p, "config");
        if(getCurrentWarps(p) == getMaxWarps(p)){
            return true;
        }
            return false;
    }

    public String getLanguage(Player p){
        this.setFile(p,"config");
        return this.getString("language");
    }
}
