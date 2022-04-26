package de.dragonhard.lobby.components.bot;

import de.dragonhard.lobby.components.Message;
import org.jibble.pircbot.IrcException;
import org.jibble.pircbot.PircBot;

import java.io.IOException;
import java.util.Arrays;

public class Bot extends PircBot{
    String con_channel;
    String con_auth;
    String con_name;

    public Bot(String name, String channel, String auth){
        con_channel = "#" + channel;
        con_auth = "oauth:" + auth;
        con_name = name;
        System.out.println(con_channel);
        System.out.println(con_auth);
    }

    public void initialize() throws IOException, IrcException {


        this.setName(con_name);
        this.connect("irc.chat.twitch.tv",6667, con_auth);
        this.joinChannel(con_channel);
        this.setVerbose(true);

        if(isConnected()) {
            System.out.println("[Service] Bot initialized");
            return;
        }
        System.err.println("[Bot] unable to connect!");
    }

    public void onMessage(String channel, String sender, String login, String hostname, String message){

        Message.broadcast(sender, message);

    }

}
