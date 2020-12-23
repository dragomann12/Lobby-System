package de.dragonhard.lobby.manager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateManager extends PlayerConfigManager{

    private final int dailyAmount = 50;

    private String getCurrentDate(){

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String orginal = sdf.format(new Date());

        return orginal;
    }

    private String getPlayerDate(Player p){
        this.setFile(p,"config");
        return this.getString("Online");
    }

    public void setPlayerDate(Player p){
        this.setFile(p,"config");
        this.set("Online", getCurrentDate());
    }

    public boolean checkPlayerActivity(Player p){
        this.setFile(p,"config");

        if(!getCurrentDate().equals(getPlayerDate(p))){
            PlayerConfigManager pm = new PlayerConfigManager();
            pm.setCoins(p, dailyAmount);
            p.sendMessage("§aDu hast dein Geschenk erhalten!");
            setPlayerDate(p);
        }


        return false;
    }

}
