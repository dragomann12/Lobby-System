package de.dragonhard.lobby;
import de.dragonhard.lobby.commands.cmdClear;
import de.dragonhard.lobby.commands.cmdblock;
import de.dragonhard.lobby.commands.coins.cmdCoins;
import de.dragonhard.lobby.commands.network.cmdCreateServer;
import de.dragonhard.lobby.commands.other.cmdBuild;
import de.dragonhard.lobby.commands.teleport.cmdGlobalWarp;
import de.dragonhard.lobby.commands.teleport.cmdSpawn;
import de.dragonhard.lobby.commands.teleport.cmdWarp;
import de.dragonhard.lobby.components.ConsoleWriter;
import de.dragonhard.lobby.components.events.*;
import de.dragonhard.lobby.components.menu.*;
import de.dragonhard.lobby.components.menu.admin.Admin_External_Menu;
import de.dragonhard.lobby.components.menu.admin.Admin_Menu;
import de.dragonhard.lobby.components.menu.admin.Admin_Server_Menu;
import de.dragonhard.lobby.components.menu.debug.debug_Menu;
import de.dragonhard.lobby.components.menu.player.Player_Menu;
import de.dragonhard.lobby.components.menu.shop.Shop_Coin_Menu;
import de.dragonhard.lobby.components.menu.shop.Shop_Menu;
import de.dragonhard.lobby.components.util.PAPI_Support;
import de.dragonhard.lobby.manager.*;
import de.dragonhard.lobby.manager.database.ConnectionManager;
import de.dragonhard.lobby.manager.database.MySQLManager;
import de.dragonhard.lobby.manager.other.BungeeCordManager;
import de.dragonhard.lobby.manager.other.PluginComunicationManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

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
            ConsoleWriter.writeWithTag("checking Config ...");

            if(!manager.getConfigManager().isCurrentVersion()){
                ConsoleWriter.writeWithTag("old Config version detected! ...");
                if(manager.getConfigManager().isUpdateReady()){
                    manager.getConfigManager().updateConfig();
                }else{
                    ConsoleWriter.writeWithTag(" [Update] an update for the config is available! write (in-game) @update to Update now or restart the Server");
                    manager.getConfigManager().setUpdateReady(true);
                }
            }


            Bukkit.getConsoleSender().sendMessage("§aEine Verbindung mit der Datenbank wird hergestellt.");
            manager.getMySqlManager().connect("createTable", null);
                ConsoleWriter.writeWithTag("Config is up to date ...");

                registerOutgoingChannel();
                registerIngoingChannel();

                setWallIdLists();

                registerCommand(); // Register the Commands
                registerEvents(); // Register the Events

                ConsoleWriter.writeWithTag("Enabled!");

                manager.getPluginWhitelistManager().onLoad();
                manager.getSoundManager().addSoundsToList();

            if (manager.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                PAPI_Support pS = new PAPI_Support(this);
                pS.register();
            }

        }else{
            ConsoleWriter.writeWithTag("installing ...");
            loadMenuConfig(); // load the Config for the Menu
            manager.getConfigManager().getDefaultConfig();
        }

        //loading debug operations
        loadDebug();

    }

    private boolean loadDebug(){
        if(manager.getConfigManager().isDebugMode()){

            manager.getShopManager().addItemToShop("Coins-Shop","Test","this is a Test item!","Debug","Test","non",22,9999);
            return true;
        }

        return false;
    }

    public boolean setWallIdLists(){
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
        //Creative-Menu
        manager.getMenuManager().getCreativeMenu().addWallIDs();
        //Settings-Menu
        manager.getMenuManager().getSettingsMenu().addWallIDs();
        //Friend-Menu
        manager.getMenuManager().getFriendMenu().addWallIDs();
        //Debug-Menu
        manager.getMenuManager().getDebugMenu().addWallIDs();
        //Player-Menu
        manager.getMenuManager().getPlayerMenu().addWallIDs();

        ConsoleWriter.writeWithTag("done");
        return true;
    }

    public boolean registerOutgoingChannel(){
        ConsoleWriter.writeWithTag("setting up outgoing communication channel ...");

        Bukkit.getMessenger().registerOutgoingPluginChannel(this, BungeeCordManager.getChannel());
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, PluginComunicationManager.getChannelOut());
        ConsoleWriter.writeWithTag("done");
        return true;
    }

    public boolean registerIngoingChannel(){
        ConsoleWriter.writeWithTag("setting up ingoing communication channel ...");
        Bukkit.getMessenger().registerIncomingPluginChannel(this, PluginComunicationManager.getChannelIn(), manager.getCommunicationManager().getListener());
        ConsoleWriter.writeWithTag("done");
        return true;
    }

    public boolean loadMenuConfig(){
        ConsoleWriter.writeWithTag("loading menu configuration");

        loadConfig("creativ","BEACON",5);
        loadConfig("shop","DIAMOND",5);
        loadConfig("Admin","BEACON",5);
        loadConfig("Settings","DIAMOND",5);
        loadConfig("Lobby","NETHER_STAR",5);
        loadConfig("Game","DIAMOND",5);
        loadConfig("Coin_shop","DIAMOND",5);
        loadConfig("Friend","DIAMOND",5);
        loadConfig("Debug","DIAMOND",5);
        loadConfig("Admin_External","DIAMOND",5);
        loadConfig("Player","DIAMOND",5);

        ConsoleWriter.writeWithTag("menu configuration loaded");
        return true;
    }

    public boolean registerCommand(){
        ConsoleWriter.writeWithTag("loading command register");

        this.getCommand("warp").setExecutor(new cmdWarp());
        this.getCommand("spawn").setExecutor(new cmdSpawn());
        this.getCommand("InvWarp").setExecutor(new cmdGlobalWarp());
        this.getCommand("block").setExecutor(new cmdblock());
        this.getCommand("coins").setExecutor(new cmdCoins());
        this.getCommand("chclear").setExecutor(new cmdClear());
        this.getCommand("crServer").setExecutor(new cmdCreateServer());
        this.getCommand("bm").setExecutor(new cmdBuild());

        ConsoleWriter.writeWithTag("command register loaded");
        return true;

    }

    public boolean loadConfig(String menuName, String material ,int lineAmount){
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

        return true;
    }

    public boolean registerEvents(){
        ConsoleWriter.writeWithTag("loading event register");

        manager.getPluginManager().registerEvents(manager.getEventManager().getInteractEvent(),this);
        manager.getPluginManager().registerEvents(manager.getEventManager().getJoinEvent(),this);
        manager.getPluginManager().registerEvents(manager.getEventManager().getChatEvent(),this);
        manager.getPluginManager().registerEvents(manager.getEventManager().getClickEvent(),this);
        manager.getPluginManager().registerEvents(manager.getEventManager().getDropEvent(),this);
        manager.getPluginManager().registerEvents(manager.getEventManager().getDragEvent(),this);
        manager.getPluginManager().registerEvents(manager.getEventManager().getBuildEvent(),this);
        manager.getPluginManager().registerEvents(manager.getEventManager().getEventBlocker(),this);
        manager.getPluginManager().registerEvents(manager.getEventManager().getHideEvent(),this);
        manager.getPluginManager().registerEvents(manager.getEventManager().getDisconnectEvent(),this);
        manager.getPluginManager().registerEvents(manager.getEventManager().getDamageEvent(),this);
        manager.getPluginManager().registerEvents(manager.getEventManager().getHungerEvent(),this);
        manager.getPluginManager().registerEvents(manager.getEventManager().getHealthEvent(),this);
        manager.getPluginManager().registerEvents(new ConnectionManager(),this);

        manager.getPluginManager().registerEvents(new Lobby_Menu(),this);
        manager.getPluginManager().registerEvents(new Game_Menu(),this);
        manager.getPluginManager().registerEvents(new Shop_Menu(),this);
        manager.getPluginManager().registerEvents(new Admin_Menu(),this);
        manager.getPluginManager().registerEvents(new Settings_Menu(),this);
        manager.getPluginManager().registerEvents(new Admin_Server_Menu(),this);
        manager.getPluginManager().registerEvents(new debug_Menu(),this);
        manager.getPluginManager().registerEvents(new Admin_External_Menu(),this);
        manager.getPluginManager().registerEvents(new Player_Menu(),this);
        manager.getPluginManager().registerEvents(new Shop_Coin_Menu(),this);

        ConsoleWriter.writeWithTag("event register loaded");
        return true;

    }

    public void onDisable(){
        ConsoleWriter.writeWithTag("Disabled!");
    }

}
