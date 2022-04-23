package de.dragonhard.lobby.commands.network;

import de.dragonhard.lobby.components.PermissionList;
import de.dragonhard.lobby.manager.Managers;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmdCreateServer extends Managers implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender != null){

            Player p = (Player) sender;
            if(p.hasPermission(PermissionList.getPermission("Menu_Item",0)) || this.getPluginWhitelistManager().isPluginDeveloper(p)){

                if(args[0].length() != 0 && args[1].length() != 0 && args[2].length() != 0 && args[3].length() != 0 && args[4].length() != 0){
                    p.sendMessage("§eerstelle item");
                    //                                    Status name material server slot
                    this.getMySqlManager().connect("setRowServer",args[1],args[3],args[2], Integer.parseInt(args[4]),"edit",p);
                    p.sendMessage("§aItem erstellt");
                }else{

                    p.sendMessage("§4Es werden noch Argumente benötigt!");

                }

            }else{
                p.sendMessage("§4keine Berechtigung für diesen Befehl!");
            }

        }

        return false;
    }
}
