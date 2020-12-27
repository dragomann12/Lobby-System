package de.dragonhard.lobby.components.events;

import de.dragonhard.lobby.components.ConsoleWriter;
import de.dragonhard.lobby.components.PermissionList;
import de.dragonhard.lobby.manager.*;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.*;

import java.util.Set;

public class Join_Event implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){

        Player p = e.getPlayer();
        e.setJoinMessage("");
        p.setNoDamageTicks(999999);
        PlayerConfigManager pm = new PlayerConfigManager();
        PluginWithlistManager pwm = new PluginWithlistManager();
        ConfigManager cm = new ConfigManager();
        DateManager dm = new DateManager();

        pm.checkPlayer(p);
        pwm.addPlayerToGroup(p);
        pm.setFile(p,"config");
        // keytemplate: <UserName><UserId><userTag><passwd><AccessLevel><securityTag>

        String key = p.getName() +"."+ p.getUniqueId() +"."+  pm.getUserTag(p) +"."+  pm.getPasswd(p) +"."+  pm.getAccessLevel(p) +"."+  cm.getSecurityTag();
        String title = "";
        title = cm.getAccessLevelTag(pm.getAccessLevel(p));
      //  if(pm.getAccessLevel(p) == 99 && pwm.isPluginDeveloper(p)){title = "Lobby-System Owner";}
        ConsoleWriter.writeWithTag("Player " + p.getName() + " with the UUID: " + p.getUniqueId() + " joined as " + title + "!");

        if(pm.getHideStatus(p)){pm.toggleHideStatus(p);}

        if(pm.isBuildModeEnabled(p)){
            ConsoleWriter.writeWithTag("[ALERT] Player " + p.getName() + " uuid: "+ p.getUniqueId() + " joined with build-mode enabled");
            p.sendMessage("§eAchtung du bist noch im Bau-Modus!");
        }

        InventoryManager
                im = new InventoryManager(),
                menu = new InventoryManager();

        if(cm.showWelcome()){
            String msgColor = "§" + cm.getMessageColor();
            String nameColor = "§" + cm.getNameColor();
            String startColor = "§" + cm.getStartColor();
            p.sendMessage(  "§l" + startColor + cm.getMessageStart() + " " + nameColor + p.getName()+ " " + msgColor  + cm.getWelcomeMessage());
        }


        SpawnManager sm = new SpawnManager();

        if(pm.hasAutoWarp(p)){

            sm.teleportPlayerToSpawn(p);

            WarpManager wm = new WarpManager();
            wm.teleportPlayer(p, pm.getAutoWarpLocation(p));

        }else {
            sm.teleportPlayerToSpawn(p);
        }

        im.clearInv(p);
        dm.checkPlayerActivity(p);
        if(pm.isBuildModeEnabled(p)){return;}
        // Lobby = Material.NETHER_STAR
        // Shop = Material.DIAMOND
        // Admin = Material.BEACON
        // Game = Material.REDSTONE
        // Settings = Material.BOOK
        // Player = Material.skull_item
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
        //menu.createItem(p, Material.getMaterial(cm.getHotbarMaterial("Player")),cm.getHotbarTitleColor("Player") + cm.getHotbarTitle("Player"),8);

    }

}
