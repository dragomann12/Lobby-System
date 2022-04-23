package de.dragonhard.lobby.manager.other;


import de.dragonhard.lobby.reader.Config;
import org.bukkit.World;
import org.bukkit.entity.Player;



public class SpawnManager {

    public void createSpawn(Player p, double x, double y, double z, float pitch, float yaw, World world){

        Config.getDatabase().set("Spawn.World", world.getName());
        Config.getDatabase().set("Spawn.X", p.getLocation().getX());
        Config.getDatabase().set("Spawn.Y", p.getLocation().getY());
        Config.getDatabase().set("Spawn.Z", p.getLocation().getZ());
        Config.getDatabase().set("Spawn.Pitch", p.getLocation().getPitch());
        Config.getDatabase().set("Spawn.Yaw", p.getLocation().getYaw());
        p.sendMessage("§aDer Spawn wurde erfolgreich erstellt!");

    }

}
