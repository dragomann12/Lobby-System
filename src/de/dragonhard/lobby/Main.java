package de.dragonhard.lobby;

import de.dragonhard.lobby.commands.*;
import de.dragonhard.lobby.commands.network.cmdCreateServer;
import de.dragonhard.lobby.commands.other.cmdBuild;
import de.dragonhard.lobby.commands.teleport.cmdGlobalWarp;
import de.dragonhard.lobby.commands.teleport.cmdSpawn;
import de.dragonhard.lobby.commands.teleport.cmdWarp;
import de.dragonhard.lobby.components.ConsoleWriter;
import de.dragonhard.lobby.manager.*;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{

/*
TODO add Yes/No question to admin items
 */
    Managers manager = new Managers();
    static Plugin plugin;

    public static Plugin getPlugin() {

        return plugin;

    }

    public void onEnable(){
        plugin = this;
        if(manager.getConfigManager().configExists()){

                _setWallIdLists();

                _registerCommand(); // Register the Commands
                _registerEvents(); // Register the Events

                ConsoleWriter.writeWithTag("Enabled!");

                manager.getPluginWhitelistManager().onLoad();

        }else{
            ConsoleWriter.writeWithTag("installing ...");
            _loadMenuConfig(); // load the Config for the Menu
            manager.getConfigManager().getDefaultConfig();
        }

    }


    private void _setWallIdLists(){
        ConsoleWriter.writeWithTag("setting up Menu IDs ...");

        //Admin-Menu
        manager.getMenuManager().getAdminMenu().addWallIDs();
        manager.getMenuManager().getAdminServerMenu().addWallIDs();
        manager.getMenuManager().getAdminExternalMenu().addWallIDs();
        //Shop-Menu
        manager.getMenuManager().getShopMenu().addWallIDs();
        manager.getMenuManager().getCoinShopMenu().addWallIDs();
        //Game-Menu
        manager.getMenuManager().getGameMenu().addWallIDs();
        //Lobby-Menu
        manager.getMenuManager().getLobbyMenu().addWallIDs();
        //Friend-Menu
        manager.getMenuManager().getFriendMenu().addWallIDs();
        //Player-Menu
        manager.getMenuManager().getPlayerMenu().addWallIDs();

        ConsoleWriter.writeWithTag("done");
    }

    private void _loadMenuConfig(){                                         //required for all menu functions!! do not change!!!
        ConsoleWriter.writeLoadingStart("loading menu configuration");

        _loadConfig("creativ","BEACON",5);
        _loadConfig("shop","DIAMOND",5);
        _loadConfig("Admin","BEACON",5);
        _loadConfig("Settings","DIAMOND",5);
        _loadConfig("Lobby","NETHER_STAR",5);
        _loadConfig("Game","DIAMOND",5);
        _loadConfig("Coin_shop","DIAMOND",5);
        _loadConfig("Friend","DIAMOND",5);
        _loadConfig("Debug","DIAMOND",5);
        _loadConfig("Admin_External","DIAMOND",5);
        _loadConfig("Player","DIAMOND",5);

        ConsoleWriter.writeLoadingEnd("menu configuration loaded");
    }

    private void _registerCommand(){                                        //required for all plugin functions!! do not change!!!
        ConsoleWriter.writeLoadingStart("loading command register");

        this.getCommand("warp").setExecutor(new cmdWarp());
        this.getCommand("spawn").setExecutor(new cmdSpawn());
        this.getCommand("InvWarp").setExecutor(new cmdGlobalWarp());
        this.getCommand("block").setExecutor(new cmdblock());
        this.getCommand("chclear").setExecutor(new cmdClear());
        this.getCommand("crServer").setExecutor(new cmdCreateServer());
        this.getCommand("bm").setExecutor(new cmdBuild());
        this.getCommand("info").setExecutor(new cmdPlayerInfo());
        this.getCommand("lbv").setExecutor(new cmdVersion());
        this.getCommand("lbHelp").setExecutor(new cmdHelp());

        ConsoleWriter.writeLoadingEnd("command register loaded");

    }

    private void _loadConfig(String menuName, String material ,int lineAmount){//required
        manager.getConfigManager().setFile(menuName + "_menu_config");

        manager.getConfigManager().setDefault("Title", menuName);
        manager.getConfigManager().setDefault("Title_Color","5");
        manager.getConfigManager().setDefault("Hotbar_Item_Title", menuName);
        manager.getConfigManager().setDefault("Hotbar_Item_Title_Color","5");
        manager.getConfigManager().setDefault("Hotbar_Item_Material", material);
        int i = 0;
        int items = lineAmount * 9;

        for (i=0; i< items +1; i++ ){

            manager.getConfigManager().setDefault("Slot_"+i+"_Item_Title","");
            manager.getConfigManager().setDefault("Slot_"+i+"_Item_Title_Color","");
            manager.getConfigManager().setDefault("Slot_"+i+"_Item_Material","STAINED_GLASS_PANE");
            manager.getConfigManager().setDefault("Slot_"+i+"_isEnabled",false);
        }

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
        _register(manager.getEventManager().getDisconnectEvent());
        _register(manager.getEventManager().getDamageEvent());
        _register(manager.getEventManager().getHungerEvent());
        _register(manager.getEventManager().getHealthEvent());
        _register(manager.getConnectionManager());

        //Menu internal Events only
        _register(manager.getMenuManager().getLobbyMenu());
        _register(manager.getMenuManager().getShopMenu());
        _register(manager.getMenuManager().getAdminMenu());
        _register(manager.getMenuManager().getGameMenu());
        _register(manager.getMenuManager().getSettingsMenu());
        _register(manager.getMenuManager().getAdminExternalMenu());
        _register(manager.getMenuManager().getAdminServerMenu());
        _register(manager.getMenuManager().getPlayerMenu());
        _register(manager.getMenuManager().getCoinShopMenu());

        ConsoleWriter.writeLoadingEnd("event register loaded");

    }

    private void _register(Listener listener){ //called from _registerEvents (required for all plugin functions!! do not change!!!)
        manager.getPluginManager().registerEvents(listener,this);
    }

}
