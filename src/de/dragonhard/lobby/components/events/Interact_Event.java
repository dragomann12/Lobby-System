package de.dragonhard.lobby.components.events;

import de.dragonhard.lobby.components.ConsoleWriter;
import de.dragonhard.lobby.components.menu.Menu;
import de.dragonhard.lobby.manager.Managers;
import de.dragonhard.lobby.manager.other.MenuManager;
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


            try {
                if (e.getItem().getType().equals(Material.COMPASS)) { // this Item is not in use!
                    ConsoleWriter.writeWithTag("Compass are disabled for the Lobby-System"); return;
                } else if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                    if (e.getItem().getType().equals(Material.POTION)) {
                        //                                                  Lobby Menu
                        p.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK, 1.0F, 1.0F);
                        MenuManager.getMenu("lobby");

                    } else if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                        if (e.getItem().getType().equals(Material.POTION)) {
                            //Admin Item
                            p.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK, 1.0F, 1.0F);


                        } else if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                            if (e.getItem().getType().equals(Material.POTION)) {
                                //Settings Item
                                p.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK, 1.0F, 1.0F);

                            } else if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                                if (e.getItem().getType().equals(Material.POTION)) {
                                    //Game Item
                                    p.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK, 1.0F, 1.0F);

                                } else if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                                    if (e.getItem().getType().equals(Material.POTION)) {
                                        //Lobby Item
                                        p.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK, 1.0F, 1.0F);

                                    } else if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                                        if (e.getItem().getType().equals(Material.POTION)) {
                                            //Player Item
                                            p.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK, 1.0F, 1.0F);

                                        } else if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                                            if (e.getItem().getType().equals(Material.POTION)) {
                                                //Shop Item
                                                p.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK, 1.0F, 1.0F);

                                            }
                                        } else if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                                            if (e.getItem().getType().equals(Material.BARRIER)) {
                                            //blocked Item
                                                p.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK, 1.0F, 1.0F);
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

