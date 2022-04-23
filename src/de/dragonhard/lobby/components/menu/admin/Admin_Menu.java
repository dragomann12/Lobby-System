package de.dragonhard.lobby.components.menu.admin;

import de.dragonhard.lobby.components.PermissionList;
import de.dragonhard.lobby.components.menu.Lobby_Inventory;
import de.dragonhard.lobby.manager.Managers;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;

public class Admin_Menu extends Lobby_Inventory implements Listener {

    private final String menuName = "Admin";
    private final Managers manager = new Managers();
    private static final ArrayList<Integer> wall_item_id = new ArrayList<Integer>();

    public void openInventory(Player p) {

        int lineAmount = 5;
        int i = 0;
        int slots = lineAmount * 9;

        this.setInventory("§" + manager.getConfigManager().getMenuTitleColor(menuName) + manager.getConfigManager().getMenuTitle(menuName), lineAmount);

        for (i = 0; i < slots; i++) {

            if (manager.getConfigManager().slotIsEnabled(menuName, i)) {
                if (manager.getConfigManager().getSlotTitle(menuName, i).contains(" /nonTag")) {
                    String title = manager.getConfigManager().getSlotTitle(menuName, i).replace(" /nonTag", "");
                    this.addItemToInventory(title, Material.getMaterial(manager.getConfigManager().getSlotMaterial(menuName, i)), "§" + manager.getConfigManager().getSlotTitleColor(menuName, i), i);
                } else {
                    this.addItemToInventory(manager.getConfigManager().getSlotTitle(menuName, i) + this.getTag("Item"), Material.getMaterial(manager.getConfigManager().getSlotMaterial(menuName, i)), "§" + manager.getConfigManager().getSlotTitleColor(menuName, i), i);
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

    private boolean hasPermission(Player p){
        return manager.getPluginWhitelistManager().isPluginDeveloper(p) ||
                manager.getPluginWhitelistManager().isOwner(p) ||
                manager.getPluginWhitelistManager().isAdmin(p) ||
                manager.getPluginWhitelistManager().isTeam_lead(p) ||
                manager.getPluginWhitelistManager().isDeveloper(p);
    }

    @EventHandler
    public void onModeClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        try {
            if (e.getInventory().getName().equals("§" + manager.getConfigManager().getMenuTitleColor(menuName) + manager.getConfigManager().getMenuTitle(menuName))) {

                if (e.getCurrentItem() == null) {return;} else{

                    if (e.getCurrentItem().getItemMeta().getDisplayName().contains(this.getTag("Item")) ||
                            e.getCurrentItem().getItemMeta().getDisplayName().contains("Menu <<")) {

                        p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);

                        switch (e.getClick()) {
                            default:
                                switch (e.getSlot()) {
                                    case 20:
                                        if (p.hasPermission(PermissionList.getPermission("Menu_Item", 2))) {

                                        } else {
                                            p.sendMessage("§4keine Berechtigung!");
                                        }
                                        break;
                                    case 22:
                                        if (hasPermission(p)&& p.getGameMode() != GameMode.CREATIVE) {
                                            manager.getMenuManager().getAdminServerMenu().openInventory(p);
                                        } else {

                                            p.sendMessage("§4keine Berechtigung!");
                                        }
                                        break;
                                    case 24:
                                        if (manager.getPluginWhitelistManager().isOwner(p) || manager.getPluginWhitelistManager().isPluginDeveloper(p)) {
                                            manager.getMenuManager().getAdminExternalMenu().openInventory(p);
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
