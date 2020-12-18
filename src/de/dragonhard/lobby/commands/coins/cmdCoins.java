package de.dragonhard.lobby.commands.coins;

import de.dragonhard.lobby.components.PermissionList;
import de.dragonhard.lobby.manager.CoinManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmdCoins extends CoinManager implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender != null){

            Player p = (Player)sender;

            if(args.length != 0) {

                if(!args[0].isEmpty()){

                    if(!args[1].isEmpty()){

                        switch(args[1]){

                            case "see":

                                if(p.hasPermission(PermissionList.getPermission("see",0))){

                                    if(!args[2].isEmpty()){
                                        try{
                                            Player target = Bukkit.getPlayer(args[2]);

                                            p.sendMessage("§5" + target.getName() + "§bhat §5" + this.getCoins(target) + " §bCC");

                                        }catch(NullPointerException e){

                                        }

                                    }

                                }else{p.sendMessage("§4Du hast keine Berechtigung!");}

                                break;

                            case "set":
                                if(p.hasPermission(PermissionList.getPermission("edit",0))){

                                    if(!args[2].isEmpty()){
                                        try{
                                            Player target = Bukkit.getPlayer(args[2]);
                                            int amount = Integer.parseInt(args[3]);

                                            this.setCoins(target, amount);
                                            p.sendMessage("§aDu hast die Coins von §b" + target.getName() + " §aauf §b" + amount + "§aCC gesetzt" );
                                        }catch(NullPointerException e){

                                        }

                                    }

                                }else{p.sendMessage("§4Du hast keine Berechtigung!");}
                        }

                    }else{

                        p.sendMessage("§aDu hast §b" + this.getCoins(p) + " §aCC");

                    }

                }else{



                }

            }

        }

        return false;
    }
}
