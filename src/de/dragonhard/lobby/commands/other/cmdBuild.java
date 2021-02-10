package de.dragonhard.lobby.commands.other;

import de.dragonhard.lobby.manager.Managers;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmdBuild extends Managers implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player){
            Player p = (Player) sender;
            try{
                if(args.length == 0) {p.sendMessage("§4Fehler§e: §e/§4bm §e<§4player§e>"); return false;}
                Player target = Bukkit.getPlayer(args[0]);

                if(this.getPluginWhitelistManager().hasBuildAccess(p)){
                    this.getCommandActionManager().Action_setMode(p,target);
                }else {
                    this.getCommandActionManager().Action_noPermission(p);
                }
            }catch (NullPointerException e){
                p.sendMessage("§4Fehler§e: §4Spieler wurde nicht gefunden!");
            }

        }

        return false;
    }

}
