package de.dragonhard.lobby.components.menu.shop;

import de.dragonhard.lobby.components.menu.Lobby_Inventory;
import de.dragonhard.lobby.manager.other.*;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;

public class Shop_Coin_Menu extends Lobby_Inventory implements Listener {


    private Player p;
    private static String menuName = "Coin_shop";
    private static String prefix = "#";
    private static ShopItemManager sim = new ShopItemManager();
    private static ArrayList<Integer> wall_item_id = new ArrayList<Integer>();
    private static PlayerConfigManager pm = new PlayerConfigManager();

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

                if(cm.getSlotTitle(menuName,i).contains(prefix + ItemTypes.TICKED)){
                    String title = cm.getSlotTitle(menuName, i).replace(prefix + ItemTypes.TICKED,this.getTag("§6Preis: §a" + PriceList.getPriceOf("TICKED","Karte") ) + " " + this.getTag("Item"));
                    if(title.contains("§6Preis: §") && pm.getCoins(p) < PriceList.getPriceOf("TICKED","Karte")){
                        title.replace("§6Preis: §", "§6Preis: §4");
                    }else if(title.contains("§6Preis: §") && pm.getCoins(p) == PriceList.getPriceOf("TICKED","Karte")){
                        title.replace("§6Preis: §", "§6Preis: §e");
                    }else if(title.contains("§6Preis: §") && pm.getCoins(p) > PriceList.getPriceOf("TICKED","Karte")){ }
                    this.addItemToInventory(title, Material.getMaterial(cm.getSlotMaterial(menuName, i)), "§" + cm.getSlotTitleColor(menuName, i), i);
                }else{
                    this.addItemToInventory(cm.getSlotTitle(menuName,i) + this.getTag("Item"), Material.getMaterial(cm.getSlotMaterial(menuName,i)),"§" + cm.getSlotTitleColor(menuName,i),i);
                }


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
        ConfigManager cm = new ConfigManager();

        if(e.getInventory().getName().equals("§"+ cm.getMenuTitleColor(menuName) + cm.getMenuTitle(menuName))){
            if (e.getCurrentItem() == null) {return;} else{

                if(e.getCurrentItem().getItemMeta().getDisplayName().contains(this.getTag("Item"))) {

                    if(e.getCurrentItem().getItemMeta().getDisplayName().contains( this.getTag("§4- §ekein verkauf §4-"))){return;}else{
                        if(cm.getSlotTitle(menuName, e.getSlot()).contains(prefix)){
                            p.playSound(p.getLocation(), Sound.CLICK,1.0F,1.0F);

                        }

                    }

                    return;

                }

            }

            return;
        }

    }

    private static class PriceList {

        public static int getPriceOf(String category, String item){

            String shop = menuName.replace("_","-");

            return sim.getPriceOfItem(shop,category,item);
        }

    }
}
