package de.dragonhard.lobby.components.menu.player;

import de.dragonhard.lobby.components.menu.Lobby_Inventory;
import de.dragonhard.lobby.components.menu.shop.Shop_Coin_Menu;
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
   private String prefix = "#";
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
                    this.addSkullToInventory(p,title,"§" + cm.getSlotTitleColor(menuName,i),i);
                }else if(cm.getSlotTitle(menuName,i).contains(" " + prefix + InteractionType.VISUAL + " /id")){
                    String title = cm.getSlotTitle(menuName, i).replace(prefix + InteractionType.VISUAL + " /id","UUID: §e" + p.getUniqueId());
                    this.addItemToInventory(title, Material.getMaterial(cm.getSlotMaterial(menuName,i)),"§" + cm.getSlotTitleColor(menuName,i),i);
                }else if(cm.getSlotTitle(menuName,i).contains(prefix + InteractionType.PLACEHOLDER)){
                    String title = cm.getSlotTitle(menuName, i).replace(prefix + InteractionType.PLACEHOLDER,"");
                    this.addItemToInventory(title, Material.getMaterial(cm.getSlotMaterial(menuName,i)),"§" + cm.getSlotTitleColor(menuName,i),i);
                }else if(cm.getSlotTitle(menuName,i).contains(prefix + InteractionType.INTERACTION)){
                    String title = cm.getSlotTitle(menuName, i).replace(prefix + InteractionType.INTERACTION + " /shop", this.getTag("Coin-Shop"));
                    this.addItemToInventory(title, Material.getMaterial(cm.getSlotMaterial(menuName,i)),"§" + cm.getSlotTitleColor(menuName,i),i);
                }else if(cm.getSlotTitle(menuName,i).contains(prefix + InteractionType.VISUAL + " /ts")){
                    String title = cm.getSlotTitle(menuName, i).replace(prefix + InteractionType.VISUAL + " /ts", "Teamspeak: §e" + cm.getTeamspeak());
                    this.addItemToInventory(title, Material.getMaterial(cm.getSlotMaterial(menuName,i)),"§" + cm.getSlotTitleColor(menuName,i),i);
                }else if(cm.getSlotTitle(menuName,i).contains(prefix + InteractionType.VISUAL + " /dc")){
                    String title = cm.getSlotTitle(menuName, i).replace(prefix + InteractionType.VISUAL + " /dc", "Discord: §e" + cm.getDiscord());
                    this.addItemToInventory(title, Material.getMaterial(cm.getSlotMaterial(menuName,i)),"§" + cm.getSlotTitleColor(menuName,i),i);
                }else{
                    this.addItemToInventory(cm.getSlotTitle(menuName,i), Material.getMaterial(cm.getSlotMaterial(menuName,i)),"§" + cm.getSlotTitleColor(menuName,i),i);
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

                 if(e.getCurrentItem().getItemMeta().getDisplayName().contains(this.getTag("Coin-Shop"))) {
                     p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);
                     Shop_Coin_Menu scm = new Shop_Coin_Menu();
                     scm.openInventory(p);

                }else if(e.getCurrentItem().getItemMeta().getDisplayName().contains(this.getTag("Player-Settings"))){
                     p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);


                 }

            }

            return;
        }

    }

}
