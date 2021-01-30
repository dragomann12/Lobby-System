package de.dragonhard.lobby.commands;

import de.dragonhard.lobby.manager.Managers;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmdPlayerInfo extends Managers implements CommandExecutor {
    final String item_color = "§6";
    final String item_value_color = "§e";

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
                             Player target = Bukkit.getPlayer(args[0]);
                             p.sendMessage("§f￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭");
                             p.sendMessage("                  §bÜbersicht");
                             p.sendMessage("§f￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭  §bSpieler§f-§bInfo");

                             p.sendMessage(item_color + "Name: " + item_value_color + target.getName());
                             p.sendMessage(item_color + "Nickname: " + item_value_color + item_value_color + target.getDisplayName());
                             p.sendMessage(item_color + "UUID: " + item_value_color + target.getUniqueId());
                             p.sendMessage(item_color + "Operator: " + item_value_color + target.isOp());
                             p.sendMessage(item_color + "Login: " + item_value_color + this.getDateManager().getPlayerDate(target));
                             p.sendMessage(item_color + "Chaos-Coins: " + item_value_color + this.getConnectionManager().callRowCoins(p));

                             p.sendMessage("§f￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭  §bGruppe");

                             String group = "";
                             if(this.getPluginWhitelistManager().isTeam(target)){
                                 group = "Team";
                             }else{
                                 group = "Standard";
                             }
                             p.sendMessage(item_color + "Gruppe: " + item_value_color + group);
                             p.sendMessage(item_color + "Rang: " + item_value_color + this.getConfigManager().getAccessLevelTag(Integer.parseInt(this.getConnectionManager().callRowLevel(target))));

                             p.sendMessage("§f￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭  §bNetzwerk");

                             String[] ip_port = target.getAddress().toString().replace("/","").split(":");
                             String ip = ip_port[0];
                             String port = ip_port[1];
                             p.sendMessage(item_color + "IP-Adresse: " + item_value_color + ip);
                             p.sendMessage(item_color + "Port: " + item_value_color + port);

                             p.sendMessage("§f￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭  §bstats");
                             p.sendMessage("      §4nach Update verfügbar");
                             p.sendMessage("§f￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭  §bsonstiges");

                             p.sendMessage(item_color + "läuft: " + item_value_color + target.isSprinting());
                             p.sendMessage(item_color + "schläft: " + item_value_color + target.isSleeping());
                             p.sendMessage(item_color + "fliegt: " + item_value_color + target.isFlying());
                             p.sendMessage(item_color + "schleicht: " + item_value_color + target.isSneaking());

                             p.sendMessage("§f￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭");
                         }catch (NullPointerException e){
                             p.sendMessage("§4Fehler§e: §4Der Spieler §e" + args[0] + " §4ist offline oder existiert nicht!");
                         }

                     }

                 }else{
                     p.sendMessage("§4Fehler§e: §4du hast nicht die Berechtigung!");
                 }

        }

        return false;
    }
}
