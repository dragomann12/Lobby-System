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

                        case "set":

                            if(args[1] != ""){
                                this.getGlobalWarpManager().createWarp(p, args[1], p.getWorld());
                            }else{help(p);}break;

                        case "remove":

                            if(args[1] != ""){
                                this.getGlobalWarpManager().delWarp(p, args[1]);
                            }else{help(p);}break;

                        default: help(p);
                    }

                }else{help(p);}

            }else{
                noPermission(p);
            }

        }

        return false;
    }

    private void noPermission(Player p){

        p.sendMessage("§4Du hast nicht die erforderlichen Rechte für diesen Befehl!");

    }

    private void help(Player p){

        if(this.getConfigManager().tagUseEnabled()){
            p.sendMessage(this.getConfigManager().getTag() + "§4Fehler benutze den Befehl so: ");
        }else{
            p.sendMessage("§4Fehler benutze den Befehl so: ");
        }

        p.sendMessage("                                 §4Hilfe >> §e/InvWarp §4set [§ename§4]");
        p.sendMessage("                                 §4Hilfe >> §e/InvWarp §4del [§ename§4]");

    }
}
