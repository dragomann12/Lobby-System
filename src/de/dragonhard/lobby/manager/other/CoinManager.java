package de.dragonhard.lobby.manager.other;

import org.bukkit.entity.Player;

@Deprecated
public class CoinManager extends PlayerConfigManager {
 private static final int defaultValue_Coins = 0;
 private static final int daily_amount = 500;

    public int getCurrentCoins(Player p){
        return this.getIntegerOf(p,"Coins");
    }

}
