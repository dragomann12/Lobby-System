package de.dragonhard.lobby.components.menu.admin;

import de.dragonhard.lobby.components.PermissionList;
import de.dragonhard.lobby.components.menu.Lobby_Inventory;
import de.dragonhard.lobby.manager.*;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.permissions.Permission;

public class Admin_Menu extends Lobby_Inventory implements Listener {
    private Player p;
    private String menuName = "Admin";
    public void openInventory(Player p){
        this.p = p;

        ConfigManager cm = new ConfigManager();
        AcessManager acm = new AcessManager();

        if(acm.isBlacklisted(p)){
            p.sendMessage("§4Du wurdest vom Admin - Menu ausgeschlossen!");
            p.sendMessage("§4Bitte wende dich an das Team!");return;}

        int lineAmmount = 5;
        int i = 0;
        int slots = lineAmmount*9;

        this.setInventory("§"+ cm.getMenuTitleColor(menuName) + cm.getMenuTitle(menuName),lineAmmount);

        for(i = 0; i<slots; i++){

            if(cm.slotIsEnabled(menuName,i)){
                if(cm.getSlotTitle(menuName,i).contains(" /nonTag")) {
                    String title = cm.getSlotTitle(menuName, i).replace(" /nonTag", "");
                    this.addItemToInventory(title, Material.getMaterial(cm.getSlotMaterial(menuName, i)), "§" + cm.getSlotTitleColor(menuName, i), i);
                }else {
                    this.addItemToInventory(cm.getSlotTitle(menuName, i) + this.getTag("Item"), Material.getMaterial(cm.getSlotMaterial(menuName, i)), "§" + cm.getSlotTitleColor(menuName, i), i);
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
        InventoryManager im = new InventoryManager();
        GlobalWarpManager gwm = new GlobalWarpManager();
        PlayerConfigManager pm = new PlayerConfigManager();
        ConfigManager cm = new ConfigManager();
        try{
            if(e.getInventory().getName().equals("§"+ cm.getMenuTitleColor(menuName) + cm.getMenuTitle(menuName))) {

                if (e.getCurrentItem().getItemMeta().getDisplayName().contains(this.getTag("Item")) ||
                        e.getCurrentItem().getItemMeta().getDisplayName().contains("Menu <<")) {

                        p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);

                        switch(e.getClick()){
                            default:
                                switch(e.getSlot()){
                                    case 20:
                                        if(p.hasPermission(PermissionList.getPermission("Menu_Item",2))){
                                            pm.toggleBuildMode(p);
                                            im.clearInv(p);
                                        } else{
                                            p.sendMessage("§4keine Berechtigung!");
                                        }break;
                                    case 22:
                                        if(p.hasPermission(PermissionList.getPermission("Menu_Item",3)) && p.getGameMode() != GameMode.CREATIVE){
                                            Admin_Server_Menu asm = new Admin_Server_Menu();
                                            asm.openInventory(p);
                                        } else{
                                            p.sendMessage("§4keine Berechtigung!");
                                        }break;
                                    case 24:break;

                                }break;
                        }

                }
            }
        }catch (Exception ex){

        }

    }

}
