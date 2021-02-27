package de.dragonhard.lobby.manager.other;

import de.dragonhard.lobby.reader.SpawnReader;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.Arrays;

public class SpawnManager extends SpawnReader {

    public void teleportPlayerToSpawn(Player p){  //call to teleport the Player to a Spawn
        if(exists(this.getFile("Spawn"))){
            this.setFile(p,"Spawn");
            World w = Bukkit.getServer().getWorld(this.getString("World"));
            try{
                p.teleport(new Location(w, this.getDouble("X"), this.getDouble("Y"), this.getDouble("Z"), this.getfloat("Yaw"), this.getfloat("Pitch")));
            }catch(Exception ex) {
                p.sendMessage("§4Es ist ein Fehler aufgetreten Teleport nicht möglich");
                p.sendMessage("Fehler: " + Arrays.toString(ex.getStackTrace()));
            }
        }else{
            p.sendMessage("§4Der Spawn wurde nicht gefunden!");
        }

    }

    public void createSpawn(Player p, double x, double y, double z, float pitch, float yaw, World world){    //call to create a new Spawn
        if(!exists(this.getFile("Spawn"))){
            this.setFile(p,"Spawn");
            this.set("World", world.getName());
            this.set("X", p.getLocation().getX());
            this.set("Y", p.getLocation().getY());
            this.set("Z", p.getLocation().getZ());
            this.set("Pitch", p.getLocation().getPitch());
            this.set("Yaw", p.getLocation().getYaw());
            p.sendMessage("§aDer Spawn wurde erfolgreich erstellt!");
        }else{
            p.sendMessage("§4Der Spawn wurde bereits erstellt!");
        }

    }

    public void delSpawn(Player p){
        if(exists(this.getFile("Spawn"))){
            remove(p, this.getFile("Spawn"));
            if(!exists(this.getFile("Spawn"))){ p.sendMessage("§aDer Spawn wurde erfolgreich entfernt!");}
        }else{
            p.sendMessage("§4Der Spawn wurde nicht gefunden!");
        }

    }

    private boolean remove(Player p, File spawnName)  {

        try{
            File file = spawnName;
            file.delete(); return true;
        }catch(Exception ex){
            return false;
        }

    }

    public boolean exists(File spawnName){
        File file = spawnName;
        if(file.exists()){return true;}
        return false;
    }

}
