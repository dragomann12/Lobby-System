package de.dragonhard.lobby.components.menu.shop;

import de.dragonhard.lobby.components.menu.Lobby_Inventory;
import de.dragonhard.lobby.manager.*;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class Shop_XP_Menu extends Lobby_Inventory implements Listener {


    private Player p;
    private String menuName = "XP_shopMenu";
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
                    this.addItemToInventory(cm.getSlotTitle(menuName,i) + this.getTag("Item"), Material.getMaterial(cm.getSlotMaterial(menuName,i)),"§" + cm.getSlotTitleColor(menuName,i),i);
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

        if(e.getInventory().getName().equals("§"+ cm.getMenuTitleColor(menuName) + cm.getMenuTitle(menuName))){

            if(e.getCurrentItem().getItemMeta().getDisplayName().contains(this.getTag("Item"))) {

                switch(e.getClick()){

                    default:

                        switch(e.getSlot()){

                            case 10:
                                p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);
                                break;
                            case 12:
                                p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);
                                break;
                            case 13:
                                p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);
                                break;
                            case 14:
                                p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);
                                break;
                            case 15:
                                p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);
                                break;
                            case 16:
                                p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);
                                break;
                            case 19:
                                p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);
                                break;
                            case 20:
                                p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);
                                break;
                            case 21:
                                p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);
                                break;
                            case 22:
                                p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);
                                break;
                            case 23:
                                p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);
                                break;
                            case 24:
                                p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);
                                break;
                            case 25:
                                p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);
                                break;
                            case 28:
                                p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);
                                break;
                            case 29:
                                p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);
                                break;
                            case 30:
                                p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);
                                break;
                            case 31:
                                p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);
                                break;
                            case 32:
                                p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);
                                break;
                            case 33:
                                p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);
                                break;
                            case 34:
                                p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);
                                break;

                        }
                        break;

                }
                return;
             
            }

            return;
        }

    }

}
