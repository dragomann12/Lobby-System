package de.dragonhard.lobby.commands.coins;

import de.dragonhard.lobby.manager.Managers;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmdCoins extends Managers implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender != null){

            Player p = (Player)sender;

            if(args[0] == null){
                this.getCommandActionManager().Action_showPlayerCoins(p);
            }else if(!args[1].isEmpty()){
                try{
                    Player target = Bukkit.getPlayer(args[1]);
                    switch (args[0]){
                        case "see": this.getCommandActionManager().Action_showCoins(target); break;
                        case "set": this.getCommandActionManager().Action_setCoins(p, target, Integer.parseInt(args[2])); break;
                        case "add": this.getCommandActionManager().Action_addCoins(p,target, Integer.parseInt(args[2])); break;
                    }
                }catch(NullPointerException e){
                    this.getCommandActionManager().Action_errorMessage(p,"Coins",e);
                }

            }else{
                this.getCommandActionManager().Action_helpCoins(p);
            }

        }

        return false;
    }

}
