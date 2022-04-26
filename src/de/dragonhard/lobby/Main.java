package de.dragonhard.lobby;

import de.dragonhard.lobby.components.ConsoleWriter;
import de.dragonhard.lobby.components.menu.Menu;
import de.dragonhard.lobby.manager.*;
import de.dragonhard.lobby.manager.other.MenuManager;
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

        _registerEvents();
        _menu_register();

    }

    private void _menu_register(){
        Menu    menu_lobby = new Menu("Lobby","5",6),
                menu_backpack = new Menu("Rucksack","5",6),
                menu_group = new Menu("Gruppen","5",6);

        MenuManager.addMenu("lobby",menu_lobby);
        MenuManager.addMenu("backpack",menu_backpack);
        MenuManager.addMenu("group",menu_group);


    }

    private void _registerEvents(){
        ConsoleWriter.writeLoadingStart("loading event register");

        _register(manager.getEventManager().getInteractEvent());
        _register(manager.getEventManager().getJoinEvent());
        _register(manager.getEventManager().getChatEvent());
        _register(manager.getEventManager().getClickEvent());
        _register(manager.getEventManager().getDropEvent());
        _register(manager.getEventManager().getDragEvent());
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
