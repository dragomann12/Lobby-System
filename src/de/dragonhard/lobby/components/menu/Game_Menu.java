package de.dragonhard.lobby.components.menu;

import de.dragonhard.lobby.manager.Managers;
import de.dragonhard.lobby.manager.other.*;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;

public class Game_Menu extends Lobby_Inventory implements Listener {
    private Player p;
    private final Managers manager = new Managers();

   private ConfigManager cm = new ConfigManager();
   private GlobalWarpManager gwm = new GlobalWarpManager();

   private final String menuName = "Game";
    private static final ArrayList<Integer> wall_item_id = new ArrayList<Integer>();

    public void openInventory(Player p){
        this.p = p;

        int lineAmount = 5;
        int i = 0;
        int slots = lineAmount*9;

        this.setInventory("§"+ cm.getMenuTitleColor(menuName) + cm.getMenuTitle(menuName),lineAmount);

        for(i = 0; i<slots; i++){

            if(cm.slotIsEnabled(menuName,i)){

                if(cm.getSlotTitle(menuName,i).contains(" /nonTag")) {
                    String title = cm.getSlotTitle(menuName, i).replace(" /nonTag", "");
                    this.addItemToInventory(title, Material.getMaterial(cm.getSlotMaterial(menuName, i)), "§" + cm.getSlotTitleColor(menuName, i), i);
                }else if(cm.getSlotTitle(menuName,i).contains(" /Wartung")){
                    String title = cm.getSlotTitle(menuName, i).replace(" /Wartung", " §4Wartung");
                    this.addItemToInventory(title + this.getGameTag(), Material.getMaterial(cm.getSlotMaterial(menuName, i)), "§" + cm.getSlotTitleColor(menuName, i), i);
                }else if(cm.getSlotTitle(menuName,i).contains(" /offline")){
                    String title = cm.getSlotTitle(menuName, i).replace(" /offline", " §4offline");
                    this.addItemToInventory(title + this.getGameTag(), Material.getMaterial(cm.getSlotMaterial(menuName, i)), "§" + cm.getSlotTitleColor(menuName, i), i);
                }else if(cm.getSlotTitle(menuName,i).contains(" /err")){
                    String title = cm.getSlotTitle(menuName, i).replace(" /err", " §4offline §e[§4Fehler§e]");
                    this.addItemToInventory(title + this.getGameTag(), Material.getMaterial(cm.getSlotMaterial(menuName, i)), "§" + cm.getSlotTitleColor(menuName, i), i);
                }else if(cm.getSlotTitle(menuName,i).contains(" /b")){
                    String title = cm.getSlotTitle(menuName, i).replace(" /b", this.getClosedBetaTestTag());
                    this.addItemToInventory(title + this.getGameTag(), Material.getMaterial(cm.getSlotMaterial(menuName, i)), "§" + cm.getSlotTitleColor(menuName, i), i);
                }else if(cm.getSlotTitle(menuName,i).contains(" /a")){
                    String title = cm.getSlotTitle(menuName, i).replace(" /a", this.getTag("§4Test"));
                    this.addItemToInventory(title + this.getGameTag(), Material.getMaterial(cm.getSlotMaterial(menuName, i)), "§" + cm.getSlotTitleColor(menuName, i), i);
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

        if(e.getInventory().getName().equals("§"+ cm.getMenuTitleColor(menuName) + cm.getMenuTitle(menuName))){

            if (e.getCurrentItem() == null) {return;} else{

                 if(e.getCurrentItem().getItemMeta().getDisplayName().contains(this.getGameTag())) {

                    String slot = cm.getSlotTitle(menuName,e.getSlot()).replace("-","_").replace(" /a","").replace(" /b","");
                    Player p = (Player) e.getWhoClicked();

                    if(slot.contains("?")){p.sendMessage("§4Der Modus ist nicht verfügbar!"); return;}
                    else if(slot.contains("/Wartung")){ p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F); p.sendMessage("§4Der Modus ist wegen arbeiten offline!"); return;}
                    else if(slot.contains("/offline")){ p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F); p.sendMessage("§4Der Modus ist derzeit offline!"); return;}
                    else if(slot.contains("/err")){ p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F); p.sendMessage("§4Der Modus ist aufgrund eines Problems offline!"); return;}

                    //@TODO code cleanup later?(all Menus)

                    else if(slot.contains("/b")){ if(manager.getPluginWhitelistManager().isTester(p) || manager.getPluginWhitelistManager().isDeveloper(p) || manager.getPluginWhitelistManager().isPluginDeveloper(p) || manager.getPluginWhitelistManager().isOwner(p)){
                        p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);
                        manager.getGlobalWarpManager().teleportPlayer(p,slot); return;
                    }else{
                        p.sendMessage("§4Der Modus ist nur für Beta-Tester"); return;}}

                    else if(slot.contains("/a")){if(manager.getPluginWhitelistManager().isDeveloper(p) || manager.getPluginWhitelistManager().isPluginDeveloper(p) || manager.getPluginWhitelistManager().isOwner(p)){
                        p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);
                        manager.getGlobalWarpManager().teleportPlayer(p,slot); return;
                    }else{
                        p.sendMessage("§4Der Modus ist nur für Alpha-Tester"); return;}}

                    p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);
                    manager.getGlobalWarpManager().teleportPlayer(p,slot);
                }


            }

            return;
        }

    }

}
