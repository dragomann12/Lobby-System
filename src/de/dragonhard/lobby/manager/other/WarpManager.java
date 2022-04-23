package de.dragonhard.lobby.manager.other;

import de.dragonhard.lobby.manager.Managers;
import de.dragonhard.lobby.reader.Config;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class WarpManager extends Managers {


    public void teleportPlayer(Player p, String warpName){

            try{
                p.teleport(Config.getWarpLocation(p, warpName));
                p.sendMessage("§aDu wurdest zum Warp §e" + warpName + " §aTeleportiert");
            }catch(Exception ex) {
                p.sendMessage("§4Es ist ein Fehler aufgetreten Teleport nicht möglich");
            }

    }


    public void createWarp(Player p, String warpName, World world){

        Config.getDatabase().set("Database.Players." + p.getUniqueId() + ".Warps." + warpName + ".World", world.getName());
        Config.getDatabase().set("Database.Players." + p.getUniqueId() + ".Warps." + warpName + ".X", p.getLocation().getX());
        Config.getDatabase().set("Database.Players." + p.getUniqueId() + ".Warps." + warpName + ".Y", p.getLocation().getY());
        Config.getDatabase().set("Database.Players." + p.getUniqueId() + ".Warps." + warpName + ".Z", p.getLocation().getZ());
        Config.getDatabase().set("Database.Players." + p.getUniqueId() + ".Warps." + warpName + ".Pitch", p.getLocation().getPitch());
        Config.getDatabase().set("Database.Players." + p.getUniqueId() + ".Warps." + warpName + ".Yaw", p.getLocation().getYaw());

        p.sendMessage("§aDer Warp §e"+ warpName +" §awurde erfolgreich erstellt!");

    }
}
