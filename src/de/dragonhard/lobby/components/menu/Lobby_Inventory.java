package de.dragonhard.lobby.components.menu;

import de.dragonhard.lobby.manager.other.InventoryManager;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

public class Lobby_Inventory {
    private String title = "";
    private int lineAmount = 1;
    private Inventory inventory;
    private InventoryManager im = new InventoryManager();

    public void setInventory(String title, int lineAmount){
        this.title = title;
        this.lineAmount = lineAmount;
        inventory = Bukkit.getServer().createInventory(null, lineAmount*9,title);
    }

    public Inventory getInventory(){
        return inventory;
    }

}
