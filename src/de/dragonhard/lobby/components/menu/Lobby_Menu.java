package de.dragonhard.lobby.components.menu;

import de.dragonhard.lobby.manager.Managers;
import de.dragonhard.lobby.manager.other.*;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;

public class Lobby_Menu extends Lobby_Inventory implements Listener {
    private Player p;
    private String menuName = "Lobby";
    private Managers manager = new Managers();
    private static ArrayList<Integer> wall_item_id = new ArrayList<Integer>();
    public void openInventory(Player p){
        this.p = p;
        ConfigManager cm = new ConfigManager();

        int lineAmmount = 5;
        int i = 0;
        int slots = lineAmmount*9;

        this.setInventory("§"+ cm.getMenuTitleColor(menuName) + cm.getMenuTitle(menuName),lineAmmount);

        for(i = 0; i<slots; i++){

            if(cm.slotIsEnabled(menuName,i)){

                    this.addItemToInventory( manager.getConfigManager().getSlotTitle(menuName, i) + this.getTag("Item"), Material.getMaterial(cm.getSlotMaterial(menuName,i)),"§" + cm.getSlotTitleColor(menuName,i),i);

            }else{

                    if(wall_item_id.contains(i)){
                        this.addWallItemToInventory(p,i);
                    }else{
                        this.addGoldWallItemToInventory(p,i);
                    }

            }

        }

        p.openInventory(this.getInventory());

    }

    public void addWallIDs(){

        for(int i = 0; i <=9; i++){
            wall_item_id.add(i);
        }

        wall_item_id.add(17);
        wall_item_id.add(18);
        wall_item_id.add(26);
        wall_item_id.add(27);

        for(int i = 35; i <=44; i++){
            wall_item_id.add(i);
        }

    }

    @EventHandler
    public void onModeClick(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        InventoryManager im = new InventoryManager();
        GlobalWarpManager gwm = new GlobalWarpManager();
        PlayerConfigManager pm = new PlayerConfigManager();
        SpawnManager sm = new SpawnManager();
        ConfigManager cm = new ConfigManager();

        if(e.getInventory().getName().equals("§"+ manager.getConfigManager().getMenuTitleColor(menuName) + manager.getConfigManager().getMenuTitle(menuName))){
            if (e.getCurrentItem() != null) {
                if(e.getCurrentItem().getItemMeta().getDisplayName().contains(this.getTag("Item"))) {

                    String slot = manager.getConfigManager().getSlotTitle(menuName,e.getSlot());

                    if(slot.contains("?")){p.sendMessage("§4Die Lobby ist nicht verfügbar!"); return;}
                    else if(slot.contains("Wartung")){p.sendMessage("§4Die Lobby ist wegen arbeiten offline!"); return;}
                    else if(slot.contains("offline")){p.sendMessage("§4Die Lobby ist derzeit offline!"); return;}
                    else if(slot.contains("err")){p.sendMessage("§4Die Lobby ist aufgrund eines Problems offline!"); return;}

                    p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);
                    BungeeCordManager.sendPlayerToServer(p, manager.getConfigManager().getSlotTitle(menuName,e.getSlot()));

                }
            }
        }

    }

}
