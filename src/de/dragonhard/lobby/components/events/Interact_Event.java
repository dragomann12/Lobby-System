package de.dragonhard.lobby.components.events;

import de.dragonhard.lobby.components.ConsoleWriter;
import de.dragonhard.lobby.components.util.InventorySetter;
import de.dragonhard.lobby.manager.Managers;
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

        InventorySetter is = new InventorySetter();
        is.getHotbarItems(p);

            try {
                if (e.getItem().getType().equals(Material.COMPASS)) { // this Item is not in use!
                    ConsoleWriter.writeWithTag("Compass are disabled for the Lobby-System"); return;
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
                                        } else if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                                            if (e.getItem().getType().equals(Material.BARRIER)) {
                                            //blocked Item
                                                p.sendMessage("§4Das Item ist gesperrt!");
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }


            } catch (Exception var20) {
                ConsoleWriter.writeErrorWithTag("" + var20.getMessage());
            }

        }
    }

