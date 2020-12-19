package de.dragonhard.lobby.components.events;

import de.dragonhard.lobby.components.ConsoleWriter;
import de.dragonhard.lobby.components.PermissionList;
import de.dragonhard.lobby.manager.*;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Join_Event implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){

        Player p = e.getPlayer();
        e.setJoinMessage("");
        p.setNoDamageTicks(999999);
        PlayerConfigManager pm = new PlayerConfigManager();
        PluginWithlistManager pwm = new PluginWithlistManager();
        ConfigManager cm = new ConfigManager();

        pm.checkPlayer(p);
        pwm.addPlayerToGroup(p);
        pm.setFile(p,"config");
        // keytemplate: <UserName><UserId><userTag><passwd><AccessLevel><securityTag>

        String key = p.getName() +"."+ p.getUniqueId() +"."+  pm.getUserTag(p) +"."+  pm.getPasswd(p) +"."+  pm.getAccessLevel(p) +"."+  cm.getSecurityTag();
        String title = "";
        title = cm.getAccessLevelTag(pm.getAccessLevel(p));
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

        if(pm.isBuildModeEnabled(p)){return;}

        menu.createItem(p, Material.NETHER_STAR,"§5Lobby",0);
        menu.createItem(p, Material.DIAMOND,"§5Shop",1);
        menu.createItem(p, Material.STAINED_GLASS_PANE,"§4 ",2);

        if(p.hasPermission(PermissionList.getPermission("Menu",0)) && pwm.isOwner(p)){
            menu.createItem(p, Material.BEACON,"§5Admin - Menu ",3);
        }else{
            menu.createItem(p, Material.STAINED_GLASS_PANE,"§3 ",3);
        }

        menu.createItem(p, Material.REDSTONE,"§5Minispiele",4);

        if(p.hasPermission(PermissionList.getPermission("Menu",0))){
            menu.createItem(p, Material.BOOK,"§5Einstellungen ",5);
        }else{
            menu.createItem(p, Material.STAINED_GLASS_PANE,"§2 ",5);
        }

        menu.createItem(p, Material.STAINED_GLASS_PANE,"§1 ",6);
        menu.createItem(p, pm.getHideStatusMaterial(p),"§5Spieler verstecken",7);
        menu.createItem(p, Material.BANNER,"§5Freunde",8);

        if(p.hasPermission(PermissionList.getPermission("external",0)) && pwm.isOwner(p)){menu.createInventoryItem(p, Material.COMMAND,"Magic-Item",22);}else{menu.createInventoryItem(p, Material.WEB,"§4Admin-item",22);} // Item 22


    }

}
