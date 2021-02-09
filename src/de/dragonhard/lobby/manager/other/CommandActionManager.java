package de.dragonhard.lobby.manager.other;

import de.dragonhard.lobby.components.Message;
import de.dragonhard.lobby.components.PermissionList;
import de.dragonhard.lobby.components.util.InventorySetter;
import de.dragonhard.lobby.manager.Managers;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CommandActionManager extends Managers {

    final String item_color = "§6";
    String item_value_color = "§e";
    final String default_color_Message = "§f";
    final String default_color_value = "§e";
    final String version = "1.4.5  beta-Build: 145.5";

    public void Action_PluginVersion(Player p){
        p.sendMessage(default_color_Message + "Version: " + default_color_value + version);
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

            p.sendMessage("§f￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭￭  §bStatistic");

            p.sendMessage("      §4nach Update verfügbar");

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
