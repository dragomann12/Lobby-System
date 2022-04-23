package de.dragonhard.lobby.manager.other;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.File;
@Deprecated
public class GroupManager extends GroupReader {

    public void createGroup(Player p, String groupName, String groupColor, int maxPlayer, String password){
        if(!this.exists(groupName)){
            this.setFile(groupName);
            this.set("GroupLeader", p.getName());
            this.set("NameColor", groupColor);
            this.set("MaxPlayer", maxPlayer);
            this.set("Password", password);
            this.set("usePassword", true);
            this.set("PlayerCount", 0);
            this.set("BannedPlayerCount", 0);
            join(p);
        }else {
            p.sendMessage("§4Der Name ist vergeben!");
        }
    }

    public void createGroup(Player p, String groupName, String groupColor, int maxPlayer){
        if(!this.exists(groupName)){
            this.setFile(groupName);
            this.set("GroupLeader", p.getName());
            this.set("NameColor", groupColor);
            this.set("MaxPlayer", maxPlayer);
            this.set("usePassword", false);
            this.set("PlayerCount", 0);
            this.set("BannedPlayerCount", 0);
            join(p);
        }else {
            p.sendMessage("§4Der Name ist vergeben!");
        }
    }

    private boolean hasPassword(){
        return this.getBoolean("usePassword");
    }

    private void join(Player p){
        p.sendMessage("§aDu bist der Gruppe beigetreten");
        this.set("PlayerCount", this.getInteger("PlayerCount") + 1);
        this.set("MemberID_"+this.getInteger("PlayerCount"), p.getName());
    }

    public boolean groupHasPlayer(String playerName, String groupName){

        Player tp = Bukkit.getPlayer(playerName);
        if(this.exists(groupName)) {
            this.setFile(groupName);
            int i = 0;
            for(i = 0; i == this.getPlayerCount(groupName); i++){
               String player = this.getString("MemberID_" + i );
               if(tp.getName() == player){return true;}
            }
        }


        return false;
    }

    public boolean isBanned(String playerName, String groupName){

        Player tp = Bukkit.getPlayer(playerName);
        if(this.exists(groupName)) {
            this.setFile(groupName);
            int i = 0;
            for(i = 0; i == this.getInteger("BannedPlayerCount"); i++){
                String player = this.getString("BannedID_" + i );
                if(tp.getName() == player){return true;}
            }
        }


        return false;
    }

    private int getPlayerCount(String groupName){

        this.setFile(groupName);

        return this.getInteger("PlayerCount");

    }

    private boolean isLeader(Player p, String groupName){
        this.setFile(groupName);

        if(this.getString("GroupLeader") == p.getName()){
         return true;
        }

        return false;
    }

    public void Ban(Player p){
        p.sendMessage("§4Du wurdest von der Gruppe ausgeschlossen");
        this.set("PlayerCount", this.getInteger("PlayerCount") - 1);
        this.set("BannedPlayerCount", this.getInteger("BannedPlayerCount") + 1);
        this.set("MemberID_"+this.getInteger("PlayerCount"), "");
        this.set("BannedID_"+this.getInteger("BannedPlayerCount"), p.getName());
    }

    public void groupLeft(Player p, String groupName){

        if(this.exists(groupName)){
            this.setFile(groupName);

            if(groupHasPlayer(p.getName(), groupName)){

                if(isLeader(p,groupName)){
                    File file = this.getFile(groupName);
                    file.delete();
                    p.sendMessage("§4Du hast die Gruppe verlassen");
                    p.sendMessage("§4Die Gruppe wurde gelöscht!");
                }else{
                    p.sendMessage("§4Du hast die Gruppe verlassen");
                    this.set("PlayerCount", this.getInteger("PlayerCount") - 1);
                    this.set("MemberID_"+this.getInteger("PlayerCount"), "");
                }

            }else{
                p.sendMessage("§4Du bist kein teil dieser Gruppe!");
            }

        }else{
            p.sendMessage("§4Diese Gruppe wurde nicht gefunden!");
        }

    }

    public void groupJoin(Player p,String groupName, String password){

        if(this.exists(groupName)){
            this.setFile(groupName);

            if(this.getBoolean("isPrivate")){

                if(this.getString("GroupLeader") == p.getName()){

                        join(p);

                }else{
                    if(hasPassword() && password ==this.getString("Password")){
                        if(isBanned(p.getName(), groupName)){
                            p.sendMessage("§4Das beitreten ist nicht möglich");
                            p.sendMessage("§4Du wurdest von der Gruppe ausgeschlossen");

                        } else {
                            join(p);
                        }

                    }else if(hasPassword() && password !=this.getString("Password")){
                      p.sendMessage("§4Das Password ist falsch!");
                    }else{
                      p.sendMessage("§4Es ist ein Fehler aufgetreten!");
                    }
                }

            }else{
                join(p);
            }

        }else{
            p.sendMessage("§4Diese Gruppe wurde nicht gefunden!");
        }

    }

}
