package de.dragonhard.lobby.components.events;

import de.dragonhard.lobby.components.ConsoleWriter;
import de.dragonhard.lobby.components.util.InventorySetter;
import de.dragonhard.lobby.manager.*;
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
        this.getSpawnManager().teleportPlayerToSpawn(p);

        this.getConnectionManager().createRow(p);

        this.getPlayerManager().checkPlayer(p);
        this.getPlayerManager().setFile(p,"config");

        String title = "";
        title = this.getConfigManager().getAccessLevelTag(Integer.parseInt(this.getConnectionManager().callRowLevel(p)));
        ConsoleWriter.writeWithTag("Player " + p.getName() + " with the UUID: " + p.getUniqueId() + " joined as " + title + "!");

        if(this.getConfigManager().showWelcome()){

            String msgColor = "§" + this.getConfigManager().getMessageColor();
            String nameColor = "§" + this.getConfigManager().getNameColor();
            String startColor = "§" + this.getConfigManager().getStartColor();
            p.sendMessage("§l" + startColor + this.getConfigManager().getMessageStart() + " " + nameColor + p.getName()+ " " + msgColor  + this.getConfigManager().getWelcomeMessage()); }

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

        //hotbar menu
        InventorySetter is = new InventorySetter();
        is.getHotbarItems(p);
    }

}
