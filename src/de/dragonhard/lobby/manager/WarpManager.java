package de.dragonhard.lobby.manager;

import de.dragonhard.lobby.reader.WarpListReader;
import de.dragonhard.lobby.reader.WarpReader;
import de.dragonhard.lobby.components.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.io.File;

public class WarpManager extends WarpReader {

    PlayerConfigManager pm = new PlayerConfigManager();
    WarpListReader wlr = new WarpListReader();

    public void teleportPlayer(Player p, String warpName){  //call to teleport the Player to a Warp
        if(this.exists(p, warpName)){
            this.setFile(p, warpName);
            World w = Bukkit.getServer().getWorld(this.getString("World"));
            try{
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

    private void addWarpToList(Player p, String warpName){
        wlr.setFile(p);
        wlr.addToList("warp_" + pm.getCurrentWarps(p), warpName);
        ConsoleWriter.writeWithTag("The Player " + p.getName() + " with the uuid " + p.getUniqueId() + " created the Warp "+ warpName + " and the Warp has ben added to the list");
    }

    public void getWarpList(Player p){ //call for a list of all the warps from a player
        wlr.setFile(p);

        if(pm.getCurrentWarps(p) == 0 && pm.getMaxWarps(p) == 0){p.sendMessage("§4Du kannst keine Warps setzen!");return;}
        if(pm.getCurrentWarps(p) == 0 && pm.getMaxWarps(p) != 0){p.sendMessage("§4Du hast keine Warps gesetzt!");return;}

        p.sendMessage("§aListe der Warps    Verwendung: §e" + pm.getCurrentWarps(p) + "§a/§e" + pm.getMaxWarps(p));

        for(int i = 0; i < pm.getMaxWarps(p); i++) {
            ConsoleWriter.writeWithTag("[Debug] for loop work");
            int item = i +1;

                if(!exists(p,wlr.getString("warp_"+i))){wlr.set("warp_" + i, "frei");}else{
                    ConsoleWriter.writeWithTag("[Debug] check file exists");
                    p.sendMessage("§a| §e" + wlr.getString("warp_" + i));
                }



        }


    }

    public void createWarp(Player p, String warpName, World world){    //call to create a new Warp

        File f = this.getCurrentFile(p, warpName);

        if(f.exists()){
            p.sendMessage("§4Der Warp §e"+ warpName +" §4wurde bereits erstellt!");
        }else{

            this.setFile(p, warpName);
            this.set("World", world.getName());
            this.set("X", p.getLocation().getX());
            this.set("Y", p.getLocation().getY());
            this.set("Z", p.getLocation().getZ());
            this.set("Pitch", p.getLocation().getPitch());
            this.set("Yaw", p.getLocation().getYaw());

            p.sendMessage("§aDer Warp §e"+ warpName +" §awurde erfolgreich erstellt!"); addWarpToList(p, warpName);
        }


    }

    public void delWarp(Player p, String warpName){//call to delete a existing Warp
        if(this.exists(p, warpName)){
            remove(p, this.getFile(warpName));
            if(!exists(p, warpName)){ p.sendMessage("§aDer Warp §e" + warpName + " §awurde erfolgreich entfernt!");}
        }else{
            p.sendMessage("§4Der Warp §e" + warpName + " §4wurde nicht gefunden!");
        }

    }


    private boolean remove(Player p, File warpName)  {

        try{
            File file = warpName;
            file.delete(); return true;
        }catch(Exception ex){
            return false;
        }

    }

}
