package de.dragonhard.lobby.components.menu;

import de.dragonhard.lobby.components.menu.Lobby_Inventory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;



public class Menu extends Lobby_Inventory implements Listener {
    private Player p;



   private String menuName = "Menu";

    public void openInventory(Player p){
        this.p = p;

        int lineAmount = 5;
        int i = 0;
        int slots = lineAmount*9;

        this.setInventory("§",lineAmount);

        for(i = 0; i<slots; i++){



        }

        p.openInventory(this.getInventory());

    }


    @EventHandler
    public void onModeClick(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();


            return;
    }

}
