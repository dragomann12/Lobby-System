package de.dragonhard.lobby.manager.other;

import de.dragonhard.lobby.manager.Managers;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateManager extends Managers {

    public String getCurrentDate(){

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String orginal = sdf.format(new Date());

        return orginal;
    }

    public String getPlayerDate(Player p){
        return this.getConnectionManager().callRowDate(p);
    }

    public boolean checkPlayerActivity(Player p){

        if(!getCurrentDate().equals(getPlayerDate(p))){
            CoinManager cm = new CoinManager();
            this.getConnectionManager().setRowCoins(p,this.getConnectionManager().callRowCoins(p) + 500);
            p.sendMessage("§aDu hast dein Geschenk erhalten!");
            this.getConnectionManager().setRowDate(p);
        }


        return false;
    }

}
