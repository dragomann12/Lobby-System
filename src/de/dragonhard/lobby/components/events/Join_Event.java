package de.dragonhard.lobby.components.events;

import de.dragonhard.lobby.components.ConsoleWriter;
import de.dragonhard.lobby.components.PermissionList;
import de.dragonhard.lobby.manager.*;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.SQLException;

public class Join_Event extends Managers implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) throws SQLException {
        Player p = e.getPlayer();
        e.setJoinMessage("");

        this.getPlayerManager().checkPlayer(p);
        this.getPlayerManager().setFile(p,"config");
        this.getConnectionManager().createRow(p);

        String title = "";
        title = this.getConfigManager().getAccessLevelTag(Integer.parseInt(this.getConnectionManager().callRowLevel(p)));
        ConsoleWriter.writeWithTag("Player " + p.getName() + " with the UUID: " + p.getUniqueId() + " joined as " + title + "!");

        if(this.getConfigManager().showWelcome()){

            String msgColor = "§" + this.getConfigManager().getMessageColor();
            String nameColor = "§" + this.getConfigManager().getNameColor();
            String startColor = "§" + this.getConfigManager().getStartColor();
            p.sendMessage(  "§l" + startColor + this.getConfigManager().getMessageStart() + " " + nameColor + p.getName()+ " " + msgColor  + this.getConfigManager().getWelcomeMessage());
        }

        if(this.getPlayerManager().hasAutoWarp(p)){     //check for the first spawn location

            this.getSpawnManager().teleportPlayerToSpawn(p);
            this.getWarpManager().teleportPlayer(p, this.getPlayerManager().getAutoWarpLocation(p));
        }else {

            this.getSpawnManager().teleportPlayerToSpawn(p);
        }

        this.getInventoryManager().clearInv(p);     //clear the Inventory
        this.getDateManager().checkPlayerActivity(p); //checks last join

        if(this.getPlayerManager().isBuildModeEnabled(p)){ //build-mode check

            ConsoleWriter.writeWithTag("[ALERT] Player " + p.getName() + " uuid: "+ p.getUniqueId() + " joined with build-mode enabled");
            p.sendMessage("§l---------------------------------------------");
            p.sendMessage("§e*§4Achtung§e* §4" + p.getName());
            p.sendMessage("§l---------------------------------------------");
            p.sendMessage("§4du bist noch im Bau-Modus!");
            p.sendMessage("§l---------------------------------------------");
            return;}

            //all lines under this: creating of the Menu

        this.getInventoryManager().createItem(p,Material.getMaterial(this.getConfigManager().getHotbarMaterial("Lobby")),this.getConfigManager().getHotbarTitleColor("Lobby") + this.getConfigManager().getHotbarTitle("Lobby"),0);
        this.getInventoryManager().createItem(p, Material.getMaterial(this.getConfigManager().getHotbarMaterial("shop")),this.getConfigManager().getHotbarTitleColor("shop") + this.getConfigManager().getHotbarTitle("shop"),1);
        this.getInventoryManager().createItem(p, Material.STAINED_GLASS_PANE,"§4 ",2);

            if(this.getPluginWhitelistManager().isAdmin(p) || this.getPluginWhitelistManager().isOwner(p) || this.getPluginWhitelistManager().isPluginDeveloper(p)){

                this.getInventoryManager().createItem(p, Material.getMaterial(this.getConfigManager().getHotbarMaterial("Admin")),this.getConfigManager().getHotbarTitleColor("Admin") + this.getConfigManager().getHotbarTitle("Admin"),3);
            }else{

                this.getInventoryManager().createItem(p, Material.STAINED_GLASS_PANE,"§3 ",3);
            }


        this.getInventoryManager().createItem(p, Material.getMaterial(this.getConfigManager().getHotbarMaterial("Game")),this.getConfigManager().getHotbarTitleColor("Game") + this.getConfigManager().getHotbarTitle("Game"),4);

            if (this.getPluginWhitelistManager().isOwner(p) || this.getPluginWhitelistManager().isPluginDeveloper(p)) {

                this.getInventoryManager().createItem(p, Material.getMaterial(this.getConfigManager().getHotbarMaterial("Settings")), this.getConfigManager().getHotbarTitleColor("Settings") + this.getConfigManager().getHotbarTitle("Settings"), 5);
            } else {

                this.getInventoryManager().createItem(p, Material.STAINED_GLASS_PANE, "§2 ", 5);
            }

        this.getInventoryManager().createItem(p, Material.STAINED_GLASS_PANE,"§1 ",6);
        this.getInventoryManager().createItem(p, this.getPlayerManager().getHideStatusMaterial(p),"§5Spieler verstecken",7);
        this.getInventoryManager().addSkull(p,"§5" + this.getConfigManager().getHotbarTitle("Player"),this.getConfigManager().getHotbarTitleColor("Player"),8);

    }

}
