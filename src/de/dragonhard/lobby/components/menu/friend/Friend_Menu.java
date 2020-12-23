package de.dragonhard.lobby.components.menu.friend;

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

import java.util.ArrayList;

public class Friend_Menu extends Lobby_Inventory implements Listener {
    private Player p;

   private ConfigManager cm = new ConfigManager();
   private InventoryManager im = new InventoryManager();
   private GlobalWarpManager gwm = new GlobalWarpManager();
   private PlayerConfigManager pm = new PlayerConfigManager();

   private String menuName = "Friend";
    private static ArrayList<Integer> wall_item_id = new ArrayList<Integer>();
   //private String title_slot_2 = cm.getSlotTitle(menuName,22).replace("-","_");
   //private String title_slot_3 = cm.getSlotTitle(menuName,24).replace("-","_");

    public void openInventory(Player p){
        this.p = p;

        int lineAmmount = 5;
        int i = 0;
        int slots = lineAmmount*9;

        this.setInventory("§"+ cm.getMenuTitleColor(menuName) + cm.getMenuTitle(menuName),lineAmmount);

        for(i = 0; i<slots; i++){

            if(cm.slotIsEnabled(menuName,i)){



            }else{

                    if(wall_item_id.contains(i)){
                        this.addWallItemToInventory(p,i);
                    }else{
                        this.addGoldWallItemToInventory(p,i);
                    }

            }

        }

        p.openInventory(this.getInventory());

    }

    public void addWallIDs(){

        for(int i = 0; i <=9; i++){
            wall_item_id.add(i);
        }

        wall_item_id.add(17);
        wall_item_id.add(18);
        wall_item_id.add(26);
        wall_item_id.add(27);

        for(int i = 35; i <=44; i++){
            wall_item_id.add(i);
        }

    }

    @EventHandler
    public void onModeClick(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();


        if(e.getInventory().getName().equals("§"+ cm.getMenuTitleColor(menuName) + cm.getMenuTitle(menuName))){
            if (e.getCurrentItem() == null) {return;} else{
                if(e.getCurrentItem().getItemMeta().getDisplayName().contains(this.getOnlineTag())
                        || e.getCurrentItem().getItemMeta().getDisplayName().contains(this.getOfflineTag()))
                {

                    String slot = cm.getSlotTitle(menuName,e.getSlot());
                    p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);

                }
            }


            return;
        }

    }

}
