package de.dragonhard.lobby.components;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Message {

   public static void sendToPlayer(Player sender, Player target, String message){

       if(Bukkit.getPlayer(target.getUniqueId()) != null){
           target.sendMessage( "§f" + sender.getName() + " §e-> §f" + message);
           return;
       }

       sender.sendMessage("§4Der Spieler wurde nicht gefunden!");

   }

   public static void sendNotify(Player player, String color, String message){

       player.sendMessage("§" + color + " " + message);

   }

   public static void sendNotifyByServer(Player player, String color, String message){

       player.sendMessage("§f[§b" + Bukkit.getServer().getName() + "§e] §" + color + " " + message);

   }

   public static void clearChat(Player player){
       int amount = 100;
       boolean showChatClearBy = true;

       for(int i = 0; i < amount; i ++){
           Bukkit.broadcastMessage(" ");
       }

       if(showChatClearBy){
           Bukkit.broadcastMessage("§aChat von §e" + player.getName() + " §ageleert");
       }
   }
}
