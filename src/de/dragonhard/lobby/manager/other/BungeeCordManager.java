package de.dragonhard.lobby.manager.other;

import de.dragonhard.lobby.Main;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class BungeeCordManager {

    private static Plugin plugin = Main.getPlugin();
    private static String channel = "BungeeCord";

    private static ByteArrayOutputStream b = new ByteArrayOutputStream();
    private static DataOutputStream out = new DataOutputStream(b);
    private static String read = "";

    public static void sendPlayerToServer(Player p, String server){

        try{

            out.writeUTF("Connect");
            out.writeUTF(server);

            p.sendMessage("§averbinde zum Server: §e" + server);
            p.sendPluginMessage(plugin, channel,b.toByteArray());

        }catch(Exception ex){
            p.sendMessage("§4Verbindung zum Server §e" + server + " §4nicht möglich!");
        }
    }

    public static void kickPlayer(Player p, String playerName){
        try{

            out.writeUTF("KickPlayer");
            out.writeUTF(playerName);
            out.writeUTF("§4Du wurdest vom Server gekickt!");

            p.sendPluginMessage(plugin, channel,b.toByteArray());

        }catch(Exception ex){
            p.sendMessage("§4Der Spieler konnte nicht gekickt werden!");
        }
    }

    public static void kickPlayer(Player p, String playerName, String reason){
        try{

            out.writeUTF("KickPlayer");
            out.writeUTF(playerName);
            out.writeUTF("§4Du wurdest vom Server gekickt! Grund: §e" + reason);

            p.sendPluginMessage(plugin, channel,b.toByteArray());

        }catch(Exception ex){
            p.sendMessage("§4Der Spieler konnte nicht gekickt werden!");
        }
    }

    public static String getChannel(){
        return channel;
    }

}
