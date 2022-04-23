package de.dragonhard.lobby.components.menu;

import de.dragonhard.lobby.manager.Managers;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class Menu extends Lobby_Inventory implements Listener {
    private Player p;

    public Menu(String title, int lines, boolean isBackpack){

    }

    public void openInventory(Player p){
        this.p = p;


        int lineAmount = 5;
        int i = 0;
        int slots = lineAmount*9;

        this.setInventory("§"+ "",lineAmount);

        for(i = 0; i<slots; i++) {


        }

        p.openInventory(this.getInventory());

    }

    @EventHandler
    public void onModeClick(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();

        if(e.getInventory().getName().equals("§")){
            if (e.getCurrentItem() != null) {
                if(e.getCurrentItem().getItemMeta().getDisplayName().contains(this.getTag("Item"))) {

                    p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);

                }
            }
        }

    }

}
