package de.dragonhard.lobby.commands.teleport;

import de.dragonhard.lobby.components.Message;
import de.dragonhard.lobby.components.PermissionList;
import de.dragonhard.lobby.manager.ConfigManager;
import de.dragonhard.lobby.manager.LanguageManager;
import de.dragonhard.lobby.manager.PlayerConfigManager;
import de.dragonhard.lobby.manager.WarpManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmdWarp extends WarpManager implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        PlayerConfigManager pm = new PlayerConfigManager();
        ConfigManager cm = new ConfigManager();

        Player p = (Player) sender;

        if(sender != null){

            if(p.hasPermission(PermissionList.getPermission("Use",0))){

                if(args.length != 0){

                        switch(args[0]){

                            case "config": // /warp config set/reset <player> <type> <value>
                                if(!args[1].isEmpty() && p.hasPermission(PermissionList.getPermission("Config",2))){

                                    switch(args[1]){

                                        case "set":
                                            if(!args[2].isEmpty()){

                                                try{
                                                    Player tp = Bukkit.getPlayer(args[2]);

                                                    if(!args[3].isEmpty()){

                                                        switch(args[3]){
                                                            case "MaxWarps":
                                                                if(!args[4].isEmpty()){
                                                                    pm.setMaxWarps(p,Integer.parseInt(args[4]));
                                                                }else{
                                                                    help(p);
                                                                } break;
                                                            case "Inf":
                                                                if(!args[4].isEmpty()){
                                                                    pm.setInf(p, args[4]);
                                                                }else{
                                                                    help(p);
                                                                } break;
                                                            default:help(p);
                                                        }

                                                    }else{
                                                        help(p);
                                                    }

                                                }catch(Exception e){
                                                        if(cm.tagUseEnabled()){
                                                            p.sendMessage(cm.getTag() + "§4Der Spieler §e" + args[2] + " §4ist nicht Online oder wurde falsch geschrieben!");

                                                        }else{
                                                            p.sendMessage("§4Der Spieler §e" + args[2] + " §4ist nicht Online oder wurde falsch geschrieben!");

                                                        }
                                                }

                                              }else{
                                                help(p);
                                            } break;

                                        default: help(p);
                                    }

                                }else{ help(p); } break;

                            case "set":
                                if (pm.isWarpEnabled(p)) {
                                    if(!args[1].isEmpty()){

                                        if(pm.hasInf(p)){
                                            if(!this.exists(p, args[1])){
                                                this.createWarp(p, args[1],p.getWorld());
                                                pm.addWarpToCount(p);
                                            }
                                        }else{
                                            if(pm.getCurrentWarps(p) != pm.getMaxWarps(p)){
                                                if(!this.exists(p, args[1])){
                                                    this.createWarp(p, args[1],p.getWorld());
                                                    pm.addWarpToCount(p);
                                                }
                                            }else{
                                                p.sendMessage("§4Du hast alle deine Warps in Verwändung!");
                                                p.sendMessage("§4lösche einen um einen neuen setzen zu können!");
                                            }
                                        }


                                    }else{help(p);}
                                }else {
                                    if(cm.tagUseEnabled()) {
                                        p.sendMessage( cm.getTag() + "§4Die Warp Funktion ist derzeit nicht verfügbar!");
                                        p.sendMessage(cm.getTag() + "§4Bitte an das Server-Team melden!"); break;
                                    } else {
                                        p.sendMessage("§4Die Warp Funktion ist derzeit nicht verfügbar!");
                                        p.sendMessage("§4Bitte an das Server-Team melden!"); break;
                                    }
                                } break;


                            case "del": if(!args[1].isEmpty()){
                                if(this.exists(p,args[1]) && pm.getCurrentWarps(p) != 0){
                                    pm.delWarpFromCount(p);
                                }
                                this.delWarp(p,args[1]);}else { help(p);} break;

                            case "count":
                                if(cm.tagUseEnabled()){
                                    if(pm.hasInf(p)){
                                        p.sendMessage(cm.getTag() + "§eDu benutzt §4" + pm.getCurrentWarps(p) + "§e/§a unbegrenzt §eWarps");
                                    }else{
                                        p.sendMessage(cm.getTag() + "§eDu benutzt §4" + pm.getCurrentWarps(p) + "§e/§4" + pm.getMaxWarps(p) + " §eWarps");
                                    } break;
                                }else{
                                    if(pm.hasInf(p)){
                                        p.sendMessage("§eDu benutzt §4" + pm.getCurrentWarps(p) + "§e/§a unbegrenzt §eWarps");
                                    }else{
                                        p.sendMessage("§eDu benutzt §4" + pm.getCurrentWarps(p) + "§e/§4" + pm.getMaxWarps(p) + " §eWarps");
                                    } break;
                                }

                            case "ls": this.getWarpList(p); break;

                                default:
                                    if(pm.isWarpEnabled(p)){
                                        this.teleportPlayer(p, args[0]);
                                    }else{
                                        p.sendMessage("§4Die Warp Funktion ist derzeit nicht verfügbar!");
                                        p.sendMessage("§4Bitte an das Server-Team melden!");
                                    }

                        }

                }else{

                 help(p);

                }

            }else{
                if(cm.tagUseEnabled()){
                    p.sendMessage(cm.getTag() + "§4Du hast nicht die Berechtigung für diesen Befehl!");
                }else {
                    p.sendMessage("§4Du hast nicht die Berechtigung für diesen Befehl!");
                }
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

        p.sendMessage("                                 §4Hilfe >> §e/warp §4[§ename§4]");
        p.sendMessage("                                 §4Hilfe >> §e/warp §4count");
        p.sendMessage("                                 §4Hilfe >> §e/warp §4config");
        p.sendMessage("                                 §4Hilfe >> §e/warp §4set [§ename§4]");
        p.sendMessage("                                 §4Hilfe >> §e/warp §4del [§ename§4]");
    }

}
