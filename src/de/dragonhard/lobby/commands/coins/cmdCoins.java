package de.dragonhard.lobby.commands.coins;

import de.dragonhard.lobby.manager.Managers;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmdCoins extends Managers implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender != null){

            Player p = (Player)sender;

            if(args[0].isEmpty()){
                if(args[0].equals("help")){help(p); return false;}
                p.sendMessage("§aDu hast §b"+ this.getCoinManager().getCoins(p) + " §aChaos-Coins");
            }else if(!args[1].isEmpty()){
                Player target = Bukkit.getPlayer(args[1]);
                switch (args[0]){

                    case "see":
                        p.sendMessage("§5"+target.getName() + " §bhat §a" + this.getCoinManager().getCoins(target) + " CC");break;
                    case "set":
                        if(args[2].isEmpty()){help(p);}
                        this.getCoinManager().setCoins(target, Integer.parseInt(args[2]));break;
                    case "add":
                        if(args[2].isEmpty()){help(p);}
                        this.getCoinManager().setCoins(target, this.getCoinManager().getCoins(p) + Integer.parseInt(args[2]));break;
                }
            }else{
                help(p);
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

        p.sendMessage("                                 §4Hilfe >> §e/coins  §4>> zeigt deine Chaos-Coins");
        p.sendMessage("                                 §4Hilfe >> §e/coins §4see [§ename§4] §4>> zeigt die Chaos-Coins eines Spielers");
        p.sendMessage("                                 §4Hilfe >> §e/coins §4set [§ename§4] [§evalue§4] §4>> setzt die Chaos-Coins eines Spielers");
        p.sendMessage("                                 §4Hilfe >> §e/coins §4add [§ename§4] [§evalue§4] §4>> gibt einem Spieler Chaos-Coins");

    }
}
