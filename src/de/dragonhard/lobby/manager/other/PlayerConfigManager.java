package de.dragonhard.lobby.manager.other;

import de.dragonhard.lobby.manager.Managers;
import de.dragonhard.lobby.reader.ConfigReader;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class PlayerConfigManager extends ConfigReader {
    private static Managers manager = new Managers();
    private static final String defaultConfig = "config";
    private static final String defaultItemBase = "Player.";

    public void checkPlayer(Player p){

    if(!this.exists(p, "config")){getDefaultPlayerConfig(p);}

    }

    private void getDefaultPlayerConfig(Player p){
        this.setFile(p,"config");
        this.setDefault("usedWarps",0);
        this.setDefault("maxWarps",5);
        this.setDefault("DbCreated",false);
        this.setDefault("InventorySlots", 10);
        this.setDefault("hasInf",false);
        this.setDefault("AutoWarp",false);
        this.setDefault("FirstTime",true);
        this.setDefault("WarpEnabled",true);
        this.setDefault("AutoWarpLocation", "non");
        this.setDefault("DBVersion","1.0");
        this.setDefault("ChatStyle",false);
        this.setDefault("ChatNameColor","4");
        this.setDefault("ChatSeperatorColor","5");
        this.setDefault("isBlacklisted", false);
        this.setDefault("isBuildModeEnabled",false);
        this.setDefault("hide",false);
        this.setDefault("updateMenu",false);
        this.setDefault("AccessLevel",0);
        this.setDefault("AccessKey","");
        this.setDefault("AccessKeyEnabled",false);
        this.setDefault("warpUpgraded", false);
        this.setDefault("UserTag","" + p.getFoodLevel() + p.getUniqueId());
        this.setDefault("passwd","");
        this.setDefault("Online","");

        // keytemplate: <UserName><UserId><userTag><passwd><AccessLevel><securityTag>
    }

    public void setWarpEnabled(Player p, boolean status){
        setBooleanOf(p,"WarpEnabled", status);
    }

    public void setKey(Player p, String key){setStringOf(p,"AccessKey", key);}

        /*
                    under this are the Integer
     */

    public int getIntegerOf(Player p, String item){
        this.setFile(p, defaultConfig);
        return this.getInteger(item);
    }

    public void setIntegerOf(Player p, String item, int value){
        this.setFile(p, defaultConfig);
        this.set(item, value);
    }

    public int getAccessLevel(Player p){
        return getIntegerOf(p,"AccessLevel");
    }

    public int getPlayerDBVersion(Player p){
        return getIntegerOf(p,"DBVersion");
    }

    public int getCurrentWarps(Player p){
        return getIntegerOf(p,"usedWarps");
    }

    public int getMaxWarps(Player p){
        return getIntegerOf(p,"maxWarps");
    }

        /*
                    under this are the Strings
     */

    public void setStringOf(Player p, String item, String value){
        this.setFile(p, defaultConfig);
        this.set(item, value);
    }

    public String getStringOf(Player p, String item){
        this.setFile(p, defaultConfig);
        return this.getString(item);
    }

    public String getPasswd(Player p){
        return getStringOf(p,"passwd");
    }

    public String getUserTag(Player p){
        return getStringOf(p,"UserTag");
    }

    public String getAccessKey(Player p) { return getStringOf(p,"AccessKey"); }

    public String getChatSeperatorColor(Player p){
        return getStringOf(p,"ChatSeperatorColor");
    }

    public String getChatNameColor(Player p){
        return getStringOf(p,"ChatNameColor");
    }

    public String getAutoWarpLocation(Player p){
        return getStringOf(p,"AutoWarpLocation");
    }

    /*
                    under this are the Boolean
     */

    public Boolean getBooleanOf(Player p, String item){
        this.setFile(p, defaultConfig);
        return this.getBoolean(item);
    }

    public void setBooleanOf(Player p, String item, boolean value){
        this.setFile(p, defaultConfig);
        this.set(item, value);
    }

    public Boolean getChatStyleEnabled(Player p){
        return getBooleanOf(p,"ChatStyle");
    }

    public Boolean isBuildModeEnabled(Player p){
        return manager.getConnectionManager().callRowBuildMode(p) == 1;
    }

    public boolean isUpdate(Player p){
        return getBooleanOf(p,"updateMenu");
    }

    public boolean getHideStatus(Player p){
        return manager.getConnectionManager().callRowHideMode(p) == 1;
    }

    public boolean getAutoWarpStatus(Player p){
        return getBooleanOf(p,"AutoWarp");
    }

    public boolean isFirstJoin(Player p){
        return getBooleanOf(p,"FirstTime");
    }

    public boolean isWarpEnabled(Player p){
        return getBooleanOf(p,"WarpEnabled");
    }

    public boolean hasAutoWarp(Player p){
        return getBooleanOf(p,"AutoWarp");
    }

    public boolean hasDBPlace(Player p){return getBooleanOf(p,"DbCreated");}

    public boolean isAccessKeyEnabled(Player p){return getBooleanOf(p,"AccessKeyEnabled");}

    public boolean hasInf(Player p){
        return getBooleanOf(p,"hasInf");
    }

    public boolean hasWarpUpgrade(Player p){
        return getBooleanOf(p,"warpUpgraded");
    }

    public boolean isMax(Player p){
        if(getCurrentWarps(p) == getMaxWarps(p)){
            return true;
        }
        return false;
    }

        /*
                    under this are the other stuff
     */

    public Material getHideStatusMaterial(Player p){

        if(getHideStatus(p)){
            return Material.BLAZE_ROD;
        }else{
            return  Material.STICK;
        }

    }

    public void toggleUpdate(Player p){
        this.setFile(p,"config");
        if(isUpdate(p)){
            this.set("updateMenu",false);
        }else{
            this.set("updateMenu",true);
        }
    }

    public void toggleHideStatus(Player p){
        manager.getConnectionManager().toggleRowHideMode(p);
        if(getHideStatus(p)){
            p.sendMessage("§4Es werden keine Spieler angezeigt!");
        }else{
            p.sendMessage("§aEs werden alle Spieler angezeigt!");
        }
    }

    public void toggleBuildMode(Player p){
        manager.getConnectionManager().toggleRowBuildMode(p);
        if(manager.getConnectionManager().callRowBuildMode(p) == 1){
            p.setGameMode(GameMode.CREATIVE);
            p.sendMessage("§aDu bist nun im Bau-Modus!");
        }else{
            p.setGameMode(GameMode.SURVIVAL);
            p.sendMessage("§4Du bist nun nicht mehr im Bau-Modus!");

        }
    }

    public void toggleFirstJoin(Player p){
        if(getBooleanOf(p,"FirstTime")){
            setBooleanOf(p,"FirstTime", false);
        }
    }

    public boolean isMatching(Player p){
        ConfigManager cm = new ConfigManager();
        String currentDbVersion = cm.getDBVersion();

        if(currentDbVersion.equals(getPlayerDBVersion(p))){ return true;}
        return false;
    }

    public void toggleAutoWarp(Player p){
        this.setFile(p,"config");
        if(hasAutoWarp(p)){
            setBooleanOf(p,"AutoWarp",false);
        }else {
            setBooleanOf(p,"AutoWarp",true);
        }
    }

    public void setAutoWarpLocation(Player p, String warp){
        if(warp != "" && warp != " "){
            WarpManager wm = new WarpManager();
            if(wm.exists(p,warp)) {
                setStringOf(p,"AutoWarpLocation", warp);
                p.sendMessage("§ader Warp wurde gesetzt");
            }else{
                p.sendMessage("§4Es muss ein gültiger Warp angegeben werden!");
            }
        }else{
            p.sendMessage("§4Es muss ein gültiger Warp angegeben werden!");
        }

    }

    public void setInf(Player p, String status){
        if(status == "enable"){
            setBooleanOf(p,"hasInf", true);
        }else{
            setBooleanOf(p,"hasInf", false);
        }

    }

    public void setMaxWarps(Player p, int warpCount){
        setIntegerOf(p,"maxWarps", warpCount);
        if(getMaxWarps(p) == warpCount){
            p.sendMessage("§aDeine Warps wurden auf §e" + getMaxWarps(p) + " §agesetzt");
        }
    }

    public void addWarpToCount(Player p){
        if(manager.getConnectionManager().callRowWarpUsed(p) <= manager.getConnectionManager().callRowWarpMax(p)){
           manager.getConnectionManager().setRowWarp(p);
        }
    }

    public void delWarpFromCount(Player p){
        if(manager.getConnectionManager().callRowWarpUsed(p) <= manager.getConnectionManager().callRowWarpMax(p)){
            manager.getConnectionManager().delRowWarp(p);
        }
    }

}
