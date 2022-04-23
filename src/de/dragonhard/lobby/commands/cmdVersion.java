package de.dragonhard.lobby.commands;

import de.dragonhard.lobby.manager.Managers;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmdVersion extends Managers implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender != null){
            Player p = (Player) sender;

            if(this.getPluginWhitelistManager().isTeam(p)){
                this.getCommandActionManager().Action_PluginVersion(p);
            }else{
                this.getCommandActionManager().Action_noPermission(p);
            }

        }

        return false;
    }
}
