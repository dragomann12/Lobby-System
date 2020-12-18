package de.dragonhard.lobby.components.menu;

import de.dragonhard.lobby.components.PermissionList;
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

public class Game_Menu extends Lobby_Inventory implements Listener {
    private Player p;

   private ConfigManager cm = new ConfigManager();
   private InventoryManager im = new InventoryManager();
   private GlobalWarpManager gwm = new GlobalWarpManager();
   private PlayerConfigManager pm = new PlayerConfigManager();

   private String menuName = "Game";
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

                if(cm.getSlotTitle(menuName,i).contains(" /nonTag")) {
                    String title = cm.getSlotTitle(menuName, i).replace(" /nonTag", "");
                    this.addItemToInventory(title, Material.getMaterial(cm.getSlotMaterial(menuName, i)), "§" + cm.getSlotTitleColor(menuName, i), i);
                }else if(cm.getSlotTitle(menuName,i).contains(" - Wartung")){
                    String title = cm.getSlotTitle(menuName, i).replace(" - Wartung", " §4Wartung");
                    this.addItemToInventory(title + this.getGameTag(), Material.getMaterial(cm.getSlotMaterial(menuName, i)), "§" + cm.getSlotTitleColor(menuName, i), i);
                }else if(cm.getSlotTitle(menuName,i).contains(" - offline")){
                    String title = cm.getSlotTitle(menuName, i).replace(" - offline", " §4offline");
                    this.addItemToInventory(title + this.getGameTag(), Material.getMaterial(cm.getSlotMaterial(menuName, i)), "§" + cm.getSlotTitleColor(menuName, i), i);
                }else if(cm.getSlotTitle(menuName,i).contains(" - err")){
                    String title = cm.getSlotTitle(menuName, i).replace(" - err", " §4offline §e[§4Fehler§e]");
                    this.addItemToInventory(title + this.getGameTag(), Material.getMaterial(cm.getSlotMaterial(menuName, i)), "§" + cm.getSlotTitleColor(menuName, i), i);
                }else{
                    this.addItemToInventory(cm.getSlotTitle(menuName,i) + this.getGameTag(), Material.getMaterial(cm.getSlotMaterial(menuName,i)),"§" + cm.getSlotTitleColor(menuName,i),i);
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

            if(e.getCurrentItem().getItemMeta().getDisplayName().contains(this.getGameTag())) {

                String slot = cm.getSlotTitle(menuName,e.getSlot()).replace("-","_");

                if(slot.contains("?")){p.sendMessage("§4Der Modus ist nicht verfügbar!"); return;}
                else if(slot.contains("Wartung")){p.sendMessage("§4Der Modus ist wegen arbeiten offline!"); return;}
                else if(slot.contains("offline")){p.sendMessage("§4Der Modus ist derzeit offline!"); return;}
                else if(slot.contains("err")){p.sendMessage("§4Der Modus ist aufgrund eines Problems offline!"); return;}

                p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);
                gwm.teleportPlayer(p,slot);

            }

            return;
        }

    }

}
