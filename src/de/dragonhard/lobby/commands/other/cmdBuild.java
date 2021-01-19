package de.dragonhard.lobby.commands.other;

import de.dragonhard.lobby.components.util.InventorySetter;
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
            if(args.length == 0) {p.sendMessage("§4Fehler§e: §e/§4bm §e<§4player§e>"); return false;}
            Player target = Bukkit.getPlayer(args[0]);
            if(this.getPluginWhitelistManager().isPluginDeveloper(p)){
                setMode(p,target);
            }else if(this.getPluginWhitelistManager().isOwner(p)){
                setMode(p,target);
            }else if(this.getPluginWhitelistManager().isTeam_lead(p)){
                setMode(p,target);
            }else if(this.getPluginWhitelistManager().isAdmin(p)){
                setMode(p,target);
            }else if(this.getPluginWhitelistManager().isDeveloper(p)){
                setMode(p,target);
            }else if(this.getPluginWhitelistManager().isBuilder(p)) {
                setMode(p,target);
            }else {
                p.sendMessage("§4Fehler§e: §4Du hast keine Berechtigung");
            }
        }

        return false;
    }

    private void setMode(Player sender ,Player target){
        try{
            if(target == null){sender.sendMessage("§4Fehler§e: §4kein Spieler als Ziel angegeben!");return;}
            if(target.getName().equals(sender.getName())){this.getPlayerManager().toggleBuildMode(target); this.getInventoryManager().clearInv(target); reloadItems(target); return;}
            if(this.getPlayerManager().isBuildModeEnabled(target)){
                this.getPlayerManager().toggleBuildMode(target);
                this.getInventoryManager().clearInv(target);
                sender.sendMessage("§aDu hast den Spieler §b" + target.getName() + " §aerfolgreich aus dem Bau-Modus entfernt");
                reloadItems(target);

            }else{
                this.getPlayerManager().toggleBuildMode(target);
                this.getInventoryManager().clearInv(target);
                sender.sendMessage("§aDu hast den Spieler §b" + target.getName() + " §aerfolgreich in den Bau-Modus gesetzt");
            }
        }catch(NullPointerException e){

        }

    }

    private void reloadItems(Player p){
        InventorySetter is = new InventorySetter();
        if(!this.getPlayerManager().isBuildModeEnabled(p)){
            is.getHotbarItems(p);
        }
    }
}
