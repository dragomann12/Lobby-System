package de.dragonhard.lobby.components.menu;

import de.dragonhard.lobby.Main;
import de.dragonhard.lobby.manager.*;
import io.netty.channel.ConnectTimeoutException;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.CommandException;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.Plugin;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class Lobby_Menu extends Lobby_Inventory implements Listener {
    private Player p;
    private String menuName = "Lobby";
    public void openInventory(Player p){
        this.p = p;
        ConfigManager cm = new ConfigManager();

        int lineAmmount = 5;
        int i = 0;
        int slots = lineAmmount*9;

        this.setInventory("§"+ cm.getMenuTitleColor(menuName) + cm.getMenuTitle(menuName),lineAmmount);

        for(i = 0; i<slots; i++){

            if(cm.slotIsEnabled(menuName,i)){

                if(cm.getSlotTitle(menuName,i).contains(" /nonTag")) {
                    String title = cm.getSlotTitle(menuName, i).replace(" /nonTag", "");
                    this.addItemToInventory(title, Material.getMaterial(cm.getSlotMaterial(menuName, i)), "§" + cm.getSlotTitleColor(menuName, i), i);
                }else if(cm.getSlotTitle(menuName,i).contains(" - Wartung")){
                    String title = cm.getSlotTitle(menuName, i).replace(" - Wartung", " §4Wartung");
                    this.addItemToInventory(title + this.getGameTag(), Material.getMaterial(cm.getSlotMaterial(menuName, i)), "§" + cm.getSlotTitleColor(menuName, i), i);
                }else if(cm.getSlotTitle(menuName,i).contains(" - offline")){
                    String title = cm.getSlotTitle(menuName, i).replace(" - offline", " §4offline");
                    this.addItemToInventory(title + this.getTag("Item"), Material.getMaterial(cm.getSlotMaterial(menuName, i)), "§" + cm.getSlotTitleColor(menuName, i), i);
                }else if(cm.getSlotTitle(menuName,i).contains(" - err")){
                    String title = cm.getSlotTitle(menuName, i).replace(" - err", " §4offline §e[§4Fehler§e]");
                    this.addItemToInventory(title + this.getTag("Item"), Material.getMaterial(cm.getSlotMaterial(menuName, i)), "§" + cm.getSlotTitleColor(menuName, i), i);
                }else{
                    this.addItemToInventory(cm.getSlotTitle(menuName,i) + this.getTag("Item"), Material.getMaterial(cm.getSlotMaterial(menuName,i)),"§" + cm.getSlotTitleColor(menuName,i),i);
                }

            }else{
                if(i <= 8){
                    this.addWallItemToInventory(p,i);
                }else {
                    switch(i){
                        case 9: this.addWallItemToInventory(p,i);break;
                        case 17: this.addWallItemToInventory(p,i);break;
                        case 18: this.addWallItemToInventory(p,i);break;
                        case 26: this.addWallItemToInventory(p,i);break;
                        case 27: this.addWallItemToInventory(p,i);break;
                        case 35: this.addWallItemToInventory(p,i);break;
                        case 36: this.addWallItemToInventory(p,i);break;
                        case 37: this.addWallItemToInventory(p,i);break;
                        case 38: this.addWallItemToInventory(p,i);break;
                        case 39: this.addWallItemToInventory(p,i);break;
                        case 40: this.addWallItemToInventory(p,i);break;
                        case 41: this.addWallItemToInventory(p,i);break;
                        case 42: this.addWallItemToInventory(p,i);break;
                        case 43: this.addWallItemToInventory(p,i);break;
                        case 44: this.addWallItemToInventory(p,i);break;
                        default: this.addGoldWallItemToInventory(p,i);break;
                    }
                }
            }

        }

        p.openInventory(this.getInventory());

    }


    @EventHandler
    public void onModeClick(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        InventoryManager im = new InventoryManager();
        GlobalWarpManager gwm = new GlobalWarpManager();
        PlayerConfigManager pm = new PlayerConfigManager();
        SpawnManager sm = new SpawnManager();
        ConfigManager cm = new ConfigManager();

        if(e.getInventory().getName().equals("§"+ cm.getMenuTitleColor(menuName) + cm.getMenuTitle(menuName))){

            if(e.getCurrentItem().getItemMeta().getDisplayName().contains(this.getTag("Item"))) {

                String slot = cm.getSlotTitle(menuName,e.getSlot());

                if(slot.contains("?")){p.sendMessage("§4Die Lobby ist nicht verfügbar!"); return;}
                else if(slot.contains("Wartung")){p.sendMessage("§4Die Lobby ist wegen arbeiten offline!"); return;}
                else if(slot.contains("offline")){p.sendMessage("§4Die Lobby ist derzeit offline!"); return;}
                else if(slot.contains("err")){p.sendMessage("§4Die Lobby ist aufgrund eines Problems offline!"); return;}
                else if(slot.contains("Spawn")){sm.teleportPlayerToSpawn(p); return;}

                p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);

                BungeeCordManager.sendPlayerToServer(p, slot);

            }

            return;
        }

    }

}
