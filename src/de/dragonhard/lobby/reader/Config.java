package de.dragonhard.lobby.reader;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Config {

   private static Reader reader = new Reader("Config", "Lobby"),
                         database = new Reader("Database", "db");


   public final static Location spawn = new Location(

           Bukkit.getWorld(database.getString("Spawn.World")),
           database.getDouble("Spawn.X"),
           database.getDouble("Spawn.Y"),
           database.getDouble("Spawn.Z"),
           database.getFloat("Spawn.Yaw"),
           database.getFloat("Spawn.Pitch")

   );


   public final static Location getLocation(Player p, String warpName){

      Location warp = new Location(

              Bukkit.getWorld(database.getString("Players." + p.getUniqueId() + ".Warps." + warpName + ".World")),
              database.getDouble("Players." + p.getUniqueId() + ".Warps." + warpName + ".X"),
              database.getDouble("Players." + p.getUniqueId() + ".Warps." + warpName + ".Y"),
              database.getDouble("Players." + p.getUniqueId() + ".Warps." + warpName + ".Z"),
              database.getFloat("Players." + p.getUniqueId() + ".Warps." + warpName + ".Yaw"),
              database.getFloat("Players." + p.getUniqueId() + ".Warps." + warpName + ".Pitch")

      );

      return warp;
   }

   public static Reader getDatabase(){
       return database;
   }


}
