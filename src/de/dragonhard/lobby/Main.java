package de.dragonhard.lobby;

import de.dragonhard.lobby.components.ConsoleWriter;
import de.dragonhard.lobby.manager.*;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{

    Managers manager = new Managers();
    static Plugin plugin;

    public static Plugin getPlugin() {

        return plugin;

    }

    public void onEnable(){
        plugin = this;

        _setWallIdLists();
        _registerCommand();
        _registerEvents();

    }


    private void _setWallIdLists(){
        ConsoleWriter.writeWithTag("setting up Menu IDs ...");



        ConsoleWriter.writeWithTag("done");
    }

    private void _registerCommand(){
        ConsoleWriter.writeLoadingStart("loading command register");


        ConsoleWriter.writeLoadingEnd("command register loaded");
    }

    private void _registerEvents(){//required
        ConsoleWriter.writeLoadingStart("loading event register");

        //Plugin core events
        _register(manager.getEventManager().getInteractEvent());
        _register(manager.getEventManager().getJoinEvent());
        _register(manager.getEventManager().getChatEvent());
        _register(manager.getEventManager().getClickEvent());
        _register(manager.getEventManager().getDropEvent());
        _register(manager.getEventManager().getDragEvent());
        _register(manager.getEventManager().getBuildEvent());
        _register(manager.getEventManager().getEventBlocker());
        _register(manager.getEventManager().getHideEvent());
        _register(manager.getEventManager().getDamageEvent());
        _register(manager.getEventManager().getHungerEvent());
        _register(manager.getEventManager().getHealthEvent());

        ConsoleWriter.writeLoadingEnd("event register loaded");
    }

    private void _register(Listener listener){ //called from _registerEvents (required for all plugin functions!! do not change!!!)
        manager.getPluginManager().registerEvents(listener,this);
    }

}
