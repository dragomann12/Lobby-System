package de.dragonhard.lobby.commands;

import de.dragonhard.lobby.manager.Managers;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmdPlayerInfo extends Managers implements CommandExecutor {
    final String item_color = "§6";
    String item_value_color = "§e";

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender != null){
         Player p = (Player) sender;

                 if(this.getPluginWhitelistManager().hasInfoAccess(p)){
                     if(args.length >= 2){p.sendMessage("§bInformation§f: §bEs wird nur ein Name als argument benötigt!");}
                     if(args.length == 0){
                         p.sendMessage("§4Fehler§e: §4du musst einen Namen angeben!");
                     }else{
                         try{
                            this.getCommandActionManager().Action_PlayerInformation(p, Bukkit.getPlayer(args[0]));
                         }catch (NullPointerException e){
                         p.sendMessage("§4Fehler§e: §4Der Spieler §e" + args[0] + " §4ist offline oder existiert nicht!");
                         }
                     }

                 }else{
                     this.getCommandActionManager().Action_noPermission(p);
                 }

        }

        return false;
    }
}
