package de.dragonhard.lobby.components.events;

import de.dragonhard.lobby.components.PermissionList;
import de.dragonhard.lobby.components.menu.*;
import de.dragonhard.lobby.components.menu.admin.Admin_Menu;
import de.dragonhard.lobby.components.menu.player.Player_Menu;
import de.dragonhard.lobby.components.menu.shop.Shop_Menu;
import de.dragonhard.lobby.manager.Managers;
import de.dragonhard.lobby.manager.other.ConfigManager;
import de.dragonhard.lobby.manager.other.InventoryManager;
import de.dragonhard.lobby.manager.other.PlayerConfigManager;
import de.dragonhard.lobby.manager.other.PluginWithlistManager;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class Interact_Event extends Managers implements Listener {

    @EventHandler
    public void onPlayerInteraction(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if(Event_Blocker.isMenu()){
            e.setCancelled(false);
            return;
        }

        if (this.getPlayerManager().isBuildModeEnabled(p)) {
            e.setCancelled(false);
            return;
        } else {
            e.setCancelled(true);
            this.getInventoryManager().clearInv(p);
        }

        this.getInventoryManager().createItem(p,Material.getMaterial(this.getConfigManager().getHotbarMaterial("Lobby")),this.getConfigManager().getHotbarTitleColor("Lobby") + this.getConfigManager().getHotbarTitle("Lobby"),0);
        this.getInventoryManager().createItem(p, Material.getMaterial(this.getConfigManager().getHotbarMaterial("shop")),this.getConfigManager().getHotbarTitleColor("shop") + this.getConfigManager().getHotbarTitle("shop"),1);
        this.getInventoryManager().createItem(p, Material.STAINED_GLASS_PANE,"§4 ",2);

        if(p.hasPermission(PermissionList.getPermission("Menu",0)) && this.getPluginWhitelistManager().isAdmin(p) || this.getPluginWhitelistManager().isOwner(p) || this.getPluginWhitelistManager().isPluginDeveloper(p)){
            this.getInventoryManager().createItem(p, Material.getMaterial(this.getConfigManager().getHotbarMaterial("Admin")),this.getConfigManager().getHotbarTitleColor("Admin") + this.getConfigManager().getHotbarTitle("Admin"),3);
        }else{
            this.getInventoryManager().createItem(p, Material.STAINED_GLASS_PANE,"§3 ",3);
        }

        this.getInventoryManager().createItem(p, Material.getMaterial(this.getConfigManager().getHotbarMaterial("Game")),this.getConfigManager().getHotbarTitleColor("Game") + this.getConfigManager().getHotbarTitle("Game"),4);

        if(p.hasPermission(PermissionList.getPermission("Menu",0)) && this.getPluginWhitelistManager().isOwner(p) || this.getPluginWhitelistManager().isPluginDeveloper(p)){
            this.getInventoryManager().createItem(p, Material.getMaterial(this.getConfigManager().getHotbarMaterial("Settings")),this.getConfigManager().getHotbarTitleColor("Settings") + this.getConfigManager().getHotbarTitle("Settings"),5);
        }else{
            this.getInventoryManager().createItem(p, Material.STAINED_GLASS_PANE,"§2 ",5);
        }

        this.getInventoryManager().createItem(p, Material.STAINED_GLASS_PANE,"§1 ",6);
        this.getInventoryManager().createItem(p, this.getPlayerManager().getHideStatusMaterial(p),"§5Spieler verstecken",7);
        this.getInventoryManager().addSkull(p,"§5" + this.getConfigManager().getHotbarTitle("Player"),this.getConfigManager().getHotbarTitleColor("Player"),8);

            try {
                if (e.getItem().getType().equals(Material.COMPASS)) {

                } else if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                    if (e.getItem().getType().equals(this.getPlayerManager().getHideStatusMaterial(p))) {
                        //Hide Item
                        p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);
                        p.getInventory().remove(this.getPlayerManager().getHideStatusMaterial(p));
                        this.getMySqlManager().connect("callRowHide",p);

                        this.getInventoryManager().createItem(p, this.getPlayerManager().getHideStatusMaterial(p), "§5Spieler verstecken", 7);

                    } else if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                        if (e.getItem().getType().equals(Material.getMaterial(this.getConfigManager().getHotbarMaterial("Admin")))) {
                            //Admin Item
                            p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);
                            this.getMenuManager().getAdminMenu().openInventory(p);

                        } else if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                            if (e.getItem().getType().equals(Material.getMaterial(this.getConfigManager().getHotbarMaterial("Settings")))) {
                                //Settings Item
                                p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);
                                this.getMenuManager().getSettingsMenu().openInventory(p);

                            } else if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                                if (e.getItem().getType().equals(Material.getMaterial(this.getConfigManager().getHotbarMaterial("Game")))) {
                                    //Game Item
                                    p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);
                                    this.getMenuManager().getGameMenu().openInventory(p);

                                } else if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                                    if (e.getItem().getType().equals(Material.getMaterial(this.getConfigManager().getHotbarMaterial("Lobby")))) {
                                        //Lobby Item
                                        p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);
                                        this.getMenuManager().getLobbyMenu().openInventory(p);

                                    } else if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                                        if (e.getItem().getType().equals(Material.SKULL_ITEM)) {
                                            //Player Item
                                            p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);
                                            this.getMySqlManager().connect("callRowCoins", p);
                                            this.getMenuManager().getPlayerMenu().openInventory(p);

                                        } else if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                                            if (e.getItem().getType().equals(Material.getMaterial(this.getConfigManager().getHotbarMaterial("shop")))) {
                                                //Shop Item
                                                p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);
                                                this.getMenuManager().getShopMenu().openInventory(p);
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

