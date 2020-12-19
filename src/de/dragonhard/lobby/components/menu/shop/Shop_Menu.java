package de.dragonhard.lobby.components.menu.shop;

import de.dragonhard.lobby.components.PermissionList;
import de.dragonhard.lobby.components.menu.Lobby_Inventory;
import de.dragonhard.lobby.manager.*;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;

public class Shop_Menu extends Lobby_Inventory implements Listener {


    private Player p;
    private String menuName = "shop";
    private static ArrayList<Integer> wall_item_id = new ArrayList<Integer>();
    public void openInventory(Player p){
        AcessManager acm = new AcessManager();
        this.p = p;

        //if(acm.isBlacklisted(p)){
          //  p.sendMessage("§4Du wurdest vom Shop ausgeschlossen!");
           // p.sendMessage("§4Bitte wende dich an das Team!");return;}

        ConfigManager cm = new ConfigManager();

        int lineAmmount = 5;
        int i = 0;
        int slots = lineAmmount*9;

        this.setInventory("§"+ cm.getMenuTitleColor(menuName) + cm.getMenuTitle(menuName),lineAmmount);

        for(i = 0; i<slots; i++){

            if(cm.slotIsEnabled(menuName,i)){

                if(cm.getSlotTitle(menuName,i).contains(" /nonTag")) {
                    String title = cm.getSlotTitle(menuName, i).replace(" /nonTag", "");
                    this.addItemToInventory(title, Material.getMaterial(cm.getSlotMaterial(menuName, i)), "§" + cm.getSlotTitleColor(menuName, i), i);
                }else{
                    this.addItemToInventory(cm.getSlotTitle(menuName,i) + this.getTag("Shop"), Material.getMaterial(cm.getSlotMaterial(menuName,i)),"§" + cm.getSlotTitleColor(menuName,i),i);
                }


            }else{
                if(i <= 8){
                    this.addWallItemToInventory(p,i);
                }else {

                    if(wall_item_id.contains(i)){
                        this.addWallItemToInventory(p,i);
                    }else{
                        this.addGoldWallItemToInventory(p,i);
                    }

                }
            }

        }

        p.openInventory(this.getInventory());

    }

    public void addWallIDs(){

        wall_item_id.add(9);
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
        InventoryManager im = new InventoryManager();
        GlobalWarpManager gwm = new GlobalWarpManager();
        PlayerConfigManager pm = new PlayerConfigManager();
        ConfigManager cm = new ConfigManager();

        if(e.getInventory().getName().equals("§"+ cm.getMenuTitleColor(menuName) + cm.getMenuTitle(menuName))){

            if(e.getCurrentItem().getItemMeta().getDisplayName().contains(this.getTag("Shop"))) {

                if(!e.getClick().isShiftClick() && e.getSlot() == 20){

                    p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);
                    Shop_XP_Menu sxpm = new Shop_XP_Menu();
                    sxpm.openInventory(p);return;

                }else if(!e.getClick().isShiftClick() && e.getSlot() == 22){

                    // add here another Item

                }else if(!e.getClick().isShiftClick() && e.getSlot() == 24){

                    // add here another Item

                }

            }

            return;
        }

    }

}
