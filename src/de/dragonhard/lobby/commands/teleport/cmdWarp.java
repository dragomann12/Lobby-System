package de.dragonhard.lobby.commands.teleport;

import de.dragonhard.lobby.components.PermissionList;
import de.dragonhard.lobby.manager.Managers;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmdWarp extends Managers implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;

        if(sender != null){

            if(p.hasPermission(PermissionList.getPermission("Use",0))){

                if(args.length != 0){

                        switch(args[0]){

                            case "config": // /warp config set/reset <player> <type> <value>
                                if(!args[1].isEmpty() && p.hasPermission(PermissionList.getPermission("Config",2))){

                                    if ("set".equals(args[1])) {
                                        if (!args[2].isEmpty()) {

                                            try {
                                                Player tp = Bukkit.getPlayer(args[2]);

                                                if (!args[3].isEmpty()) {

                                                    switch (args[3]) {
                                                        case "MaxWarps":
                                                            if (!args[4].isEmpty()) {
                                                                this.getPlayerManager().setMaxWarps(p, Integer.parseInt(args[4]));
                                                            } else {
                                                                help(p);
                                                            }
                                                            break;
                                                        case "Inf":
                                                            if (!args[4].isEmpty()) {
                                                                this.getPlayerManager().setInf(p, args[4]);
                                                            } else {
                                                                help(p);
                                                            }
                                                            break;
                                                        default:
                                                            help(p);
                                                    }

                                                } else {
                                                    help(p);
                                                }

                                            } catch (Exception e) {
                                                if (this.getConfigManager().tagUseEnabled()) {
                                                    p.sendMessage(this.getConfigManager().getTag() + "§4Der Spieler §e" + args[2] + " §4ist nicht Online oder wurde falsch geschrieben!");

                                                } else {
                                                    p.sendMessage("§4Der Spieler §e" + args[2] + " §4ist nicht Online oder wurde falsch geschrieben!");

                                                }
                                            }

                                        } else {
                                            help(p);
                                        }
                                    } else {
                                        help(p);
                                    }

                                }else{ help(p); } break;

                            case "set":
                                if (this.getPlayerManager().isWarpEnabled(p)) {
                                    if(!args[1].isEmpty()){

                                        if(this.getPlayerManager().hasInf(p)){
                                            if(!this.getWarpManager().exists(p, args[1])){
                                                this.getWarpManager().createWarp(p, args[1],p.getWorld());
                                                this.getPlayerManager().addWarpToCount(p);
                                            }
                                        }else{
                                            if(this.getPlayerManager().getCurrentWarps(p) != this.getPlayerManager().getMaxWarps(p)){
                                                if(!this.getWarpManager().exists(p, args[1])){
                                                    this.getWarpManager().createWarp(p, args[1],p.getWorld());
                                                    this.getPlayerManager().addWarpToCount(p);
                                                }
                                            }else{
                                                p.sendMessage("§4Du hast alle deine Warps in Verwändung!");
                                                p.sendMessage("§4lösche einen um einen neuen setzen zu können!");
                                            }
                                        }


                                    }else{help(p);}
                                }else {
                                    if(this.getConfigManager().tagUseEnabled()) {
                                        p.sendMessage( this.getConfigManager().getTag() + "§4Die Warp Funktion ist derzeit nicht verfügbar!");
                                        p.sendMessage(this.getConfigManager().getTag() + "§4Bitte an das Server-Team melden!");
                                    } else {
                                        p.sendMessage("§4Die Warp Funktion ist derzeit nicht verfügbar!");
                                        p.sendMessage("§4Bitte an das Server-Team melden!");
                                    }
                                    break;
                                } break;


                            case "del":
                                if(!args[1].isEmpty()) {

                                    if (args[1].equals("*")) {

                                        p.sendMessage("§asuche alle warps ...");
                                        this.getWarpManager().delAllWarps(p);
                                        p.sendMessage("§aAlle warps wurden erfolgreich entfernt!");
                                    }else {

                                        if (this.getWarpManager().exists(p, args[1]) && this.getPlayerManager().getCurrentWarps(p) != 0) {
                                            this.getPlayerManager().delWarpFromCount(p);
                                        }
                                        this.getWarpManager().delWarp(p, args[1]);
                                    }

                                }else{help(p);}

                                break;

                            case "count":
                                if(this.getConfigManager().tagUseEnabled()){
                                    if(this.getPlayerManager().hasInf(p)){
                                        p.sendMessage(this.getConfigManager().getTag() + "§eDu benutzt §4" + this.getPlayerManager().getCurrentWarps(p) + "§e/§a unbegrenzt §eWarps");
                                    }else{
                                        p.sendMessage(this.getConfigManager().getTag() + "§eDu benutzt §4" + this.getPlayerManager().getCurrentWarps(p) + "§e/§4" + this.getPlayerManager().getMaxWarps(p) + " §eWarps");
                                    }
                                }else{
                                    if(this.getPlayerManager().hasInf(p)){
                                        p.sendMessage("§eDu benutzt §4" + this.getPlayerManager().getCurrentWarps(p) + "§e/§a unbegrenzt §eWarps");
                                    }else{
                                        p.sendMessage("§eDu benutzt §4" + this.getPlayerManager().getCurrentWarps(p) + "§e/§4" + this.getPlayerManager().getMaxWarps(p) + " §eWarps");
                                    }
                                }
                                break;

                            case "ls": this.getWarpManager().getWarpList(p); break;

                                default:
                                    if(this.getPlayerManager().isWarpEnabled(p)){
                                        this.getWarpManager().teleportPlayer(p, args[0]);
                                    }else{
                                        p.sendMessage("§4Die Warp Funktion ist derzeit nicht verfügbar!");
                                        p.sendMessage("§4Bitte an das Server-Team melden!");
                                    }

                        }

                }else{

                 help(p);
                }

            }else{
                if(this.getConfigManager().tagUseEnabled()){
                    p.sendMessage(this.getConfigManager().getTag() + "§4Du hast nicht die Berechtigung für diesen Befehl!");
                }else {
                    p.sendMessage("§4Du hast nicht die Berechtigung für diesen Befehl!");
                }
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

        p.sendMessage("                                 §4Hilfe >> §e/warp §4[§ename§4]");
        p.sendMessage("                                 §4Hilfe >> §e/warp §4count");
        p.sendMessage("                                 §4Hilfe >> §e/warp §4config");
        p.sendMessage("                                 §4Hilfe >> §e/warp §4set [§ename§4]");
        p.sendMessage("                                 §4Hilfe >> §e/warp §4del [§ename§4]");
        p.sendMessage("                                 §4Hilfe >> §e/warp §4ls");
    }

}
