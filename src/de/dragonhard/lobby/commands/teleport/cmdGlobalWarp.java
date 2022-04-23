package de.dragonhard.lobby.commands.teleport;

import de.dragonhard.lobby.components.PermissionList;
import de.dragonhard.lobby.manager.Managers;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmdGlobalWarp extends Managers implements CommandExecutor {

//@TODO remove later?

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender != null){

            Player p = (Player)sender;

            if(p.hasPermission(PermissionList.getPermission("Use",3))){

                if(args.length != 0){

                    switch(args[0]){

                        case "set": this.getCommandActionManager().Action_setGlobalWarp(p,args[1]); break;
                        case "remove": this.getCommandActionManager().Action_delGlobalWarp(p,args[1]); break;
                        default: this.getCommandActionManager().Action_helpGlobalWarp(p);
                    }

                }else{this.getCommandActionManager().Action_helpGlobalWarp(p);}

            }else{
                this.getCommandActionManager().Action_noPermission(p);
            }

        }

        return false;
    }

}
