package de.dragonhard.lobby.commands.teleport;

import de.dragonhard.lobby.components.Message;
import de.dragonhard.lobby.components.PermissionList;
import de.dragonhard.lobby.manager.ConfigManager;
import de.dragonhard.lobby.manager.LanguageManager;
import de.dragonhard.lobby.manager.PlayerConfigManager;
import de.dragonhard.lobby.manager.SpawnManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmdSpawn extends SpawnManager implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        ConfigManager cm = new ConfigManager();

        Player p = (Player) sender;

        if(sender != null){

            if(p.hasPermission(PermissionList.getPermission("Use",1))){

                if(args.length != 0){

                        switch (args[0]) {
                            case "set":
                                    if(p.hasPermission(PermissionList.getPermission("Config",1))){
                                       this.createSpawn(p, p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), p.getLocation().getPitch(), p.getLocation().getYaw(), p.getWorld());
                                    }else{noPermission(p);} break;
                            case "del":
                                    if(p.hasPermission(PermissionList.getPermission("Config",2))){
                                        this.delSpawn(p);
                                    }else{noPermission(p);} break;
                            default:
                              help(p);
                        }

                }else{

                    if(this.exists(this.getFile("Spawn"))) {
                        this.teleportPlayerToSpawn(p);
                        Message.sendMessageByTag(Message.getMessageByTag("tpSpawn"),p);
                    }else{
                        p.sendMessage("§4Der Spawn wurde nicht gefunden!");

                    }
                }

            }else{
                noPermission(p);
            }

        }

        return false;
    }

    private static void help(Player p){
        ConfigManager cm = new ConfigManager();

        if(cm.tagUseEnabled()){
            p.sendMessage(cm.getTag() + "§4Fehler benutze den Befehl so: ");
        }else{
            p.sendMessage("§4Fehler benutze den Befehl so: ");
        }

        p.sendMessage("                                 §4Hilfe >> §e/spawn ");
        p.sendMessage("                                 §4Hilfe >> §e/spawn §4set");
        p.sendMessage("                                 §4Hilfe >> §e/spawn §4del");
    }

    private static void noPermission(Player p){
        ConfigManager cm = new ConfigManager();

        if (cm.tagUseEnabled()) {
            p.sendMessage(cm.getTag() + "§4Du hast nicht die Berechtigung für diesen Befehl!");
        }else{
            p.sendMessage("§4Du hast nicht die Berechtigung für diesen Befehl!");
        }

    }

}
