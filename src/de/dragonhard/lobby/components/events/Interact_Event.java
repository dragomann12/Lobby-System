package de.dragonhard.lobby.components.events;

import de.dragonhard.lobby.components.ConsoleWriter;
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


            try {
                if (e.getItem().getType().equals(Material.COMPASS)) { // this Item is not in use!
                    ConsoleWriter.writeWithTag("Compass are disabled for the Lobby-System"); return;
                } else if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                    if (e.getItem().getType().equals(null)) {
                        //Hide Item
                        p.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK, 1.0F, 1.0F);

                    } else if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                        if (e.getItem().getType().equals(Material.getMaterial(null))) {
                            //Admin Item
                            p.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK, 1.0F, 1.0F);


                        } else if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                            if (e.getItem().getType().equals(Material.getMaterial(null))) {
                                //Settings Item
                                p.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK, 1.0F, 1.0F);

                            } else if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                                if (e.getItem().getType().equals(Material.getMaterial(null))) {
                                    //Game Item
                                    p.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK, 1.0F, 1.0F);

                                } else if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                                    if (e.getItem().getType().equals(Material.getMaterial(null))) {
                                        //Lobby Item
                                        p.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK, 1.0F, 1.0F);

                                    } else if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                                        if (e.getItem().getType().equals(null)) {
                                            //Player Item
                                            p.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK, 1.0F, 1.0F);

                                        } else if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                                            if (e.getItem().getType().equals(Material.getMaterial(null))) {
                                                //Shop Item
                                                p.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK, 1.0F, 1.0F);

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

