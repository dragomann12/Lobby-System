package de.dragonhard.lobby.components.menu.player;

import de.dragonhard.lobby.components.menu.Lobby_Inventory;
import de.dragonhard.lobby.components.menu.shop.Shop_Menu;
import de.dragonhard.lobby.manager.*;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;

public class Player_Menu extends Lobby_Inventory implements Listener {
    private Player p;

   private ConfigManager cm = new ConfigManager();
   private InventoryManager im = new InventoryManager();
   private GlobalWarpManager gwm = new GlobalWarpManager();
   private PlayerConfigManager pm = new PlayerConfigManager();
   private PluginWithlistManager pwm = new PluginWithlistManager();
   private String menuName = "Player";
   private String prefix = "@";
   private static ArrayList<Integer> wall_item_id = new ArrayList<Integer>();


    public void openInventory(Player p){
        this.p = p;

        int lineAmmount = 5;
        int i = 0;
        int slots = lineAmmount*9;

        this.setInventory("§"+ cm.getMenuTitleColor(menuName) + cm.getMenuTitle(menuName),lineAmmount);

        for(i = 0; i<slots; i++){

            if(cm.slotIsEnabled(menuName,i)){

                if(cm.getSlotTitle(menuName,i).contains(" /nonTag")) {
                    String title = cm.getSlotTitle(menuName, i).replace(" /nonTag", "");
                    this.addItemToInventory(title, Material.getMaterial(cm.getSlotMaterial(menuName, i)), "§" + cm.getSlotTitleColor(menuName, i), i);
                }else if(cm.getSlotTitle(menuName,i).contains(" " + prefix + InteractionType.VISUAL + " /coins")){
                    String title = cm.getSlotTitle(menuName, i).replace(prefix + InteractionType.VISUAL + " /coins","Chaos-Coins: §e" + pm.getCoins(p));
                    this.addItemToInventory(title, Material.getMaterial(cm.getSlotMaterial(menuName,i)),"§" + cm.getSlotTitleColor(menuName,i),i);
                }else if(cm.getSlotTitle(menuName,i).contains(" " + prefix + InteractionType.VISUAL + " /nick")){
                    String title = cm.getSlotTitle(menuName, i).replace(prefix + InteractionType.VISUAL + " /nick","Name: §e" + p.getName());
                    this.addSkullToInventory(title,"§" + cm.getSlotTitleColor(menuName,i),i);
                }else if(cm.getSlotTitle(menuName,i).contains(" " + prefix + InteractionType.VISUAL + " /id")){
                    String title = cm.getSlotTitle(menuName, i).replace(prefix + InteractionType.VISUAL + " /id","UUID: §e" + p.getUniqueId());
                    this.addItemToInventory(title, Material.getMaterial(cm.getSlotMaterial(menuName,i)),"§" + cm.getSlotTitleColor(menuName,i),i);
                }else if(cm.getSlotTitle(menuName,i).contains(prefix + InteractionType.PLACEHOLDER + " /placeholder")){
                    String title = cm.getSlotTitle(menuName, i).replace(prefix + InteractionType.PLACEHOLDER + " /placeholder","");
                    this.addItemToInventory(title, Material.getMaterial(cm.getSlotMaterial(menuName,i)),"§" + cm.getSlotTitleColor(menuName,i),i);
                }else{
                    this.addItemToInventory(cm.getSlotTitle(menuName,i) + this.getGameTag(), Material.getMaterial(cm.getSlotMaterial(menuName,i)),"§" + cm.getSlotTitleColor(menuName,i),i);
                }

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

                 if(cm.getSlotTitle(menuName,e.getSlot()).contains(cm.getSlotTitle(menuName, e.getSlot()) + InteractionType.INTERACTION)) {
                     p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);
                     if(cm.getSlotTitle(menuName,e.getSlot()).contains("Shop")){
                         Shop_Menu shm = new Shop_Menu();
                         shm.openInventory(p);
                     }

                }

            }

            return;
        }

    }

}
