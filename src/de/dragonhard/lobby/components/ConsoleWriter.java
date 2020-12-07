package de.dragonhard.lobby.components;

public class ConsoleWriter {

    private static String tag = "[LobbySystem]";

    public static void write(String text){ System.out.println(text);}
    public static void writeWithTag(String text){ System.out.println(tag + " " + text);}
    public static void writeDebug(String text){ System.out.println("[Debug] " + text);}
}
