package de.dragonhard.lobby.commands.group;

import de.dragonhard.lobby.manager.other.GroupManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmdJoin extends GroupManager implements CommandExecutor {

    //@TODO remove later?
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player p = (Player) sender;

        if(sender != null){

            switch(args[0]){
                case "group":

                if(args[1].length() != 0 && args[2].length() != 0){
                  this.groupJoin(p, args[1], args[2]);
                }

                default:getCmdInfo(p);
            }

        }

        return false;

    }
    private void getCmdInfo(Player p){
        p.sendMessage("§4Fehler >> bitte so nutzen: /join group <name> <password>");
    }
}
