package de.dragonhard.lobby.commands.other;

import de.dragonhard.lobby.components.ConsoleWriter;
import de.dragonhard.lobby.manager.Managers;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class cmdBuild extends Managers implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player){
            Player p = (Player) sender;
            try{
                if(args.length == 0) {p.sendMessage("§4Fehler§e: §e/§4bm §e<§4player§e>"); return false;}
                Player target = Bukkit.getPlayer(args[0]);

                if(this.getPluginWhitelistManager().hasBuildAccess(p)){
                    setMode(p,target);
                }else {
                    this.getCommandActionManager().Action_noPermission(p);
                }
            }catch (NullPointerException e){
                p.sendMessage("§4Fehler§e: §4Spieler wurde nicht gefunden!");
            }

        }

        return false;
    }

    private void setMode(Player sender ,Player target){
        try{
            if(target == null){sender.sendMessage("§4Fehler§e: §4kein Spieler als Ziel angegeben!");return;}
            if(target.getName().equals(sender.getName())){this.getPlayerManager().toggleBuildMode(target); this.getInventoryManager().clearInv(target); this.getCommandActionManager().Action_reloadItems(target); return;}
            if(this.getPlayerManager().isBuildModeEnabled(target)){
                this.getPlayerManager().toggleBuildMode(target);
                this.getInventoryManager().clearInv(target);
                sender.sendMessage("§aDu hast den Spieler §b" + target.getName() + " §aerfolgreich aus dem Bau-Modus entfernt");
                this.getCommandActionManager().Action_reloadItems(target);

            }else{
                this.getPlayerManager().toggleBuildMode(target);
                this.getInventoryManager().clearInv(target);
                sender.sendMessage("§aDu hast den Spieler §b" + target.getName() + " §aerfolgreich in den Bau-Modus gesetzt");
            }
        }catch(NullPointerException | SQLException e){
            ConsoleWriter.writeErrorWithTag("this is not god please report it to me on Discord: Dragonhard117 Error: §e" + e);
        }

    }
}
