package de.dragonhard.lobby.components.events;

import de.dragonhard.lobby.components.PermissionList;
import de.dragonhard.lobby.components.menu.*;
import de.dragonhard.lobby.components.menu.admin.Admin_Menu;
import de.dragonhard.lobby.components.menu.friend.Friend_Menu;
import de.dragonhard.lobby.components.menu.shop.Shop_Menu;
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

            menu.createItem(p, Material.NETHER_STAR, "§5Lobby", 0);

            menu.createItem(p, Material.DIAMOND, "§5Shop", 1);

            menu.createItem(p, Material.STAINED_GLASS_PANE, "§4 ", 2);

            if (p.hasPermission(PermissionList.getPermission("Menu", 0))) {
                menu.createItem(p, Material.BEACON, "§5Admin - Menu ", 3);
            } else {
                menu.createItem(p, Material.STAINED_GLASS_PANE, "§3 ", 3);
            }

            menu.createItem(p, Material.REDSTONE, "§5Minispiele", 4);

            if (p.hasPermission(PermissionList.getPermission("Menu", 0))) {
                menu.createItem(p, Material.BOOK, "§5Einstellungen ", 5);
            } else {
                menu.createItem(p, Material.STAINED_GLASS_PANE, "§2 ", 5);
            }

            menu.createItem(p, Material.STAINED_GLASS_PANE, "§1 ", 6);

            menu.createItem(p, pm.getHideStatusMaterial(p), "§5Spieler verstecken", 7);

            menu.createItem(p, Material.BANNER, "§5Freunde", 8);


            if (p.hasPermission(PermissionList.getPermission("external", 0)) && pwm.isOwner(p)) {
                menu.createInventoryItem(p, Material.COMMAND, "Magic item", 22);
            } else {
                menu.createInventoryItem(p, Material.BARRIER, "§4Admin-Item", 22);
            }


            try {
                if (e.getItem().getType().equals(Material.COMPASS)) {
                    p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);


                } else if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                    if (e.getItem().getType().equals(pm.getHideStatusMaterial(p))) {
                        p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);

                            p.getInventory().remove(pm.getHideStatusMaterial(p));

                        pm.toggleHideStatus(p);
                        menu.createItem(p, pm.getHideStatusMaterial(p), "§5Spieler verstecken", 7);


                    } else if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                        if (e.getItem().getType().equals(Material.BEACON)) {
                            p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);                  // Admin
                            Admin_Menu am = new Admin_Menu();
                            am.openInventory(p);

                        } else if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                            if (e.getItem().getType().equals(Material.BOOK)) {
                                p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);              //Settings
                                Settings_Menu stm = new Settings_Menu();
                                stm.openInventory(p);

                            } else if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                                if (e.getItem().getType().equals(Material.GOLDEN_APPLE)) {
                                    p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);

                                        } else if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                                            if (e.getItem().getType().equals(Material.REDSTONE)) {
                                                p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);      //Gamemodi
                                                Game_Menu gm = new Game_Menu();
                                                gm.openInventory(p);

                                            } else if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                                                if (e.getItem().getType().equals(Material.NETHER_STAR)) {
                                                    p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);  //Lobby
                                                    Lobby_Menu lm = new Lobby_Menu();
                                                    lm.openInventory(p);

                                                } else if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                                                    if (e.getItem().getType().equals(Material.BANNER)) {
                                                        p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);  //friend
                                                        Friend_Menu fm = new Friend_Menu();
                                                        fm.openInventory(p);

                                                    } else if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                                                        if (e.getItem().getType().equals(Material.DIAMOND)) {
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
                }


            } catch (Exception var20) {

            }

        }
    }

