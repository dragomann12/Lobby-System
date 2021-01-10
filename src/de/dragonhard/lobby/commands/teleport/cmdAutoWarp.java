package de.dragonhard.lobby.commands.teleport;

import de.dragonhard.lobby.components.PermissionList;
import de.dragonhard.lobby.manager.other.PlayerConfigManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmdAutoWarp extends PlayerConfigManager implements CommandExecutor {

//@TODO remove later?

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;

            if(sender != null){

                if(p.hasPermission(PermissionList.getPermission("Use",2))){

                    if(args[0].length() != 0){

                        switch(args[0]){

                            case "set":
                                if(args[1].length() != 0){
                                    this.setAutoWarpLocation(p,args[1]);
                                }else{
                                    p.sendMessage("§4Es wurde kein Warp angegeben!");
                                } break;

                            case "toggle":
                                this.toggleAutoWarp(p);

                                if(this.getAutoWarpStatus(p)){
                                    p.sendMessage("§aAutoWarp ist jetzt atkiv");
                                }else{
                                    p.sendMessage("§4AutoWarp ist nicht mehr atkiv");
                                } break;

                            default: p.sendMessage("§4Fehler benutze den Befehl so: ");
                                     p.sendMessage("                                 §4Hilfe >> §e/autowarp §4set");
                                     p.sendMessage("                                 §4Hilfe >> §e/autowarp §4toggle");

                        }

                    }else{
                        p.sendMessage("§4Fehler benutze den Befehl so: ");
                        p.sendMessage("                                 §4Hilfe >> §e/autowarp §4set");
                        p.sendMessage("                                 §4Hilfe >> §e/autowarp §4toggle");

                    }

                }else{
                    p.sendMessage("§4Du hast nicht die erforderlichen Rechte!");
                }

            }

        return false;
    }
}
