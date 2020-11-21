package de.dragonhard.lobby.manager;

import de.dragonhard.lobby.reader.GlobalWarpReader;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.io.File;

public class GlobalWarpManager extends GlobalWarpReader {
           private static String itemID = "";
    public void teleportPlayer(Player p, String warpName){  //call to teleport the Player to a Warp
        if(this.exists( warpName)){
            this.setFile( warpName);
            World w = Bukkit.getServer().getWorld(this.getString("World"));
            try{ //this.getfloat("Pitch"), this.getfloat("Yaw")
                p.teleport(new Location(w, this.getDouble("X"), this.getDouble("Y"), this.getDouble("Z"), this.getfloat("Yaw"), this.getfloat("Pitch")));
                p.sendMessage("§aDu wurdest zum Warp §e" + warpName + " §aTeleportiert");
            }catch(Exception ex) {
                p.sendMessage("§4Es ist ein Fehler aufgetreten Teleport nicht möglich");
                p.sendMessage("Fehler: " + ex.getStackTrace());
            }
        }else{
            p.sendMessage("§4Der Warp §e" + warpName + " §4wurde nicht gefunden!");
        }

    }

    public void setID(String id){itemID = id;}
    public String getID(){
        return itemID;
    }

    public void createWarp(Player p, String warpName, World world){    //call to create a new Warp

        File f = this.getCurrentFile( warpName);

        if(f.exists()){
            p.sendMessage("§4Der Warp §e"+ warpName +" §4wurde bereits erstellt!");
        }else{

            this.setFile( warpName);
            this.set("World", world.getName());
            this.set("X", p.getLocation().getX());
            this.set("Y", p.getLocation().getY());
            this.set("Z", p.getLocation().getZ());
            this.set("Pitch", p.getLocation().getPitch());
            this.set("Yaw", p.getLocation().getYaw());

            p.sendMessage("§aDer Warp §e"+ warpName +" §awurde erfolgreich erstellt!");
        }


    }

    public void delWarp(Player p, String warpName){//call to delete a existing Warp
        if(this.exists(warpName)){
            remove( this.getFile(warpName));
            if(!exists(warpName)){ p.sendMessage("§aDer Warp §e" + warpName + " §awurde erfolgreich entfernt!");}
        }else{
            p.sendMessage("§4Der Warp §e" + warpName + " §4wurde nicht gefunden!");
        }

    }


    private boolean remove( File warpName)  {

        try{
            File file = warpName;
            file.delete(); return true;
        }catch(Exception ex){
            return false;
        }

    }

}
