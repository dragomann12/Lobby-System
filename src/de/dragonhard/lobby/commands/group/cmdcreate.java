package de.dragonhard.lobby.commands.group;

import de.dragonhard.lobby.manager.GroupManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmdcreate extends GroupManager implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
  /*
                      (String)  args[2] = name
                      (String)  args[3] = Group Color
                         (int)  args[4] = maximale Spieler
                      (String)  args[5] = password
                                 */

        Player p = (Player) sender;

        if(sender != null){

            switch(args[0]){

                case "group":
                    switch(args[1]){
/*
                        case "public":

                            if(args[2].length() != 0 && args[3].length() != 0 && args[4].length() != 0 && args[5].length() == 0){
                                gm.createGroup(p, args[2], args[3], Integer.parseInt(args[4]));
                            }else{
                                getCmdInfo(p);
                            } break;
 */
                        case "private":

                            if(args[2].length() != 0 && args[3].length() != 0 && args[4].length() != 0 && args[5].length() != 0){
                                this.createGroup(p, args[2], args[3], Integer.parseInt(args[4]), args[5]);
                            }else{
                                getCmdInfo(p);
                            } break;

                        default:getCmdInfo(p);

                    } break;

                default: getCmdInfo(p);

            }

        }

        return false;

    }

    private void getCmdInfo(Player p){
        p.sendMessage("§4Fehler >> bitte so nutzen: /cr group <Gruppentyp> <name> <maxSpieler>");
    }

}
