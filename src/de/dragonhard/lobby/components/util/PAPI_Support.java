package de.dragonhard.lobby.components.util;

import de.dragonhard.lobby.manager.Managers;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class PAPI_Support extends PlaceholderExpansion {
    Managers manager = new Managers();

    public PAPI_Support(Plugin plugin) {

    }

    @Override
    public boolean persist(){
        return true;
    }

    @Override
    public boolean canRegister(){
        return true;
    }

    @Override
    public String getAuthor(){
        return "Dragonhard117";
    }

    @Override
    public String getIdentifier(){
        return "CoinsPapi";
    }

    @Override
    public String getVersion(){
        return "1.0";
    }


    public String onPlaceholderRequest(Player p, String identifier){

        if(p == null){
            return "";
        }

        if(identifier.equals("coins")){
            return "" + manager.getConnectionManager().callRowCoins(p);
        }

        return "";
    }
}
