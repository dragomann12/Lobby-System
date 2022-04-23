package de.dragonhard.lobby.components.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class Hunger_Event implements Listener {
    @EventHandler
    public void onHunger(FoodLevelChangeEvent e){
        e.setFoodLevel(20);
        e.setCancelled(true);
    }

}
