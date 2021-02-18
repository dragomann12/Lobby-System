package de.dragonhard.lobby.commands.teleport;

import de.dragonhard.lobby.components.PermissionList;
import de.dragonhard.lobby.manager.Managers;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmdSpawn extends Managers implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender != null){
            Player p = (Player) sender;
            if(p.hasPermission(PermissionList.getPermission("Use",1))){

                if(args.length != 0){

                        switch (args[0]) {
                            case "set": this.getCommandActionManager().Action_createSpawn(p); break;
                            case "del": this.getCommandActionManager().Action_delSpawn(p); break;
                            default: this.getCommandActionManager().Action_helpSpawn(p);
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

}
