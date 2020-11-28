package de.dragonhard.lobby.manager;

import com.avaje.ebeaninternal.server.type.ScalarTypeChar;
import de.dragonhard.lobby.Main;
import de.dragonhard.lobby.components.ConsoleWriter;
import org.bukkit.plugin.Plugin;

import java.io.*;

public class PluginComunicationManager {

    private static Plugin plugin = Main.getPlugin();

    private static String[] channelIn = {"lbSys_Friend","lbSys_Config","lbSys_Manager","lbSys_Debug"};
    private static String[] channelOut = {"lbSys_Friend","lbSys_Config","lbSys_Manager","lbSys_Debug"};
    private static byte[] byteIn = {10,10};

    //Outgoing
    private static ByteArrayOutputStream bOut = new ByteArrayOutputStream();
    private static DataOutputStream dOut = new DataOutputStream(bOut);

    //Ingoing
    private static ByteArrayInputStream bIn = new ByteArrayInputStream(byteIn);
    private static DataInputStream dIn = new DataInputStream(bIn);

    private void onRequestError(int errorID, String request){
        switch(errorID){
            case 3: ConsoleWriter.writeWithTag("[Request - Error] the Request: " + request + " requires at least 3 args and has 2"); break;
            case 2: ConsoleWriter.writeWithTag("[Request - Error] the Request: " + request + " requires at least 3 args and has 1"); break;
            case 1: ConsoleWriter.writeWithTag("[Request - Error] the Request: " + request + " requires at least 3 args and has 0"); break;
            default: ConsoleWriter.writeWithTag("[Request - Error] the Request: " + request + " is unknown"); break;

        }
    }

    public String onRequest(){
        try{

            String[] in = dIn.readUTF().split(":");
            if(in[0].isEmpty()){onRequestError(1,"*empty*");}
            if(in[1].isEmpty()){onRequestError(2,in[0]);}
            if(in[2].isEmpty()){onRequestError(3,"*empty*");}
            if(in[3].isEmpty()){onRequestError(4,"*empty*");}
           switch(in[0]){
               case "Friend":

                   switch(in[1]){
                       case "add":
                       case "remove":
                       case "request":
                   }
               default: onRequestError(0,in[0]); break;

           }

        }catch(Exception ex){

        }

        return "";
    }

}
