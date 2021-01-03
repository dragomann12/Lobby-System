package de.dragonhard.lobby.manager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateManager extends PlayerConfigManager{

    private String getCurrentDate(){

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String orginal = sdf.format(new Date());

        return orginal;
    }

    public String getPlayerDate(Player p){
        this.setFile(p,"config");
        return this.getString("Online");
    }

    public void setPlayerDate(Player p){
        this.setFile(p,"config");
        this.set("Online", getCurrentDate());
    }

    public boolean checkPlayerActivity(Player p){

        if(!getCurrentDate().equals(getPlayerDate(p))){
            CoinManager cm = new CoinManager();
            cm.addDailyCoins(p);
            p.sendMessage("§aDu hast dein Geschenk erhalten!");
            setPlayerDate(p);
        }


        return false;
    }

}
