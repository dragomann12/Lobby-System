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
                                        this.getCommandActionManager().Action_createSpawn(p);
                                   break;
                            case "del":
                                        this.getCommandActionManager().Action_delSpawn(p);
                                 break;
                            default:
                              help(p);
                        }

                }else{
                    this.getCommandActionManager().Action_teleportToSpawn(p);
                }

            }else{
                this.getCommandActionManager().Action_noPermission(p);
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

}
