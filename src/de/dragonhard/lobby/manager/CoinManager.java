package de.dragonhard.lobby.manager;

import org.bukkit.entity.Player;

public class CoinManager extends PlayerConfigManager {
 private static int maxCoins = 1000000;
 private static final int defaultValue_Coins = 0;

public void addCoins(Player p, int value){
    if(this.getCoins(p) == maxCoins) {return;}
    this.setCoins(p,this.getCoins(p) + value);
    getMessage(p,"add", value);
}

public void removeCoins(Player p, int value){
if(this.getCoins(p)< value) {
    getMessage(p,"noMoney",0);
    return;
}
    this.setCoins(p,this.getCoins(p) - value);
    getMessage(p,"add", value);

}

    public void setCoins(Player p, int value){
        this.setCoins(p, value);
        getMessage(p,"add", value);
    }

    public int getCurrentCoins(Player p){
    return this.getCoins(p);
    }

public void getMessage(Player p, String type, int value){

    switch(type){

        case "add": p.sendMessage("§bDu hast §a" + value + " CC §bbekommen du hast jetzt §a" + this.getCoins(p) + " CC");break;
        case "buy": p.sendMessage("§bDu hast §a" + value + " CC §bgezahlt");break;
        case "noMoney": p.sendMessage("§4Deine Chaos-Coins(CC) reichen nicht!");break;
        case "remove": p.sendMessage("§bDu hast §e" + value + " CC §bverloren du hast jetzt §e" + this.getCoins(p) + " CC");break;
        case "set": p.sendMessage("§bDeine Chaos-Coins wurden auf §a" + this.getCoins(p) + " CC §bgesetzt");break;

    }

}

}
