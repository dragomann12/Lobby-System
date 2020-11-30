package de.dragonhard.lobby.manager;

import de.dragonhard.lobby.Main;
import de.dragonhard.lobby.components.ConsoleWriter;
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

    }
}
