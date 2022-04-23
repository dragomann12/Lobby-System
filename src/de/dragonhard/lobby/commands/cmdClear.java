package de.dragonhard.lobby.commands;

import de.dragonhard.lobby.manager.Managers;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmdClear extends Managers implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender != null){
            if(!(sender instanceof Player)){ return false;}
            Player p = (Player) sender;

            if(this.getPluginWhitelistManager().isTeam(p)){
                this.getCommandActionManager().Action_clearChat(p);
            }else{
                this.getCommandActionManager().Action_noPermission(p);
            }

        }

        return false;
    }

}
