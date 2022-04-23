package de.dragonhard.lobby.components;

import org.bukkit.Bukkit;

public class ConsoleWriter {

    public static void write(String text){
        Bukkit.getConsoleSender().sendMessage(text);
    }
    public static void writeWithTag(String text){
        Bukkit.getConsoleSender().sendMessage("§b[§eLobbySystem§b]"+ " §5" + text);
    }

    public static void writeLoadingStart(String text){
        Bukkit.getConsoleSender().sendMessage("§b[§eLobbySystem§b]"+ " §e" + text);
    }

    public static void writeLoadingEnd(String text){
        Bukkit.getConsoleSender().sendMessage("§b[§eLobbySystem§b]"+ " §a" + text);
    }

    public static void writeDebug(String text){
        Bukkit.getConsoleSender().sendMessage("§b[§eLobbySystem§b]" + " §b[§eDebug§b] §f" + text);
    }
    public static void writeErrorWithTag(String text){
        Bukkit.getConsoleSender().sendMessage("§b[§eLobbySystem§b]" + " §e[§4Error§e] §4" + text);
    }
}
