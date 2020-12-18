package de.dragonhard.lobby.components.menu.creativ;

import de.dragonhard.lobby.components.menu.Lobby_Inventory;
import de.dragonhard.lobby.manager.ConfigManager;
import de.dragonhard.lobby.manager.GlobalWarpManager;
import de.dragonhard.lobby.manager.InventoryManager;
import de.dragonhard.lobby.manager.PlayerConfigManager;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class Creativ_Menu extends Lobby_Inventory implements Listener {

    private Player p;
    private String menuName = "creativ";
    public void openInventory(Player p){
        this.p = p;
        ConfigManager cm = new ConfigManager();

        int lineAmmount = 5;
        int i = 0;
        int slots = lineAmmount*9;

        this.setInventory("§"+ cm.getMenuTitleColor(menuName) + cm.getMenuTitle(menuName),lineAmmount);

        for(i = 0; i<slots; i++){

          if(cm.slotIsEnabled(menuName,i)){

                  this.addItemToInventory(cm.getSlotTitle(menuName,i), Material.getMaterial(cm.getSlotMaterial(menuName,i)),"§" + cm.getSlotTitleColor(menuName,i),i);

          }else{
            if(i <= 8){
                this.addWallItemToInventory(p,i);
              }else {
                switch(i){
                    case 9: this.addWallItemToInventory(p,i);break;
                    case 17: this.addWallItemToInventory(p,i);break;
                    case 18: this.addWallItemToInventory(p,i);break;
                    case 26: this.addWallItemToInventory(p,i);break;
                    case 27: this.addWallItemToInventory(p,i);break;
                    case 35: this.addWallItemToInventory(p,i);break;
                    case 36: this.addWallItemToInventory(p,i);break;
                    case 37: this.addWallItemToInventory(p,i);break;
                    case 38: this.addWallItemToInventory(p,i);break;
                    case 39: this.addWallItemToInventory(p,i);break;
                    case 40: this.addWallItemToInventory(p,i);break;
                    case 41: this.addWallItemToInventory(p,i);break;
                    case 42: this.addWallItemToInventory(p,i);break;
                    case 43: this.addWallItemToInventory(p,i);break;
                    case 44: this.addWallItemToInventory(p,i);break;
                    default: this.addGoldWallItemToInventory(p,i);break;
                }
            }
          }

        }

        p.openInventory(this.getInventory());

    }

    public boolean loadItems(){
        return true;
    }

}
