package de.dragonhard.lobby.components.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class CommandListener implements Listener {

    @EventHandler (priority = EventPriority.HIGH)
    public void onCommandPreprocessEvent(PlayerCommandPreprocessEvent event) {
        List<String> commands = new ArrayList<String>();
        for (Plugin plugin : Bukkit.getServer().getPluginManager().getPlugins())
            for (String command : plugin.getDescription().getCommands().keySet())
                commands.add(command);

        if (!commands.contains(event.getMessage().replaceAll("/", ""))) {
            event.getPlayer().sendMessage("§e[§4Fehler§e] §4Dieser Befehl ist nicht verfügbar oder wurde falsch geschrieben!");
            event.setCancelled(true);
        }
    }

}
