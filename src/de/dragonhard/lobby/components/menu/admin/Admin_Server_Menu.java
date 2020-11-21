package de.dragonhard.lobby.components.menu.admin;

import de.dragonhard.lobby.components.ConsoleWriter;
import de.dragonhard.lobby.components.menu.Lobby_Inventory;
import de.dragonhard.lobby.manager.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.inventory.meta.ItemMeta;

public class Admin_Server_Menu extends Lobby_Inventory implements Listener {
    private Player p;

   private ConfigManager cm = new ConfigManager();
   private InventoryManager im = new InventoryManager();
   private GlobalWarpManager gwm = new GlobalWarpManager();
   private AcessManager acm = new AcessManager();
   private PlayerConfigManager pm = new PlayerConfigManager();

   private String menuName = "Admin_ServerMenu";

   private Material material;
    public void openInventory(Player p){
        this.p = p;

        if(acm.isBlacklisted(p)){
            p.sendMessage("§4Du wurdest vom Admin - Menu ausgeschlossen!");
            p.sendMessage("§4Bitte wende dich an das Team!");return;}

        int lineAmmount = 5;
        int i = 0;
        int slots = lineAmmount*9;

        this.setInventory("§"+ cm.getMenuTitleColor(menuName) + cm.getMenuTitle(menuName),lineAmmount);

        for(i = 0; i<slots; i++){

            if(cm.slotIsEnabled(menuName,i)){
                if(cm.getSlotTitle(menuName,i).contains(" /nonTag")){
                   String title =  cm.getSlotTitle(menuName,i).replace(" /nonTag","");
                    this.addItemToInventory(title, Material.getMaterial(cm.getSlotMaterial(menuName,i)),"§" + cm.getSlotTitleColor(menuName,i),i);
                }else{
                    this.addItemToInventory(cm.getSlotTitle(menuName,i) + this.getTag("Server-Event"), Material.getMaterial(cm.getSlotMaterial(menuName,i)),"§" + cm.getSlotTitleColor(menuName,i),i);
                }

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

    @EventHandler
    public void onModeClick(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();


        if(e.getInventory().getName().equals("§"+ cm.getMenuTitleColor(menuName) + cm.getMenuTitle(menuName))){

            if(e.getCurrentItem().getItemMeta().getDisplayName().contains(this.getTag("Server-Event")) ||
                    e.getCurrentItem().getItemMeta().getDisplayName().contains("Menu <<")) {

                switch(e.getClick()){

                    default:

                       switch(e.getSlot()) {

                           case 10:
                               p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);
                               ConsoleWriter.writeWithTag("The user " + p.getName() + " used the Admin-Item 'Stop' and closed the Server " + Bukkit.getServer().getServerName());
                               System.exit(0); break;

                           case 36:
                               p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);
                               Admin_Menu am = new Admin_Menu();
                               am.openInventory(p);break;

                       }break;

                }

            }

            return;
        }

    }

}
