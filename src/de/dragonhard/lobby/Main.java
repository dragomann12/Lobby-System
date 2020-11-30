package de.dragonhard.lobby;
import com.sun.xml.internal.bind.v2.TODO;
import de.dragonhard.lobby.commands.cmdblock;
import de.dragonhard.lobby.commands.teleport.cmdAutoWarp;
import de.dragonhard.lobby.commands.teleport.cmdGlobalWarp;
import de.dragonhard.lobby.commands.teleport.cmdSpawn;
import de.dragonhard.lobby.commands.teleport.cmdWarp;
import de.dragonhard.lobby.components.ConsoleWriter;
import de.dragonhard.lobby.components.events.*;
import de.dragonhard.lobby.components.menu.*;
import de.dragonhard.lobby.components.menu.admin.Admin_Menu;
import de.dragonhard.lobby.components.menu.admin.Admin_Server_Menu;
import de.dragonhard.lobby.components.menu.debug.debug_Menu;
import de.dragonhard.lobby.components.menu.shop.Shop_Menu;
import de.dragonhard.lobby.manager.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{

/*
TODO Bugfix (NULL Pointer) in the menu when click outside of th menu
TODO add MY SQL db later
 */

    ConfigManager cm = new ConfigManager();
    SoundManager sm = new SoundManager();
    PluginManager plm = Bukkit.getServer().getPluginManager();
    PluginComunicationManager pcm = new PluginComunicationManager();
    PluginWithlistManager pwm = new PluginWithlistManager();

   static Plugin plugin;

    public static Plugin getPlugin() {
        return plugin;
    }

    public void onEnable(){

        if(cm.configExists()){
            ConsoleWriter.writeWithTag("checking Config ...");

            if(!cm.isCurrentVersion()){
                ConsoleWriter.writeWithTag("old Config version detected! ...");
                if(cm.isUpdateReady()){
                    cm.updateConfig();
                }else{
                    ConsoleWriter.writeWithTag(" [Update] an update for the config is available! write (in-game) @update to Update now or restart the Server");
                    cm.setUpdateReady(true);
                }
            }

                ConsoleWriter.writeWithTag("Config is up to date ...");
                ConsoleWriter.writeWithTag("Enabled!");

                pwm.onLoad();

                sm.addSoundsToList();

        }else{
            ConsoleWriter.writeWithTag("installing ...");
            loadMenuConfig(); // load the Config for the Menu
            cm.getDefaultConfig();
        }

         plugin = this;

         registerOutgoingChannel();
         registerIngoingChannel();

         registerCommand(); // Register the Commands
         registerEvents(); // Register the Events

    }

    public boolean registerOutgoingChannel(){
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, BungeeCordManager.getChannel());
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, PluginComunicationManager.getChannelOut());
        return true;
    }

    public boolean registerIngoingChannel(){
        Bukkit.getMessenger().registerIncomingPluginChannel(this, PluginComunicationManager.getChannelIn(), pcm.getListener());
        return true;
    }

    public boolean loadMenuConfig(){

        loadConfig("creativMenu","BEACON",5);
        loadConfig("shopMenu","DIAMOND",5);
        loadConfig("AdminMenu","BEACON",5);
        loadConfig("SettingsMenu","DIAMOND",5);
        loadConfig("LobbyMenu","NETHER_STAR",5);
        loadConfig("GameMenu","DIAMOND",5);
        loadConfig("XP_shopMenu","DIAMOND",5);
        loadConfig("FriendMenu","DIAMOND",5);
        loadConfig("DebugMenu","DIAMOND",5);

        return true;
    }

    public boolean registerCommand(){

        this.getCommand("warp").setExecutor(new cmdWarp());
        this.getCommand("spawn").setExecutor(new cmdSpawn());
        this.getCommand("autowarp").setExecutor(new cmdAutoWarp());
        this.getCommand("InvWarp").setExecutor(new cmdGlobalWarp());
        this.getCommand("block").setExecutor(new cmdblock());
        //this.getCommand("cr").setExecutor(new cmdcreate());
        //this.getCommand("join").setExecutor(new cmdJoin());
        //this.getCommand("left").setExecutor(new cmdleft());

        return true;

    }



    public boolean loadConfig(String menuName, String material ,int lineAmount){
        ConfigManager cm = new ConfigManager();

        cm.setFile(menuName + "_menu_config");

        cm.setDefault("Title", menuName);
        cm.setDefault("Title_Color","5");

        cm.setDefault("Hotbar_Item_Title", menuName);
        cm.setDefault("Hotbar_Item_Title_Color","5");
        cm.setDefault("Hotbar_Item_Material", material);
        int i = 0;
        int items = lineAmount * 9;

        for (i=0; i< items +1; i++ ){

            cm.setDefault("Slot_"+i+"_Item_Title","");
            cm.setDefault("Slot_"+i+"_Item_Title_Color","");
            cm.setDefault("Slot_"+i+"_Item_Material","STAINED_GLASS_PANE");
            cm.setDefault("Slot_"+i+"_isEnabled",false);

        }

        return true;
    }

    public boolean registerEvents(){

        plm.registerEvents(new Interact_Event(),this);
        plm.registerEvents(new Join_Event(),this);
        plm.registerEvents(new Chat_Event(),this);
        plm.registerEvents(new Click_Event(),this);
        plm.registerEvents(new Drop_Event(),this);
        plm.registerEvents(new Drag_Event(),this);
        plm.registerEvents(new Build_Event(),this);
        plm.registerEvents(new Inventory_Click_Event(),this);
        plm.registerEvents(new Lobby_Menu(),this);
        plm.registerEvents(new Game_Menu(),this);
        plm.registerEvents(new Shop_Menu(),this);
        plm.registerEvents(new Admin_Menu(),this);
        plm.registerEvents(new Settings_Menu(),this);
        plm.registerEvents(new Admin_Server_Menu(),this);
        plm.registerEvents(new Event_Blocker(),this);
        plm.registerEvents(new Hide_Event(),this);
        plm.registerEvents(new debug_Menu(), this);
        //plm.registerEvents(new CommandListener(),this); work on the test server but not at the Dev-Server
        //                                                          Conflict with other Plugin

        return true;

    }

    public void onDisable(){
        ConsoleWriter.writeWithTag("Disabled!");
    }

}
