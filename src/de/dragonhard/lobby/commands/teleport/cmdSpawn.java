package de.dragonhard.lobby.commands.teleport;

import de.dragonhard.lobby.components.Message;
import de.dragonhard.lobby.components.PermissionList;
import de.dragonhard.lobby.manager.Managers;
import de.dragonhard.lobby.manager.other.ConfigManager;
import de.dragonhard.lobby.manager.other.SpawnManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmdSpawn extends Managers implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;

        if(sender != null){

            if(p.hasPermission(PermissionList.getPermission("Use",1))){

                if(args.length != 0){

                        switch (args[0]) {
                            case "set":
                                    if(p.hasPermission(PermissionList.getPermission("Config",1))){
                                       this.getSpawnManager().createSpawn(p, p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), p.getLocation().getPitch(), p.getLocation().getYaw(), p.getWorld());
                                    }else{noPermission(p);} break;
                            case "del":
                                    if(p.hasPermission(PermissionList.getPermission("Config",2))){
                                        this.getSpawnManager().delSpawn(p);
                                    }else{noPermission(p);} break;
                            default:
                              help(p);
                        }

                }else{

                    if(this.getSpawnManager().exists(this.getSpawnManager().getFile("Spawn"))) {
                        this.getSpawnManager().teleportPlayerToSpawn(p);
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

    private void help(Player p){

        if(this.getConfigManager().tagUseEnabled()){
            p.sendMessage(this.getConfigManager().getTag() + "§4Fehler benutze den Befehl so: ");
        }else{
            p.sendMessage("§4Fehler benutze den Befehl so: ");
        }

        p.sendMessage("                                 §4Hilfe >> §e/spawn ");
        p.sendMessage("                                 §4Hilfe >> §e/spawn §4set");
        p.sendMessage("                                 §4Hilfe >> §e/spawn §4del");
    }

    private void noPermission(Player p){

        if (this.getConfigManager().tagUseEnabled()) {
            p.sendMessage(this.getConfigManager().getTag() + "§4Du hast nicht die Berechtigung für diesen Befehl!");
        }else{
            p.sendMessage("§4Du hast nicht die Berechtigung für diesen Befehl!");
        }

    }

}
