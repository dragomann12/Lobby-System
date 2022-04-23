package de.dragonhard.lobby.components.events;

import de.dragonhard.lobby.components.ConsoleWriter;
import org.bukkit.event.EventException;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class Bad_Event implements Listener {

    @EventHandler
    public void onError(EventException e){


            ConsoleWriter.write("___________________ Start of Error ___________________");
            ConsoleWriter.writeErrorWithTag("Error Detected at a Event: " + e.getCause());
            ConsoleWriter.writeErrorWithTag("Error Detected at Class: " + e.getClass());
            ConsoleWriter.writeErrorWithTag("Stacktrace: " + e.getStackTrace());
            ConsoleWriter.write("____________________ End of Error ____________________");

    }


}
