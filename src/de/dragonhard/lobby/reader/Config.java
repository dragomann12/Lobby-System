package de.dragonhard.lobby.reader;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Config {
    private static final Reader config = new Reader("Config","lobby"),
                                database = new Reader("Database", "db");

    private final static Location spawn = new Location(
            Bukkit.getWorld(database.getString("Spawn.World")),
            database.getDouble("X"),
            database.getDouble("Y"),
            database.getDouble("Z"),
            database.getFloat("Yaw"),
            database.getFloat("Pitch")
    );

    public static Reader getDatabase(){
        return database;
    }

    public static Location getSpawnLocation(){

        Location location = new Location(
                Bukkit.getWorld(database.getString("Spawn.World")),
                database.getDouble("Spawn.X"),
                database.getDouble("Spawn.Y"),
                database.getDouble("Spawn.Z"),
                database.getFloat("Spawn.Yaw"),
                database.getFloat("Spawn.Pitch")
        );

        return location;
    }

    public static Location getWarpLocation(Player p, String warpName){

        Location location = new Location(
                Bukkit.getWorld(database.getString("Database.Players." + p.getUniqueId() + ".Warps." + warpName + ".World")),
                database.getDouble("Database.Players." + p.getUniqueId() + ".Warps." + warpName + ".X"),
                database.getDouble("Database.Players." + p.getUniqueId() + ".Warps." + warpName + ".Y"),
                database.getDouble("Database.Players." + p.getUniqueId() + ".Warps." + warpName + ".Z"),
                database.getFloat("Database.Players." + p.getUniqueId() + ".Warps." + warpName + ".Yaw"),
                database.getFloat("Database.Players." + p.getUniqueId() + ".Warps." + warpName + ".Pitch")
        );

        return location;

    }


}
