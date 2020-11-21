package de.dragonhard.lobby.manager;

import de.dragonhard.lobby.components.ConsoleWriter;
import de.dragonhard.lobby.reader.PluginConfigReader;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.io.File;
import java.io.IOException;

public class ConfigManager extends PluginConfigReader {

    private static String defaultConfigFile = "config_main";
    private static String configVersion = "1.1";

    public void createConfig(String fileName , String item, String value){
        this.setFile(fileName);
        this.set(item, value);
    }

    public boolean slotIsEnabled(String menuName, int slotID){
        String file = menuName + "_menu_config";
        String slot = "Slot_" + slotID + "_isEnabled";

        if(this.getBooleanOfItem(file, slot)){
            return true;
        }
        return false;
    }

    public String getSlotTitle(String menuName, int slotID){
        String file = menuName + "_menu_config";
        String slot = "Slot_" + slotID + "_Item_Title";
        return this.getStringOfItem(file,slot);
    }

    public void setSlotTitle(String menuName, String title, int slotID){
        String file = menuName + "_menu_config";
        String slot = "Slot_" + slotID + "_Item_Title";

        this.setFile(file);
        this.set(slot,title);

    }

    public String getSlotMaterial(String menuName, int slotID){
        String file = menuName + "_menu_config";
        String slot = "Slot_" + slotID + "_Item_Material";
        return this.getStringOfItem(file,slot);
    }

    public void setSlotMaterial(String menuName, String material, int slotID){
        String file = menuName + "_menu_config";
        String slot = "Slot_" + slotID + "_Item_Material";

        this.setFile(file);
        this.set(slot,material);

    }

    public String getSlotTitleColor(String menuName, int slotID){
        String file = menuName + "_menu_config";
        String slot = "Slot_" + slotID + "_Item_Title_Color";
        return this.getStringOfItem(file,slot);
    }

    public void setSlotTitleColor(String menuName, String colorTag, int slotID){
        String file = menuName + "_menu_config";
        String slot = "Slot_" + slotID + "_Item_Title_Color";

        this.setFile(file);
        this.set(slot,colorTag);

    }

    public String getHotbarTitle(String menuName){
        String file = menuName + "_menu_config";
        String slot = "Hotbar_Item_Title";
        return this.getStringOfItem(file,slot);
    }

    public void setHotbarTitle(String menuName, String title){
        String file = menuName + "_menu_config";
        String slot = "Hotbar_Item_Title";

        this.setFile(file);
        this.set(slot,title);

    }

    public String getHotbarTitleColor(String menuName){
        String file = menuName + "_menu_config";
        String slot = "Hotbar_Item_Title_Color";
        return this.getStringOfItem(file,slot);
    }

    public void setHotbarTitleColor(String menuName, String colorTag){
        String file = menuName + "_menu_config";
        String slot = "Hotbar_Item_Title_Color";

        this.setFile(file);
        this.set(slot,colorTag);

    }

    public String getHotbarMaterial(String menuName){
        String file = menuName + "_menu_config";
        String slot = "Hotbar_Item_Title";
        return this.getStringOfItem(file,slot);
    }

    public void setHotbarMaterial(String menuName, String material){
        String file = menuName + "_menu_config";
        String slot = "Hotbar_Item_Material";

        this.setFile(file);
        this.set(slot,material);

    }

    public String getMenuTitle(String menuName){
        String file = menuName + "_menu_config";
        String slot = "Title";
        return this.getStringOfItem(file,slot);
    }

    public void setMenuTitle(String menuName, String title){
        String file = menuName + "_menu_config";
        String slot = "Title";

        this.setFile(file);
        this.set(slot,title);

    }

    public String getMenuTitleColor(String menuName){
        String file = menuName + "_menu_config";
        String slot = "Title_Color";
        return this.getStringOfItem(file,slot);
    }

    public void setMenuTitleColor(String menuName, String colorTag){
        String file = menuName + "_menu_config";
        String slot = "Title_Color";

        this.setFile(file);
        this.set(slot,colorTag);

    }

    public void createConfig(String fileName , String item, int value){
        this.setFile(fileName);
        this.set(item, value);
    }

    public void createConfig(String fileName , String item, float value){
        this.setFile(fileName);
        this.set(item, value);
    }

    public void createConfig(String fileName , String item, double value){
        this.setFile(fileName);
        this.set(item, value);
    }

    public void createConfig(String fileName , String item, boolean value){
        this.setFile(fileName);
        this.set(item, value);
    }

    public String getStringOfItem(String fileName, String item){
        this.setFile(fileName);
        return this.getString(item);
    }

    public int getIntegerOfItem(String fileName, String item){
        this.setFile(fileName);
        return this.getInteger(item);
    }

    public float getFloatOfItem(String fileName, String item){
        this.setFile(fileName);
        return this.getFloat(item);
    }

    public double getDoubleOfItem(String fileName, String item){
        this.setFile(fileName);
        return this.getDouble(item);
    }

    public boolean getBooleanOfItem(String fileName, String item){
        this.setFile(fileName);
        return this.getBoolean(item);
    }

    public String getDefaultConfigName(){
        return defaultConfigFile;
    }

    public void setDefaultConfigFile(String configName){
        defaultConfigFile = configName;
    }

    public void getDefaultConfig(){             // get called from the Main
        ConsoleWriter.writeWithTag("start creating config ...");
        try{
            this.setFile(getDefaultConfigName());

            this.setDefault("UseTag", true);
            this.setDefault("TagStart", "[");
            this.setDefault("Tag","Lobby");
            this.setDefault("TagEnd", "] ");
            this.setDefault("StartTagColor", "5");
            this.setDefault("TagColor", "a");
            this.setDefault("EndTagColor", "5");

            this.setDefault("DBVersion",1);
            this.setDefault("ConfigVersion","1.1");
            this.setDefault("showWelcome",true);
            this.setDefault("MessageColor","b");
            this.setDefault("StartColor","b");
            this.setDefault("MessageStart","Hey");
            this.setDefault("NameColor", "a");
            this.setDefault("WelcomeMessage","willkommen auf dem Server");
            this.setDefault("MenuWallColor",15);
            this.setDefault("MenuInsideColor",8);
            this.setDefault("MessagePrefix","@");
        }catch (Exception e){
            ConsoleWriter.writeWithTag("An error occurred while creating the config");
            ConsoleWriter.write("Error: " + e.getCause());
        }


        ConsoleWriter.writeWithTag("config now created!");
        ConsoleWriter.writeWithTag("restart required stopping Server!");
        System.exit(0);
    }

    public String getConfigVersion(){
        this.setFile(getDefaultConfigName());
        return this.getString("ConfigVersion");
    }

    public boolean isCurrentVersion(){
        this.setFile(getDefaultConfigName());
        if(getConfigVersion() == configVersion){ return true;}
        return false;
    }

    public void updateConfig(){
        File file = this.getFile(getDefaultConfigName());
        ConsoleWriter.writeWithTag("remove old config ... 0%");
        file.delete();
        ConsoleWriter.writeWithTag("done ... 100%");
        getDefaultConfig();

    }

    public boolean configExists(){

        File file = this.getFile(getDefaultConfigName());

        if(file.exists()){return true;}
        return false;
    }

    public int getWallColor(){
        this.setFile(getDefaultConfigName());
        return this.getInteger("MenuWallColor");
    }

    public int getInsideColor(){
        this.setFile(getDefaultConfigName());
        return this.getInteger("MenuInsideColor");
    }

    public String getMessagePrefix(){
        this.setFile(getDefaultConfigName());
        return this.getString("MessagePrefix");

    }

    public String getStartColor(){
        this.setFile(getDefaultConfigName());
        return this.getString("StartColor");
    }

    public String getMessageStart(){
        this.setFile(getDefaultConfigName());
        return this.getString("MessageStart");
    }

    public boolean showWelcome(){
        this.setFile(getDefaultConfigName());
        return this.getBoolean("showWelcome");
    }

    public String getNameColor(){
        this.setFile(getDefaultConfigName());
        return this.getString("NameColor");
    }

    public String getMessageColor(){
        this.setFile(getDefaultConfigName());
        return this.getString("MessageColor");
    }

    public String getWelcomeMessage(){
        this.setFile(getDefaultConfigName());
        return this.getString("WelcomeMessage");
    }

    public int getDBVersion(){
        this.setFile(getDefaultConfigName());
        return this.getInteger("DBVersion");
    }

    public boolean tagUseEnabled(){
        this.setFile(getDefaultConfigName());
        return this.getBoolean("UseTag");
    }

    public String getTag(){
        this.setFile(getDefaultConfigName());
        return "§" + this.getString("StartTagColor") + this.getString("TagStart") + "§" + this.getString("TagColor") + this.getString("Tag") + "§" + this.getString("EndTagColor") + this.getString("TagEnd");
    }

}
