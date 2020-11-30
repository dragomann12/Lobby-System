package de.dragonhard.lobby.commands;

import de.dragonhard.lobby.components.Message;
import de.dragonhard.lobby.components.PermissionList;
import de.dragonhard.lobby.manager.AcessManager;
import de.dragonhard.lobby.manager.ConfigManager;
import de.dragonhard.lobby.manager.SpawnManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmdblock extends SpawnManager implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        ConfigManager cm = new ConfigManager();
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

    private static void help(Player p){
        ConfigManager cm = new ConfigManager();

        if(cm.tagUseEnabled()){
            p.sendMessage(cm.getTag() + "§4Fehler benutze den Befehl so: ");
        }else{
            p.sendMessage("§4Fehler benutze den Befehl so: ");
        }
        p.sendMessage("                                 §4Hilfe >> §e/block §4add");
        p.sendMessage("                                 §4Hilfe >> §e/block §4del");
    }

    private static void noPermission(Player p){
        ConfigManager cm = new ConfigManager();

        if (cm.tagUseEnabled()) {
            p.sendMessage(cm.getTag() + "§4Du hast nicht die Berechtigung für diesen Befehl!");
        }else{
            p.sendMessage("§4Du hast nicht die Berechtigung für diesen Befehl!");
        }

    }

}
