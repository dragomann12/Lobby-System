package de.dragonhard.lobby.components.menu;

import de.dragonhard.lobby.components.menu.Lobby_Inventory;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;


public class Menu implements Listener {
    private final String name;
    private final String color;
    private final Player player;
    private final int lines;

    private Inventory inventory = null;



   public Menu(String name, String color, int lines, Player player){

       this.name = name;
       this.color = color;
       this.lines = lines;
       this.player = player;
   }

    public void openInventory(){

        int slots = lines*9;

        for(int i = 0; i < slots; i++){


        }

        inventory = Bukkit.getServer().createInventory(null, lines*9, name);
        player.openInventory(inventory);

    }


    @EventHandler
    public void onModeClick(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();

    }

}
