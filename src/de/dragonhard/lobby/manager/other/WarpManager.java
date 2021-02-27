package de.dragonhard.lobby.manager.other;

import de.dragonhard.lobby.manager.Managers;
import de.dragonhard.lobby.reader.WarpListReader;
import de.dragonhard.lobby.reader.WarpReader;
import de.dragonhard.lobby.components.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.Arrays;

public class WarpManager extends Managers {


    WarpReader warpReader = this.getReaderManager().getWarpReader();
    WarpListReader warpListReader = this.getReaderManager().getWarpListReader();

    public void teleportPlayer(Player p, String warpName){  //call to teleport the Player to a Warp
        if(warpReader.exists(p, warpName)){
            warpReader.setFile(p, warpName);
            World w = Bukkit.getServer().getWorld(warpReader.getString("World"));
            try{
                p.teleport(new Location(w, warpReader.getDouble("X"), warpReader.getDouble("Y"), warpReader.getDouble("Z"), warpReader.getfloat("Yaw"), warpReader.getfloat("Pitch")));
                p.sendMessage("§aDu wurdest zum Warp §e" + warpName + " §aTeleportiert");
            }catch(Exception ex) {
                p.sendMessage("§4Es ist ein Fehler aufgetreten Teleport nicht möglich");
                p.sendMessage("Fehler: " + Arrays.toString(ex.getStackTrace()));
            }
        }else{
            p.sendMessage("§4Der Warp §e" + warpName + " §4wurde nicht gefunden!");
        }

    }

    private void addWarpToList(Player p, String warpName){
        warpListReader.setFile(p);
        warpListReader.addToList("warp_" + this.getPlayerManager().getCurrentWarps(p), warpName);
        ConsoleWriter.writeWithTag("The Player " + p.getName() + " with the uuid " + p.getUniqueId() + " created the Warp "+ warpName + " and the Warp has ben added to the list");
    }

    public void delAllWarps(Player p){
        warpListReader.setFile(p);
        if(this.getPlayerManager().getCurrentWarps(p) == 0 && this.getPlayerManager().getMaxWarps(p) != 0){p.sendMessage("§4Du hast keine Warps gesetzt!");return;}

        for(int i = 0; i < this.getPlayerManager().getMaxWarps(p); i++) {

            if(!warpReader.exists(p,warpListReader.getString("warp_"+i))){warpListReader.set("warp_" + i, "frei");}else{
                this.delWarp(p, warpListReader.getString("warp_" + i));
                this.getPlayerManager().delWarpFromCount(p);
            }

        }

    }

    public void getWarpList(Player p){ //call for a list of all the warps from a player
        warpListReader.setFile(p);

        if(this.getPlayerManager().getCurrentWarps(p) == 0 && this.getPlayerManager().getMaxWarps(p) == 0){p.sendMessage("§4Du kannst keine Warps setzen!");return;}
        if(this.getPlayerManager().getCurrentWarps(p) == 0 && this.getPlayerManager().getMaxWarps(p) != 0){p.sendMessage("§4Du hast keine Warps gesetzt!");return;}

        p.sendMessage("§aListe der Warps    Verwendung: §e" + this.getPlayerManager().getCurrentWarps(p) + "§a/§e" + this.getPlayerManager().getMaxWarps(p));

        for(int i = 0; i < this.getPlayerManager().getMaxWarps(p); i++) {

                if(!warpListReader.exists(p,warpListReader.getString("warp_"+i))){warpListReader.set("warp_" + i, "frei");}else{
                    p.sendMessage("§a| §e" + warpListReader.getString("warp_" + i));
                }



        }


    }

    public void createWarp(Player p, String warpName, World world){    //call to create a new Warp

        File f = warpReader.getCurrentFile(p, warpName);

        if(f.exists()){
            p.sendMessage("§4Der Warp §e"+ warpName +" §4wurde bereits erstellt!");
        }else{

            warpReader.setFile(p, warpName);
            warpReader.set("World", world.getName());
            warpReader.set("X", p.getLocation().getX());
            warpReader.set("Y", p.getLocation().getY());
            warpReader.set("Z", p.getLocation().getZ());
            warpReader.set("Pitch", p.getLocation().getPitch());
            warpReader.set("Yaw", p.getLocation().getYaw());

            p.sendMessage("§aDer Warp §e"+ warpName +" §awurde erfolgreich erstellt!"); addWarpToList(p, warpName);
        }


    }

    public void delWarp(Player p, String warpName){//call to delete a existing Warp
        if(warpReader.exists(p, warpName)){
            remove(p, warpReader.getFile(warpName));
            if(!warpReader.exists(p, warpName)){ p.sendMessage("§aDer Warp §e" + warpName + " §awurde erfolgreich entfernt!");}
        }else{
            p.sendMessage("§4Der Warp §e" + warpName + " §4wurde nicht gefunden!");
        }

    }


    private boolean remove(Player p, File warpName)  {

        try{
            warpName.delete(); return true;
        }catch(Exception ex){
            return false;
        }

    }

}
