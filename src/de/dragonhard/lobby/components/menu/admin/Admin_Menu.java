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
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.permissions.Permission;

import java.util.ArrayList;

public class Admin_Menu extends Lobby_Inventory implements Listener {
    private Player p;
    private String menuName = "Admin";
    PluginWithlistManager pwm = new PluginWithlistManager();
    private static ArrayList<Integer> wall_item_id = new ArrayList<Integer>();

    public void openInventory(Player p) {
        this.p = p;

        ConfigManager cm = new ConfigManager();
        AcessManager acm = new AcessManager();

        if (acm.isBlacklisted(p)) {
            p.sendMessage("§4Du wurdest vom Admin - Menu ausgeschlossen!");
            p.sendMessage("§4Bitte wende dich an das Team!");
            return;
        }

        int lineAmmount = 5;
        int i = 0;
        int slots = lineAmmount * 9;

        this.setInventory("§" + cm.getMenuTitleColor(menuName) + cm.getMenuTitle(menuName), lineAmmount);

        for (i = 0; i < slots; i++) {

            if (cm.slotIsEnabled(menuName, i)) {
                if (cm.getSlotTitle(menuName, i).contains(" /nonTag")) {
                    String title = cm.getSlotTitle(menuName, i).replace(" /nonTag", "");
                    this.addItemToInventory(title, Material.getMaterial(cm.getSlotMaterial(menuName, i)), "§" + cm.getSlotTitleColor(menuName, i), i);
                } else {
                    this.addItemToInventory(cm.getSlotTitle(menuName, i) + this.getTag("Item"), Material.getMaterial(cm.getSlotMaterial(menuName, i)), "§" + cm.getSlotTitleColor(menuName, i), i);
                }
            } else {

                if (wall_item_id.contains(i)) {
                    this.addWallItemToInventory(p, i);
                } else {
                    this.addGoldWallItemToInventory(p, i);
                }


            }

        }

        p.openInventory(this.getInventory());

    }

    public void addWallIDs() {

        for (int i = 0; i <= 9; i++) {
            wall_item_id.add(i);
        }

        wall_item_id.add(17);
        wall_item_id.add(18);
        wall_item_id.add(26);
        wall_item_id.add(27);

        for (int i = 35; i <= 44; i++) {
            wall_item_id.add(i);
        }

    }

    @EventHandler
    public void onModeClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        InventoryManager im = new InventoryManager();
        GlobalWarpManager gwm = new GlobalWarpManager();
        PlayerConfigManager pm = new PlayerConfigManager();
        ConfigManager cm = new ConfigManager();
        try {
            if (e.getInventory().getName().equals("§" + cm.getMenuTitleColor(menuName) + cm.getMenuTitle(menuName))) {

                if (e.getCurrentItem() == null) {return;} else{

                    if (e.getCurrentItem().getItemMeta().getDisplayName().contains(this.getTag("Item")) ||
                            e.getCurrentItem().getItemMeta().getDisplayName().contains("Menu <<")) {

                        p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);

                        switch (e.getClick()) {
                            default:
                                switch (e.getSlot()) {
                                    case 20:
                                        if (p.hasPermission(PermissionList.getPermission("Menu_Item", 2))) {
                                            pm.toggleBuildMode(p);
                                            im.clearInv(p);
                                        } else {
                                            p.sendMessage("§4keine Berechtigung!");
                                        }
                                        break;
                                    case 22:
                                        if (p.hasPermission(PermissionList.getPermission("Menu_Item", 3)) && p.getGameMode() != GameMode.CREATIVE) {
                                            Admin_Server_Menu asm = new Admin_Server_Menu();
                                            asm.openInventory(p);
                                        } else {

                                            p.sendMessage("§4keine Berechtigung!");
                                        }
                                        break;
                                    case 24:
                                        if (pwm.isOwner(p) || pwm.isPluginDeveloper(p)) {
                                            Admin_External_Menu aem = new Admin_External_Menu();
                                            aem.openInventory(p);
                                        } else {
                                            p.sendMessage("§4keine Berechtigung!");
                                        }
                                        break;

                                }
                                break;
                        }

                    }

                }


                }
            }catch(Exception ex){

            }


        }
}
