package de.dragonhard.lobby.components.menu.shop;

import de.dragonhard.lobby.components.ConsoleWriter;
import de.dragonhard.lobby.components.menu.Lobby_Inventory;
import de.dragonhard.lobby.manager.*;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;

public class Shop_Coin_Menu extends Lobby_Inventory implements Listener {


    private Player p;
    private String menuName = "Coin_shop";
    private String prefix = "#";
    private static ArrayList<Integer> wall_item_id = new ArrayList<Integer>();
    public void openInventory(Player p){
        AcessManager acm = new AcessManager();
        this.p = p;

        //if(acm.isBlacklisted(p)){
          //  p.sendMessage("§4Du wurdest vom Shop ausgeschlossen!");
           // p.sendMessage("§4Bitte wende dich an das Team!");return;}

        ConfigManager cm = new ConfigManager();

        int lineAmmount = 5;
        int i = 0;
        int slots = lineAmmount*9;

        this.setInventory("§"+ cm.getMenuTitleColor(menuName) + cm.getMenuTitle(menuName),lineAmmount);

        for(i = 0; i<slots; i++){

            if(cm.slotIsEnabled(menuName,i)){

                if(cm.getSlotTitle(menuName,i).contains(prefix + ItemTypes.BOOTS)){
                    String title = cm.getSlotTitle(menuName, i).replace(prefix + ItemTypes.BOOTS,this.getTag("§6Preis: " + PriceList.getPriceOf("Boots") ) + " " + this.getTag("Item"));
                    this.addItemToInventory(title, Material.getMaterial(cm.getSlotMaterial(menuName, i)), "§" + cm.getSlotTitleColor(menuName, i), i);
                }else if(cm.getSlotTitle(menuName,i).contains(prefix + ItemTypes.HEAD)){
                    String title = cm.getSlotTitle(menuName, i).replace(prefix + ItemTypes.HEAD,this.getTag("§6Preis: " + PriceList.getPriceOf("HEAD") ) + " " + this.getTag("Item"));
                    this.addItemToInventory(title, Material.getMaterial(cm.getSlotMaterial(menuName, i)), "§" + cm.getSlotTitleColor(menuName, i), i);
                }else if(cm.getSlotTitle(menuName,i).contains(prefix + ItemTypes.EFFECT)){
                    String title = cm.getSlotTitle(menuName, i).replace(prefix + ItemTypes.EFFECT,this.getTag("§6Preis: " + PriceList.getPriceOf("EFFECT") ) + " " + this.getTag("Item"));
                    this.addItemToInventory(title, Material.getMaterial(cm.getSlotMaterial(menuName, i)), "§" + cm.getSlotTitleColor(menuName, i), i);
                }else if(cm.getSlotTitle(menuName,i).contains(prefix + ItemTypes.PLACEHOLDER)){
                    String title = cm.getSlotTitle(menuName, i).replace(prefix + ItemTypes.PLACEHOLDER, this.getTag("§4- §ekein verkauf §4-"));
                    this.addItemToInventory(title, Material.getMaterial(cm.getSlotMaterial(menuName, i)), "§" + cm.getSlotTitleColor(menuName, i), i);
                }else if(cm.getSlotTitle(menuName,i).contains(prefix + ItemTypes.POTION)){
                    String title = cm.getSlotTitle(menuName, i).replace(prefix + ItemTypes.POTION,this.getTag("§6Preis: " + PriceList.getPriceOf("POTION") ) + " " + this.getTag("Item"));
                    this.addItemToInventory(title, Material.getMaterial(cm.getSlotMaterial(menuName, i)), "§" + cm.getSlotTitleColor(menuName, i), i);
                }else if(cm.getSlotTitle(menuName,i).contains(prefix + ItemTypes.TICKED)){
                    String title = cm.getSlotTitle(menuName, i).replace(prefix + ItemTypes.TICKED,this.getTag("§6Preis: " + PriceList.getPriceOf("TICKED") ) + " " + this.getTag("Item"));
                    this.addItemToInventory(title, Material.getMaterial(cm.getSlotMaterial(menuName, i)), "§" + cm.getSlotTitleColor(menuName, i), i);
                }else if(cm.getSlotTitle(menuName,i).contains(prefix + ItemTypes.WEAPON)){
                    String title = cm.getSlotTitle(menuName, i).replace(prefix + ItemTypes.WEAPON,this.getTag("§6Preis: " + PriceList.getPriceOf("WEAPON") ) + " " + this.getTag("Item"));
                    this.addItemToInventory(title, Material.getMaterial(cm.getSlotMaterial(menuName, i)), "§" + cm.getSlotTitleColor(menuName, i), i);
                }else{
                    this.addItemToInventory(cm.getSlotTitle(menuName,i) + this.getTag("Item"), Material.getMaterial(cm.getSlotMaterial(menuName,i)),"§" + cm.getSlotTitleColor(menuName,i),i);
                }


            }else{
                if(i <= 8){
                    this.addWallItemToInventory(p,i);
                }else {

                    if(wall_item_id.contains(i)){
                        this.addWallItemToInventory(p,i);
                    }else{
                        this.addGoldWallItemToInventory(p,i);
                    }

                }
            }

        }

        p.openInventory(this.getInventory());

    }

    public void addWallIDs(){

        wall_item_id.add(9);
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
        ConfigManager cm = new ConfigManager();

        if(e.getInventory().getName().equals("§"+ cm.getMenuTitleColor(menuName) + cm.getMenuTitle(menuName))){
            if (e.getCurrentItem() == null) {return;} else{

                if(e.getCurrentItem().getItemMeta().getDisplayName().contains(this.getTag("Item"))) {

                    if(e.getCurrentItem().getItemMeta().getDisplayName().contains( this.getTag("§4- §ekein verkauf §4-"))){return;}else{
                        if(cm.getSlotTitle(menuName,e.getSlot()).contains(prefix + ItemTypes.BOOTS)){}
                        if(cm.getSlotTitle(menuName,e.getSlot()).contains(prefix + ItemTypes.HEAD)){}
                        if(cm.getSlotTitle(menuName,e.getSlot()).contains(prefix + ItemTypes.EFFECT)){
                            switch(e.getCurrentItem().getItemMeta().getDisplayName()){
                                case "fly":
                            }
                        }
                        if(cm.getSlotTitle(menuName,e.getSlot()).contains(prefix + ItemTypes.POTION)){}
                        if(cm.getSlotTitle(menuName,e.getSlot()).contains(prefix + ItemTypes.TICKED)){}
                        if(cm.getSlotTitle(menuName,e.getSlot()).contains(prefix + ItemTypes.WEAPON)){}


                    }

                    return;

                }

            }

            return;
        }

    }

    private static class PriceList {

        public static int getPriceOf(String item){

            switch(item){
                case "BOOTS": return 200;
                case "HEAD": return 450;
                case "EFFECT": return 300;
                case "POTION": return 250;
                case "TICKED": return 500;
                case "WEAPON": return 1000;
            }

            return 0;
        }

    }
}
