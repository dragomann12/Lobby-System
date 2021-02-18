package de.dragonhard.lobby.manager.other;

import de.dragonhard.lobby.components.ConsoleWriter;
import de.dragonhard.lobby.components.Message;
import de.dragonhard.lobby.components.PermissionList;
import de.dragonhard.lobby.components.util.InventorySetter;
import de.dragonhard.lobby.manager.Managers;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class CommandActionManager extends Managers {

    final String item_color = "§6";
    String item_value_color = "§e";
    final String default_color_Message = "§f";
    final String default_color_value = "§e";
    final String version = "1.5.0  beta-Build: 150.5";

    public void Action_showCoins(Player p){
        p.sendMessage("§5"+ p.getName() + " §bhat §a" + this.getConnectionManager().callRowCoins(p) + " CC");
    }

    public void Action_setCoins(Player p, Player tp, int value){
        this.getConnectionManager().setRowCoins(tp, value);
        if(p.getName().equals(tp.getName())) {
            tp.sendMessage("§bDu hast jetzt §5" + this.getConnectionManager().callRowCoins(tp) + "§bCC"); return;
        }
            p.sendMessage("§aCoins für §e" + tp.getName() + " §aerfolgreich geändert!");
            tp.sendMessage("§bDu hast jetzt §5" + this.getConnectionManager().callRowCoins(tp) + "§bCC");
    }

    public void Action_addCoins(Player p, Player tp, int value){
        this.getConnectionManager().setRowCoins(tp,this.getConnectionManager().callRowCoins(p) + value);
        if(p.getName().equals(tp.getName())){
            tp.sendMessage("§bDu hast jetzt §5" + this.getConnectionManager().callRowCoins(p) + "§bCC");return;
        }
            p.sendMessage("§bDu hast die Chaos-Coins des Spielers auf §e" + this.getConnectionManager().callRowCoins(tp) + "§bCC gesetzt");
            tp.sendMessage("§bDu hast jetzt §5" + this.getConnectionManager().callRowCoins(tp) + "§bCC");

    }

    public void Action_errorMessage(Player p, String cmdName, Exception e){
        p.sendMessage("§4Fehler§e: §4Im Befehl: §e" + cmdName + " §4ist es zu einem Fehler gekommen der Fehler: " + e.getLocalizedMessage());
        ConsoleWriter.writeErrorWithTag("§4Fehler§e: §4Im Befehl: §e" + cmdName + " §4ist es zu einem Fehler gekommen der Fehler: " + e.getLocalizedMessage());
    }

    public void Action_setGlobalWarp(Player p, String warpName){
        if(!warpName.equals("")){
            this.getGlobalWarpManager().createWarp(p, warpName, p.getWorld());
        }else{Action_helpGlobalWarp(p);}
    }

    public void Action_delGlobalWarp(Player p, String warpName){
        if(!warpName.equals("")){
            this.getGlobalWarpManager().delWarp(p, warpName);
        }else{Action_helpGlobalWarp(p);}
    }

    public void Action_helpGlobalWarp(Player p){
        if(this.getConfigManager().tagUseEnabled()){
            p.sendMessage(this.getConfigManager().getTag() + "§4Fehler benutze den Befehl so: ");
        }else{
            p.sendMessage("§4Fehler benutze den Befehl so: ");
        }

        Action_sendHelpLine(p,"InvWarp",getHelpArgs("set","name"),"setzt einen Mini-Spiel Warp");
        Action_sendHelpLine(p,"InvWarp",getHelpArgs("del","name"),"zum löschen eines Mini-Spiel Warp");
    }

    public void Action_showPlayerCoins(Player p){
        p.sendMessage("§aDu hast §b"+ this.getConnectionManager().callRowCoins(p) + " §aChaos-Coins");
    }

    public void Action_setMode(Player p, Player tp){
        try{
            if(tp == null){p.sendMessage("§4Fehler§e: §4kein Spieler als Ziel angegeben!");return;}
            if(tp.getName().equals(p.getName())){this.getPlayerManager().toggleBuildMode(tp); this.getInventoryManager().clearInv(tp); this.getCommandActionManager().Action_reloadItems(tp); return;}
            if(this.getPlayerManager().isBuildModeEnabled(tp)){
                this.getPlayerManager().toggleBuildMode(tp);
                this.getInventoryManager().clearInv(tp);
                p.sendMessage("§aDu hast den Spieler §b" + tp.getName() + " §aerfolgreich aus dem Bau-Modus entfernt");
                this.getCommandActionManager().Action_reloadItems(tp);

            }else{
                this.getPlayerManager().toggleBuildMode(tp);
                this.getInventoryManager().clearInv(tp);
                p.sendMessage("§aDu hast den Spieler §b" + tp.getName() + " §aerfolgreich in den Bau-Modus gesetzt");
            }
        }catch(NullPointerException | SQLException e){
            ConsoleWriter.writeErrorWithTag("this is not god please report it to me on Discord: Dragonhard117 Error: §e" + e);
        }
    }

    public void Action_helpClear(Player p){
        if(this.getConfigManager().tagUseEnabled()){
            p.sendMessage(this.getConfigManager().getTag() + "§4Fehler benutze den Befehl so: ");
        }else{
            p.sendMessage("§4Fehler benutze den Befehl so: ");
        }
        Action_sendHelpLine(p,"chclear","","zum leeren des Chats");
    }

    public void Action_showHelp(Player p, String cmd){

        switch(cmd){
            case "*":
                cmd = "warp";
                Action_sendHelpHeader(p,"Befehl: §f" + cmd);
                Action_sendInfoLine(p,"warp",getInfoArgs("","name"),"um sich zu einem Warp zu teleportieren");
                Action_sendInfoLine(p,"warp",getInfoArgs("set","name"),"um sich einen Warp zu setzen");
                Action_sendInfoLine(p,"warp",getInfoArgs("del","name"),"um einen gesetzten Warp wieder zu löschen");

                Action_addSpacer(p);

                cmd = "spawn";
                Action_sendHelpHeader(p,"Befehl: §f" + cmd);
                Action_sendInfoLine(p,"spawn","","um zum Spawn zu gelangen");
                Action_sendInfoLine(p,"spawn",getInfoArgs("set",""),"zum erstellen eines Spawns");
                Action_sendInfoLine(p,"spawn",getInfoArgs("del",""),"zum löschen eines Spawns");

                Action_addSpacer(p);

                cmd = "invWarp";
                Action_sendHelpHeader(p,"Befehl: §f" + cmd);
                Action_sendInfoLine(p,"invWarp",getInfoArgs("set","warpName"),"zum setzt einen Mini-Spiel Warp");
                Action_sendInfoLine(p,"invWarp",getInfoArgs("del","warpName"),"zum löschen eines Mini-Spiel Warp");

                Action_addSpacer(p);

                cmd = "block";
                Action_sendHelpHeader(p,"Befehl: §f" + cmd);
                Action_sendInfoLine(p,"block",getInfoArgs("",""),"keine Information!");

                Action_addSpacer(p);

                cmd = "coins";
                Action_sendHelpHeader(p,"Befehl: §f" + cmd);
                Action_sendInfoLine(p,"coins","","zeigt deine Chaos-Coins");
                Action_sendInfoLine(p,"coins",getInfoArgs("see","name"),"zeigt die Chaos-Coins eines Spielers");
                Action_sendInfoLine(p,"coins",getInfoArgs("set","name") + getInfoArgs("","value"),"setzt die Chaos-Coins eines Spielers");
                Action_sendInfoLine(p,"coins",getInfoArgs("add","name") + getInfoArgs("","value"),"gibt einem Spieler Chaos-Coins");

                Action_addSpacer(p);

                cmd = "chclear";
                Action_sendHelpHeader(p,"Befehl: §f" + cmd);
                Action_sendInfoLine(p,"chclear","","zum leeren des Chats");

                Action_addSpacer(p);

                cmd = "crServer";
                Action_sendHelpHeader(p,"Befehl: §f" + cmd);
                Action_sendInfoLine(p,"crServer",getInfoArgs("",""),"keine Information!");

                Action_addSpacer(p);

                cmd = "bm";
                Action_sendHelpHeader(p,"Befehl: §f" + cmd);
                Action_sendInfoLine(p,"bm",getInfoArgs("","Spieler"),"setzt den Spieler in den Bau-Modus");

                Action_addSpacer(p);

                cmd = "info";
                Action_sendHelpHeader(p,"Befehl: §f" + cmd);
                Action_sendInfoLine(p,"info",getInfoArgs("","Spieler"),"zeigt eine Liste von nützlichen Informationen");

                Action_addSpacer(p);

                cmd = "lbv";
                Action_sendHelpHeader(p,"Befehl: §f" + cmd);
                Action_sendInfoLine(p,"lbv","","zeigt die aktuelle Version des Lobby-Systems");

                break;
            case "warp":
                Action_sendHelpHeader(p,"Befehl: §f" + cmd);
                Action_sendInfoLine(p,"warp",getInfoArgs("","name"),"um sich zu einem Warp zu teleportieren");
                Action_sendInfoLine(p,"warp",getInfoArgs("set","name"),"um sich einen Warp zu setzen");
                Action_sendInfoLine(p,"warp",getInfoArgs("del","name"),"um einen gesetzten Warp wieder zu löschen");
                break;
            case "spawn":
                Action_sendHelpHeader(p,"Befehl: §f" + cmd);
                Action_sendInfoLine(p,"spawn","","um zum Spawn zu gelangen");
                Action_sendInfoLine(p,"spawn",getInfoArgs("set",""),"zum erstellen eines Spawns");
                Action_sendInfoLine(p,"spawn",getInfoArgs("del",""),"zum löschen eines Spawns");
                break;
            case "InvWarp":
                Action_sendHelpHeader(p,"Befehl: §f" + cmd);
                Action_sendInfoLine(p,"invWarp",getInfoArgs("set","warpName"),"setzt einen Mini-Spiel Warp");
                Action_sendInfoLine(p,"invWarp",getInfoArgs("del","warpName"),"zum löschen eines Mini-Spiel Warp");
                break;
            case "block":
                Action_sendHelpHeader(p,"Befehl: §f" + cmd);
                Action_sendInfoLine(p,"block",getInfoArgs("",""),"keine Information!");
                break;
            case "coins":
                Action_sendHelpHeader(p,"Befehl: §f" + cmd);
                Action_sendInfoLine(p,"coins","","zeigt deine Chaos-Coins");
                Action_sendInfoLine(p,"coins",getInfoArgs("see","name"),"zeigt die Chaos-Coins eines Spielers");
                Action_sendInfoLine(p,"coins",getInfoArgs("set","name") + getInfoArgs("","value"),"setzt die Chaos-Coins eines Spielers");
                Action_sendInfoLine(p,"coins",getInfoArgs("add","name") + getInfoArgs("","value"),"gibt einem Spieler Chaos-Coins");
                break;
            case "chclear":
                Action_sendHelpHeader(p,"Befehl: §f" + cmd);
                Action_sendInfoLine(p,"chclear","","zum leeren des Chats");
                break;
            case "crServer":
                Action_sendHelpHeader(p,"Befehl: §f" + cmd);
                Action_sendInfoLine(p,"crServer",getInfoArgs("",""),"keine Information!");
                break;
            case "bm":
                Action_sendHelpHeader(p,"Befehl: §f" + cmd);
                Action_sendInfoLine(p,"bm",getInfoArgs("","Spieler"),"setzt den Spieler in den Bau-Modus");
                break;
            case "info":
                Action_sendHelpHeader(p,"Befehl: §f" + cmd);
                Action_sendInfoLine(p,"info",getInfoArgs("","Spieler"),"zeigt eine Liste von nützlichen Informationen");
                break;
            case "lbv":
                Action_sendHelpHeader(p,"Befehl: §f" + cmd);
                Action_sendInfoLine(p,"lbv","","zeigt die aktuelle Version des Lobby-Systems");
                break;
            default:p.sendMessage("§4Befehl existiert nicht");
        }

    }

    public void Action_addSpacer(Player p){p.sendMessage("");}

    public void Action_helpSpawn(Player p){
        if(this.getConfigManager().tagUseEnabled()){
            p.sendMessage(this.getConfigManager().getTag() + "§4Fehler benutze den Befehl so: ");
        }else{
            p.sendMessage("§4Fehler benutze den Befehl so: ");
        }

        Action_sendHelpLine(p,"spawn","","um zum Spawn zu gelangen");
        Action_sendHelpLine(p,"spawn",getHelpArgs("set",""),"zum erstellen eines Spawns");
        Action_sendHelpLine(p,"spawn",getHelpArgs("del",""),"zum löschen eines Spawns");

    }

    public void Action_helpCoins(Player p){

        if(this.getConfigManager().tagUseEnabled()){
            p.sendMessage(this.getConfigManager().getTag() + "§4Fehler benutze den Befehl so: ");
        }else{
            p.sendMessage("§4Fehler benutze den Befehl so: ");
        }

        Action_sendHelpLine(p,"coins","","zeigt deine Chaos-Coins");
        Action_sendHelpLine(p,"coins",getHelpArgs("see","name"),"zeigt die Chaos-Coins eines Spielers");
        Action_sendHelpLine(p,"coins",getHelpArgs("set","name") + getHelpArgs("","value"),"setzt die Chaos-Coins eines Spielers");
        Action_sendHelpLine(p,"coins",getHelpArgs("add","name") + getHelpArgs("","value"),"gibt einem Spieler Chaos-Coins");

    }

    private String getHelpArgs(String argTitle, String requireContent){
        if(argTitle.equals("") && requireContent.equals("")){return "";}
        if(argTitle.equals("")){return " §4<§e"+requireContent+"§4>";}
        if(requireContent.equals("")){return  " §4"+argTitle;}

        return " §4"+argTitle+" <§e"+requireContent+"§4>";
    }

    private String getInfoArgs(String argTitle, String requireContent){
        if(argTitle.equals("") && requireContent.equals("")){return "";}
        if(argTitle.equals("")){return " §b<§f"+requireContent+"§b>";}
        if(requireContent.equals("")){return  " §b"+argTitle;}

        return " §b"+argTitle+" <§f"+requireContent+"§b>";
    }

    private void Action_sendHelpLine(Player p, String cmdName, String args, String description){
        p.sendMessage("                                 §4Hilfe >> §e/"+cmdName + args+" §4-> " + description);
    }

    private void Action_sendInfoLine(Player p, String cmdName, String args, String description){
        p.sendMessage("§bInfo >> §f/"+cmdName + args+" §b-> " + description);
    }

    public void Action_sendHelpHeader(Player p, String title){
        p.sendMessage("§f----------------------------------------------------------| §5" + title + " §f|----------------------------------------------------------" );
    }

    public void Action_PluginVersion(Player p){
        p.sendMessage(default_color_Message + "Version: " + default_color_value + version);
    }

    public void Action_setWarp(Player p, String warpName){
        if (this.getPlayerManager().isWarpEnabled(p)) {
            if(!warpName.isEmpty()){

                if(this.getPlayerManager().hasInf(p)){
                    if(!this.getWarpManager().exists(p, warpName)){
                        this.getWarpManager().createWarp(p, warpName,p.getWorld());
                        this.getMySqlManager().connect("setRowWarpUsed",p);
                    }
                }else{
                    if(this.getPlayerManager().getCurrentWarps(p) != this.getPlayerManager().getMaxWarps(p)){
                        if(!this.getWarpManager().exists(p, warpName)){
                            this.getWarpManager().createWarp(p, warpName,p.getWorld());
                            this.getMySqlManager().connect("setRowWarp",p);
                        }
                    }else{
                        p.sendMessage("§4Du hast alle deine Warps in Verwändung!");
                        p.sendMessage("§4lösche einen um einen neuen setzen zu können!");
                    }
                }


            }else{
               Action_helpWarp(p);
            }
        }else {
            this.getCommandActionManager().Action_notActive(p);
        }
    }



    public void Action_delWarp(Player p, String warpName){

        if(!warpName.isEmpty()) {

            if (warpName.equals("*")) {

                p.sendMessage("§asuche alle warps ...");
                this.getWarpManager().delAllWarps(p);
                p.sendMessage("§aAlle warps wurden erfolgreich entfernt!");
            }else {

                if (this.getWarpManager().exists(p, warpName) && this.getPlayerManager().getCurrentWarps(p) > 0) {
                    this.getMySqlManager().connect("delRowWarp",p);
                }
                this.getWarpManager().delWarp(p, warpName);
            }

        }else{Action_helpWarp(p);}

    }

    public String getStatusTag(String value){
        return " §e[§4"+value+"§e]";
    }

    public void Action_helpWarp(Player p){
        if(this.getConfigManager().tagUseEnabled()){
            p.sendMessage(this.getConfigManager().getTag() + "§4Fehler benutze den Befehl so: ");
        }else{
            p.sendMessage("§4Fehler benutze den Befehl so: ");
        }

        Action_sendHelpLine(p,"warp",getHelpArgs("","name"),"um sich zu einem Warp zu teleportieren");
        Action_sendHelpLine(p,"warp",getHelpArgs("set","name"),"um sich einen Warp zu setzen");
        Action_sendHelpLine(p,"warp",getHelpArgs("del","name"),"um einen gesetzten Warp wieder zu löschen");

    }

    public void Action_clearChat(Player p){

        for(int i = 0; i < 100; i++){
            Bukkit.broadcastMessage("");
        }

        Bukkit.broadcastMessage("§e" + p.getName() + " §bhat den Chat geleert!");

    }

    private String getGroup(Player target){
        String group = "";
        if(this.getPluginWhitelistManager().isTeam(target)){
            group = "Team";
        }else{
            group = "Standard";
        }
        return group;
    }

    public void Action_WarpTeleport(Player p, String warp){
        this.getWarpManager().teleportPlayer(p, warp);
    }

    public void Action_reloadItems(Player p){
        InventorySetter is = new InventorySetter();
        if(!this.getPlayerManager().isBuildModeEnabled(p)){
            is.getHotbarItems(p);
        }
    }

    public void Action_createSpawn(Player p){
        if(p.hasPermission(PermissionList.getPermission("Config",1))){
            this.getSpawnManager().createSpawn(p, p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), p.getLocation().getPitch(), p.getLocation().getYaw(), p.getWorld());
        }else{Action_noPermission(p);}
    }

    public void Action_delSpawn(Player p){
        if(p.hasPermission(PermissionList.getPermission("Config",2))){
            this.getSpawnManager().delSpawn(p);
        }else{Action_noPermission(p);}
    }

    public void Action_teleportToSpawn(Player p){
        if(this.getSpawnManager().exists(this.getSpawnManager().getFile("Spawn"))) {
            this.getSpawnManager().teleportPlayerToSpawn(p);
            Message.sendMessageByTag(Message.getMessageByTag("tpSpawn"),p);
        }else{
            p.sendMessage("§4Der Spawn wurde nicht gefunden!");
        }
    }

    public void Action_notActive(Player p){
        p.sendMessage("§4Diese Funktion ist derzeit nicht verfügbar!");
        p.sendMessage("§4Bitte an das Server-Team melden!");
    }

    public void Action_noPermission(Player p){
        p.sendMessage("§4Fehler§e: §4Du hast nicht die erforderliche Berechtigung!");
    }

    public void Action_WarpCount(Player p){

        if(this.getConfigManager().tagUseEnabled()){
            if(this.getPlayerManager().hasInf(p)){
                p.sendMessage(this.getConfigManager().getTag() + "§eDu benutzt §4" + this.getPlayerManager().getCurrentWarps(p) + "§e/§a unbegrenzt §eWarps");
            }else{
                p.sendMessage(this.getConfigManager().getTag() + "§eDu benutzt §4" + this.getPlayerManager().getCurrentWarps(p) + "§e/§4" + this.getPlayerManager().getMaxWarps(p) + " §eWarps");
            }
        }else{
            if(this.getPlayerManager().hasInf(p)){
                p.sendMessage("§eDu benutzt §4" + this.getPlayerManager().getCurrentWarps(p) + "§e/§a unbegrenzt §eWarps");
            }else{
                p.sendMessage("§eDu benutzt §4" + this.getPlayerManager().getCurrentWarps(p) + "§e/§4" + this.getPlayerManager().getMaxWarps(p) + " §eWarps");
            }
        }

    }

    public void Action_PlayerInformation(Player p, Player target){
            p.sendMessage("§f￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭");
            p.sendMessage("                  §bÜbersicht");
            p.sendMessage("§f￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭  §bSpieler§f-§bInfo");

            p.sendMessage(item_color + "Name: " + item_value_color + target.getName());
            p.sendMessage(item_color + "Nickname: " + item_value_color + item_value_color + target.getDisplayName());
            p.sendMessage(item_color + "UUID: " + item_value_color + target.getUniqueId());
            p.sendMessage(item_color + "Operator: " + item_value_color + target.isOp());
            p.sendMessage(item_color + "Login: " + item_value_color + this.getDateManager().getPlayerDate(target));
            p.sendMessage(item_color + "Chaos-Coins: " + item_value_color + this.getConnectionManager().callRowCoins(p));

            p.sendMessage("§f￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭  §bGruppe");

            p.sendMessage(item_color + "Gruppe: " + item_value_color + getGroup(target));
            p.sendMessage(item_color + "Rang: " + item_value_color + this.getConfigManager().getAccessLevelTag(Integer.parseInt(this.getConnectionManager().callRowLevel(target))));

            p.sendMessage("§f￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭  §bNetzwerk");

            String[] ip_port = target.getAddress().toString().replace("/","").split(":");
            String ip = ip_port[0];
            String port = ip_port[1];
            p.sendMessage(item_color + "IP-Adresse: " + item_value_color + ip);
            p.sendMessage(item_color + "Port: " + item_value_color + port);

            if(this.getConfigManager().exists("perm_list")) {
                p.sendMessage("§f￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭  §bRechte");

                for (int i = 0; i < this.getConfigManager().getPermissionCount(); i++) {
                    if(this.getConfigManager().getPermissionName(i) != null) {
                        if(this.getConfigManager().hasPermission(target, i)){item_value_color = "§a";}else{item_value_color = "§4";}
                        p.sendMessage(item_color + this.getConfigManager().getPermissionName(i) + ": " + item_value_color + this.getConfigManager().hasPermission(target, i));
                    }
                }
                item_value_color = "§e";
            }
            p.sendMessage("§f￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭  §bStatus");

            String msg_Sprint = item_color + "läuft: " + item_value_color + target.isSprinting(); msg_Sprint.replace("false","§4false"); msg_Sprint.replace("true","§atrue");
            String msg_Sleep = item_color + "schläft: " + item_value_color + target.isSleeping(); msg_Sleep.replace("false","§4false"); msg_Sleep.replace("true","§atrue");
            String msg_Fly = item_color + "fliegt: " + item_value_color + target.isFlying(); msg_Fly.replace("false","§4false"); msg_Fly.replace("true","§atrue");
            String msg_Sneak = item_color + "schleicht: " + item_value_color + target.isSneaking(); msg_Sneak.replace("false","§4false"); msg_Sneak.replace("true","§atrue");
            String msg_Whithelist = item_color + "Whitelist: " + item_value_color + target.isWhitelisted(); msg_Whithelist.replace("false","§4false"); msg_Whithelist.replace("true","§atrue");

            p.sendMessage(msg_Sprint);
            p.sendMessage(msg_Sleep);
            p.sendMessage(msg_Fly);
            p.sendMessage(msg_Sneak);
            p.sendMessage(msg_Whithelist);

            p.sendMessage("§f￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭");
    }

}
