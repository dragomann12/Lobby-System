package de.dragonhard.lobby.manager.other;

import de.dragonhard.lobby.reader.Config;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.Arrays;

public class SpawnManager {

    public void teleportPlayerToSpawn(Player p){

            try{
                p.teleport(Config.getSpawnLocation());
            }catch(Exception ex) {
                p.sendMessage("§4Es ist ein Fehler aufgetreten Teleport nicht möglich");
                p.sendMessage("Fehler: " + Arrays.toString(ex.getStackTrace()));
            }

    }

    public void createSpawn(Player p, World world){    //call to create a new Spawn

        Config.getDatabase().set("Spawn.World", world.getName());
        Config.getDatabase().set("Spawn.X", p.getLocation().getX());
        Config.getDatabase().set("Spawn.Y", p.getLocation().getY());
        Config.getDatabase().set("Spawn.Z", p.getLocation().getZ());
        Config.getDatabase().set("Spawn.Pitch", p.getLocation().getPitch());
        Config.getDatabase().set("Spawn.Yaw", p.getLocation().getYaw());
        p.sendMessage("§aDer Spawn wurde erfolgreich erstellt!");

    }
}
