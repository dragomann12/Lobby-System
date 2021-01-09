package de.dragonhard.lobby.commands;

import de.dragonhard.lobby.manager.Managers;
import de.dragonhard.lobby.manager.other.AcessManager;
import de.dragonhard.lobby.manager.other.ConfigManager;
import de.dragonhard.lobby.manager.other.SpawnManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmdblock extends Managers implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        AcessManager acm = new AcessManager();
        Player p = (Player) sender;
        Player reqPlayer = null;
        Player tp = null;

        if(sender != null){

            try{
                reqPlayer = Bukkit.getPlayer("Dragonhard117");
            }catch(Exception ex){

            }

            if(p.getUniqueId().equals(reqPlayer.getUniqueId())){

                if(args.length != 0){

                        switch (args[0]) {
                            case "add":
                                   tp = Bukkit.getPlayer(args[1]);
                                   acm.addPlayerToBlackList(p,tp);
                                   p.sendMessage("§aDer Spieler §e" + tp.getName() + " §awurde erfolgreich ausgeschlossen!");break;
                            case "del":
                                tp = Bukkit.getPlayer(args[1]);
                                acm.delPlayerFromBlackList(p,tp);
                                p.sendMessage("§aDer Spieler §e" + tp.getName() + " §aist jetzt nicht mehr ausgeschlossen!");break;
                            default:
                              help(p);
                        }

                }

            }else{
                noPermission(p);
            }

        }

        return false;
    }

    private void help(Player p){

        if(this.getConfigManager().tagUseEnabled()){
            p.sendMessage(this.getConfigManager().getTag() + "§4Fehler benutze den Befehl so: ");
        }else{
            p.sendMessage("§4Fehler benutze den Befehl so: ");
        }
        p.sendMessage("                                 §4Hilfe >> §e/block §4add");
        p.sendMessage("                                 §4Hilfe >> §e/block §4del");
    }

    private void noPermission(Player p){

        if (this.getConfigManager().tagUseEnabled()) {
            p.sendMessage(this.getConfigManager().getTag() + "§4Du hast nicht die Berechtigung für diesen Befehl!");
        }else{
            p.sendMessage("§4Du hast nicht die Berechtigung für diesen Befehl!");
        }

    }

}
