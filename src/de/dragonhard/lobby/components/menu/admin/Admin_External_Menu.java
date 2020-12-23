package de.dragonhard.lobby.components.menu.admin;

import de.dragonhard.lobby.components.ConsoleWriter;
import de.dragonhard.lobby.components.PermissionList;
import de.dragonhard.lobby.components.menu.Lobby_Inventory;
import de.dragonhard.lobby.manager.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;

public class Admin_External_Menu extends Lobby_Inventory implements Listener {
    private Player p;

   private ConfigManager cm = new ConfigManager();
   private InventoryManager im = new InventoryManager();
   private GlobalWarpManager gwm = new GlobalWarpManager();
   private AcessManager acm = new AcessManager();
   private PlayerConfigManager pm = new PlayerConfigManager();
   private PluginWithlistManager pwm = new PluginWithlistManager();

   private String menuName = "Admin_External";
    private static ArrayList<Integer> wall_item_id = new ArrayList<Integer>();
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
                       this.addItemToInventory(title, Material.getMaterial(cm.getSlotMaterial(menuName, i)), "§" + cm.getSlotTitleColor(menuName, i), i);
                }else{
                    this.addItemToInventory(cm.getSlotTitle(menuName,i) + this.getTag("Server-Event"), Material.getMaterial(cm.getSlotMaterial(menuName,i)),"§" + cm.getSlotTitleColor(menuName,i),i);
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
                if(e.getCurrentItem().getItemMeta().getDisplayName().contains(this.getTag("Server-Event")) ||
                        e.getCurrentItem().getItemMeta().getDisplayName().contains("Menu <<")) {

                    switch(e.getClick()){

                        default:

                            switch(e.getSlot()) {

                                default:
                                    p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);

                                    if(p.hasPermission(PermissionList.getPermission("external",0)) && pwm.isOwner(p) || pwm.isPluginDeveloper(p)) {// controlpanel
                                        String title = cm.getSlotTitle(menuName, e.getSlot());

                                        switch (title) {
                                            case "Bungeecord Panel":Bukkit.getServer().dispatchCommand(p,"controlpanel");break;
                                        }

                                    }
                                    break;

                                case 36:
                                    p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);
                                    Admin_Menu am = new Admin_Menu();
                                    am.openInventory(p);break;

                            }break;

                    }

                }
            }


            return;
        }

    }

}
