package de.dragonhard.lobby.components.util;

import org.bukkit.Bukkit;

public class functions {

    public static void sendSpacer(int amount){

        for(int i = 1; i <= amount; i++){

            Bukkit.broadcastMessage(" ");

        }

    }

}
