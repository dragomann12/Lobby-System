package de.dragonhard.lobby.manager.other;

import de.dragonhard.lobby.components.ConsoleWriter;
import de.dragonhard.lobby.components.colorGenerator;
import de.dragonhard.lobby.components.menu.admin.Admin_Menu;
import de.dragonhard.lobby.components.menu.creativ.Creativ_Menu;
import de.dragonhard.lobby.components.menu.debug.debug_Menu;
import de.dragonhard.lobby.components.menu.player.Player_Menu;
import de.dragonhard.lobby.manager.Managers;
import de.dragonhard.lobby.manager.database.ConnectionManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class MessageManager extends Managers {
    private static ConfigManager cm = new ConfigManager();
    public static String prefix = cm.getMessagePrefix();
    private static String[] seperator = {"/","#", ">>"};

    public void getChatFunktion(String message, Player p){

        brodcast.sendChat(p, message, seperator[2]);

    }

    public void getFunktion(String message, Player p) {

            GlobalWarpManager gwm = new GlobalWarpManager();
        if (message.startsWith(prefix + "config")) {

        } else if (message.startsWith(prefix + "alert")) {
            String msg = message.replaceFirst(prefix + "alert", "");
            brodcast.sendAlert(msg);
        } else if (message.startsWith(prefix + "info")) {
            String msg = message.replaceFirst(prefix + "info", "");
            brodcast.sendInfo(msg);
        } else if (message.startsWith(prefix + "warn")) {
            String msg = message.replaceFirst(prefix + "warn", "");
            brodcast.sendWarning(msg);
        } else if (message.startsWith(prefix + "#restart")) {
            String msg = message.replaceFirst(prefix + "#restart", "");
            brodcast.sendRestart();
        } else if (message.startsWith(prefix + "#service")) {
            String msg = message.replaceFirst(prefix + "#service", "");
            brodcast.sendService();
        } else if (message.startsWith(prefix + "mode")){
            Admin_Menu am = new Admin_Menu();
            am.openInventory(p);
        } else if (message.startsWith(prefix + "testInv")){
            Creativ_Menu creative = new Creativ_Menu();
            creative.openInventory(p);
        } else if (message.startsWith(prefix + "devInv")){
            debug_Menu dm = new debug_Menu();
            dm.openInventory(p);
        }else if (message.startsWith(prefix + "cGen")){
            colorGenerator cGen = new colorGenerator();
            p.sendMessage("Nummer: " + cGen.getColor(9,"test-generator"));
        }else if (message.startsWith(prefix + "checkUpdate")){
           if(!cm.isCurrentVersion()){
               ConsoleWriter.writeWithTag("[Update] Ein Update für die Config ist verfügbar!");
               p.sendMessage("§eEin Update für die Config ist verfügbar!");
               cm.setUpdateReady(true);
           }else{
               ConsoleWriter.writeWithTag("[Update] Es ist kein Update verfügbar!");
               p.sendMessage("§aEs ist kein Update verfügbar!");
               cm.setUpdateReady(false);
           }

        }else if (message.startsWith(prefix + "update")){
        if(cm.isUpdateReady()) {
            p.sendMessage("§aDas Update wird geladen ...");
            cm.updateConfig();
        }else{
            p.sendMessage("§4Es ist kein Update bereit!");
        }
        }else if (message.startsWith(prefix + "playSound")){
            SoundManager sm = new SoundManager();
            String[] msg = message.split(" ");
            sm.getSoundOfString(msg[1]);

                sm.play(p, msg[1]);


        }else if (message.startsWith(prefix + "player")){
            Player_Menu plm = new Player_Menu();
            plm.openInventory(p);
        }
    }

}

class brodcast{

    public static void sendChat(Player p, String message, String sep){

        PlayerConfigManager pm = new PlayerConfigManager();
        String msg = message.replaceAll(" /c ","§");
        Bukkit.broadcastMessage("§" + pm.getChatNameColor(p) + p.getName() + "  §" +  pm.getChatSeperatorColor(p) + sep + "  §f" + msg);

    }

    public static void sendAlert(String Message){
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage("§4§l[§e§lAlarm§4§l] §e§l" + Message);
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage("");
    }

    public static void sendInfo(String Message){
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage("§f§l[§b§lInformation§f§l] §b§l" + Message );
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage("");
    }

    public static void sendWarning(String Message){
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage("§e§l[§4§lAchtung§e§l] §4§l"  + Message);
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage("");
    }

    public static void sendRestart(){
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage("§e§l[§4§lAchtung - Neustart§e§l] §4§lDer Server wird gleich neu gestartet!");
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage("");
    }

    public static void sendService(){
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage("§e§l[§4§lWartungsarbeiten§e§l] §4§lDer Server wird auf Grund von Wartungsarbeiten gleich gestoppt!");
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage("");
    }

}