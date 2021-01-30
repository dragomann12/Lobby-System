package de.dragonhard.lobby.components.menu;

import de.dragonhard.lobby.manager.Managers;
import de.dragonhard.lobby.manager.other.*;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;

public class Settings_Menu extends Lobby_Inventory implements Listener {

    private Player p;
    private String menuName = "Settings";
    private final Managers manager = new Managers();
    private static ArrayList<Integer> wall_item_id = new ArrayList<Integer>();
    public void openInventory(Player p){
        this.p = p;
        ConfigManager cm = new ConfigManager();
        AcessManager acm = new AcessManager();

        if(acm.isBlacklisted(p)){
            p.sendMessage("§4Du wurdest von den Einstellungen des Plugins ausgeschlossen!");
            p.sendMessage("§4Bitte wende dich an das Team!");return;}

        int lineAmmount = 5;
        int i = 0;
        int slots = lineAmmount*9;

        if(cm.getSlotTitle(menuName,i).contains(" /nonTag")) {
            String title = cm.getSlotTitle(menuName, i).replace(" /nonTag", "");
            this.addItemToInventory(title, Material.getMaterial(cm.getSlotMaterial(menuName, i)), "§" + cm.getSlotTitleColor(menuName, i), i);
        }else{
            this.setInventory("§"+ cm.getMenuTitleColor(menuName) + cm.getMenuTitle(menuName),lineAmmount);
        }


        for(i = 0; i<slots; i++){

            if(cm.slotIsEnabled(menuName,i)){

                this.addItemToInventory(cm.getSlotTitle(menuName,i), Material.getMaterial(cm.getSlotMaterial(menuName,i)),"§" + cm.getSlotTitleColor(menuName,i),i);

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

        if(e.getInventory().getName().equals("§"+ manager.getConfigManager().getMenuTitleColor(menuName) + manager.getConfigManager().getMenuTitle(menuName))){
            if (e.getCurrentItem() == null) {return;} else{
                if(e.getCurrentItem().getItemMeta().getDisplayName().contains(this.getTag("Item"))) {

                    if(e.getCurrentItem().getItemMeta().getDisplayName().equals("1")){

                    }else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("2")){
                    }else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("3")){
                    }else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("4")){



                    }

                }
            }

            return;
        }

    }

}
