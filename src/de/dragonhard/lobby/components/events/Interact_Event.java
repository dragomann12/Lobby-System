package de.dragonhard.lobby.components.events;

import de.dragonhard.lobby.components.PermissionList;
import de.dragonhard.lobby.components.menu.*;
import de.dragonhard.lobby.components.menu.admin.Admin_Menu;
import de.dragonhard.lobby.components.menu.friend.Friend_Menu;
import de.dragonhard.lobby.components.menu.player.Player_Menu;
import de.dragonhard.lobby.components.menu.shop.Shop_Menu;
import de.dragonhard.lobby.manager.ConfigManager;
import de.dragonhard.lobby.manager.InventoryManager;
import de.dragonhard.lobby.manager.PlayerConfigManager;
import de.dragonhard.lobby.manager.PluginWithlistManager;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class Interact_Event implements Listener {

    @EventHandler
    public static void onPlayerInteraction(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        p.setNoDamageTicks(999);

        PlayerConfigManager pm = new PlayerConfigManager();
        PluginWithlistManager pwm = new PluginWithlistManager();
        ConfigManager cm = new ConfigManager();
        InventoryManager im = new InventoryManager(),
                menu = new InventoryManager();

        if(Event_Blocker.isMenu()){
            e.setCancelled(false);
            return;
        }

        if (pm.isBuildModeEnabled(p)) {
            e.setCancelled(false);
            return;
        } else {
            e.setCancelled(true);
            im.clearInv(p);
        }

        menu.createItem(p,Material.getMaterial(cm.getHotbarMaterial("Lobby")),cm.getHotbarTitleColor("Lobby") + cm.getHotbarTitle("Lobby"),0);
        menu.createItem(p, Material.getMaterial(cm.getHotbarMaterial("shop")),cm.getHotbarTitleColor("shop") + cm.getHotbarTitle("shop"),1);
        menu.createItem(p, Material.STAINED_GLASS_PANE,"§4 ",2);

        if(p.hasPermission(PermissionList.getPermission("Menu",0)) && pwm.isAdmin(p) || pwm.isOwner(p) || pwm.isPluginDeveloper(p)){
            menu.createItem(p, Material.getMaterial(cm.getHotbarMaterial("Admin")),cm.getHotbarTitleColor("Admin") + cm.getHotbarTitle("Admin"),3);
        }else{
            menu.createItem(p, Material.STAINED_GLASS_PANE,"§3 ",3);
        }

        menu.createItem(p, Material.getMaterial(cm.getHotbarMaterial("Game")),cm.getHotbarTitleColor("Game") + cm.getHotbarTitle("Game"),4);

        if(p.hasPermission(PermissionList.getPermission("Menu",0)) && pwm.isOwner(p) || pwm.isPluginDeveloper(p)){
            menu.createItem(p, Material.getMaterial(cm.getHotbarMaterial("Settings")),cm.getHotbarTitleColor("Settings") +cm.getHotbarTitle("Settings"),5);
        }else{
            menu.createItem(p, Material.STAINED_GLASS_PANE,"§2 ",5);
        }

        menu.createItem(p, Material.STAINED_GLASS_PANE,"§1 ",6);
        menu.createItem(p, pm.getHideStatusMaterial(p),"§5Spieler verstecken",7);
        menu.addSkull(p,"§5" + cm.getHotbarTitle("Player"),cm.getHotbarTitleColor("Player"),8);

            try {
                if (e.getItem().getType().equals(Material.COMPASS)) {

                } else if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                    if (e.getItem().getType().equals(pm.getHideStatusMaterial(p))) { // hide item
                        p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);

                            p.getInventory().remove(pm.getHideStatusMaterial(p));

                        pm.toggleHideStatus(p);
                        menu.createItem(p, pm.getHideStatusMaterial(p), "§5Spieler verstecken", 7);


                    } else if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                        if (e.getItem().getType().equals(Material.getMaterial(cm.getHotbarMaterial("Admin")))) {
                            p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);                  // Admin
                            Admin_Menu am = new Admin_Menu();
                            am.openInventory(p);

                        } else if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                            if (e.getItem().getType().equals(Material.getMaterial(cm.getHotbarMaterial("Settings")))) {
                                p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);              //Settings
                                Settings_Menu stm = new Settings_Menu();
                                stm.openInventory(p);

                            } else if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                                if (e.getItem().getType().equals(Material.getMaterial(cm.getHotbarMaterial("Game")))) {
                                    p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);      //Gamemodi
                                    Game_Menu gm = new Game_Menu();
                                    gm.openInventory(p);

                                } else if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                                    if (e.getItem().getType().equals(Material.getMaterial(cm.getHotbarMaterial("Lobby")))) {
                                        p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);  //Lobby
                                        Lobby_Menu lm = new Lobby_Menu();
                                        lm.openInventory(p);

                                    } else if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                                        if (e.getItem().getType().equals(Material.SKULL_ITEM)) {
                                            p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);  //Player
                                            Player_Menu plm = new Player_Menu();
                                            plm.openInventory(p);

                                        } else if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                                            if (e.getItem().getType().equals(Material.getMaterial(cm.getHotbarMaterial("shop")))) {
                                                p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);  //shop
                                                Shop_Menu shm = new Shop_Menu();
                                                shm.openInventory(p);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }


            } catch (Exception var20) {

            }

        }
    }

