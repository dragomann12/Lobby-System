package de.dragonhard.lobby.commands.teleport;

import de.dragonhard.lobby.components.PermissionList;
import de.dragonhard.lobby.manager.Managers;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmdWarp extends Managers implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender != null){
            Player p = (Player) sender;
            if(p.hasPermission(PermissionList.getPermission("Use",0))){

                if(args.length != 0){

                        switch(args[0]){

                            case "set": this.getCommandActionManager().Action_setWarp(p,args[1]); break;

                            case "del": this.getCommandActionManager().Action_delWarp(p,args[1]); break;

                            case "count": this.getCommandActionManager().Action_WarpCount(p); break;

                            case "ls": this.getWarpManager().getWarpList(p); break;

                                default:
                                    if(this.getPlayerManager().isWarpEnabled(p)){
                                        this.getCommandActionManager().Action_WarpTeleport(p, args[0]);
                                    }else{
                                        this.getCommandActionManager().Action_notActive(p);
                                    }

                        }

                }else{
                 this.getCommandActionManager().Action_helpWarp(p);
                }

            }else{
                this.getCommandActionManager().Action_noPermission(p);
            }

        }

        return false;
    }


}
