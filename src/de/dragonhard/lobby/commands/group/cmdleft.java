package de.dragonhard.lobby.commands.group;

import de.dragonhard.lobby.manager.other.GroupManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmdleft extends GroupManager implements CommandExecutor {


//@TODO remove later?

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player p = (Player) sender;

        if(sender != null){
            if(args[0].length() != 0){

                switch(args[0]){

                    case "group":
                        if(args[1].length() != 0){
                            this.setFile(args[1]);
                            if(this.groupHasPlayer(p.getName(), args[1])){
                                this.groupLeft(p, args[1]);
                            }else{
                                p.sendMessage("§4Du bist kein teil dieser Gruppe!");
                            }
                        } else{
                            getCmdInfo(p);
                        }

                    default: getCmdInfo(p);
                }

            }else{
                getCmdInfo(p);
            }
        }

        return false;

    }

    private void getCmdInfo(Player p){
        p.sendMessage("§4Fehler >> bitte so nutzen: /left group <name>");
    }

}
