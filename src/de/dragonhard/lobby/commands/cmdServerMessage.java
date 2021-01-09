package de.dragonhard.lobby.commands;

import de.dragonhard.lobby.components.ConsoleWriter;
import de.dragonhard.lobby.manager.other.ServerMessageManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmdServerMessage extends ServerMessageManager implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender != null){
            if(!(sender instanceof Player)){ return false;}
            Player p = (Player) sender;

            switch (args[0]){
                case "del": delMessage(p, args[1]); break;
                case "set": setMessage(p, args[1]); break;
                default: ConsoleWriter.writeErrorWithTag("input not known"); break;

            }


        }

        return false;
    }

    private void setMessage(Player p, String message){
        String[] msg_args = message.split("/");
        this.createMessage(p, msg_args[0], msg_args[1], msg_args[2], msg_args[3], msg_args[4], false,true);


    }

    private void delMessage(Player p, String message){



    }
}
