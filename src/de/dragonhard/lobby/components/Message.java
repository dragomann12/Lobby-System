package de.dragonhard.lobby.components;

import org.bukkit.entity.Player;

public class Message {

    public static void sendMessageByTag(String tag, Player p){

        p.sendMessage(tag);

    }

    public static String getMessageByTag(String tag) {

        switch (tag) {

            case "tpSpawn":
                return "§aDu wurdest zum Spawn Teleportiert";
            case "tpWarp":
                return "§aDu wurdest zum Warp Teleportiert";
            case "tpWarpNotExists":
                return "§4Der Warp wurde nicht gefunden!";
            case "tpWarpNoPermission":
                return "§4Du hast keine Berechtigung um diesen Warp zu nutzen!";
            case "tpWarpDelete":
                return "§aDer Warp wurde erfolgreich gelöscht";
        }

        return "§4keine oder falsche eingabe!";

    }

}
