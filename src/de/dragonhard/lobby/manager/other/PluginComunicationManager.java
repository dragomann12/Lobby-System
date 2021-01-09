package de.dragonhard.lobby.manager.other;

import de.dragonhard.lobby.Main;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.*;

public class PluginComunicationManager implements PluginMessageListener {

    private static Plugin plugin = Main.getPlugin();

    private static final String channelIn = "lbSys_In";
    private static final String channelOut = "lbSys_Out";
    private static byte[] byteIn = {10,10};

    //Outgoing
    private static ByteArrayOutputStream bOut = new ByteArrayOutputStream();
    private static DataOutputStream dOut = new DataOutputStream(bOut);

    //Ingoing
    private static ByteArrayInputStream bIn = new ByteArrayInputStream(byteIn);
    private static DataInputStream dIn = new DataInputStream(bIn);

    public static String getChannelOut(){
        return channelOut;
    }

    public static String getChannelIn(){
        return channelIn;
    }

    public PluginMessageListener getListener(){
        return this;
    }
    @Override
    public void onPluginMessageReceived(String msg, Player p, byte[] b) {
        PluginWithlistManager pwm = new PluginWithlistManager();
        if(msg.contains("/")){
            String[] args = msg.split("/");

            switch(args[0]){    // base requests where no accessKey is required
                case "get":
                    if(pwm.isDeveloper(p) || pwm.isTeam_lead(p) || pwm.isOwner(p)){
                        switch(args[1]){
                            case "help connect Plugin": p.sendMessage("§aUm ein Plugin mit dem Lobby-System zu verbinden ist ein Zugangsschlüssel erforderlich dieser wird in der Datenbank zu finden sein");break;
                            default: p.sendMessage("§4Anfrage nicht bekannt");break;
                        }
                    }else{
                        p.sendMessage("§4Du besitzt nicht die erforderliche Berechtigung");
                    }break;
                default: p.sendMessage("§4Anfrage nicht bekannt");break;
            }

        }


    }
}
