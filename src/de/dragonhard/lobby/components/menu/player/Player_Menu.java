package de.dragonhard.lobby.components.menu.player;

import de.dragonhard.lobby.components.menu.Lobby_Inventory;
import de.dragonhard.lobby.manager.Managers;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;

public class Player_Menu extends Lobby_Inventory implements Listener {

   private final String menuName = "Player";

    private static final ArrayList<Integer> wall_item_id = new ArrayList<Integer>();
    Managers manager = new Managers();

    public void openInventory(Player p){

        int lineAmount = 5;
        int i = 0;
        int slots = lineAmount*9;

        this.setInventory("§"+ manager.getConfigManager().getMenuTitleColor(menuName) + manager.getConfigManager().getMenuTitle(menuName),lineAmount);

        for(i = 0; i<slots; i++){

            if(manager.getConfigManager().slotIsEnabled(menuName,i)){
                String prefix = "#";

                if(manager.getConfigManager().getSlotTitle(menuName,i).contains(" /nonTag")) {
                    String title = manager.getConfigManager().getSlotTitle(menuName, i).replace(" /nonTag", "");
                    this.addItemToInventory(title, Material.getMaterial(manager.getConfigManager().getSlotMaterial(menuName, i)), "§" + manager.getConfigManager().getSlotTitleColor(menuName, i), i);

                }else if(manager.getConfigManager().getSlotTitle(menuName,i).contains(" " + prefix + InteractionType.VISUAL + " /coins")){
                    String title = manager.getConfigManager().getSlotTitle(menuName, i).replace(prefix + InteractionType.VISUAL + " /coins","Chaos-Coins: §e" + manager.getConnectionManager().callRowCoins(p) );
                    this.addItemToInventory(title, Material.getMaterial(manager.getConfigManager().getSlotMaterial(menuName,i)),"§" + manager.getConfigManager().getSlotTitleColor(menuName,i),i);

                }else if(manager.getConfigManager().getSlotTitle(menuName,i).contains(" " + prefix + InteractionType.VISUAL + " /nick")){
                    String title = manager.getConfigManager().getSlotTitle(menuName, i).replace(prefix + InteractionType.VISUAL + " /nick","Name: §e" + p.getName());
                    this.addSkullToInventory(p,title,"§" + manager.getConfigManager().getSlotTitleColor(menuName,i),i);

                }else if(manager.getConfigManager().getSlotTitle(menuName,i).contains(" " + prefix + InteractionType.VISUAL + " /id")){
                    String title = manager.getConfigManager().getSlotTitle(menuName, i).replace(prefix + InteractionType.VISUAL + " /id","UUID: §e" + p.getUniqueId());
                    this.addItemToInventory(title, Material.getMaterial(manager.getConfigManager().getSlotMaterial(menuName,i)),"§" + manager.getConfigManager().getSlotTitleColor(menuName,i),i);

                }else if(manager.getConfigManager().getSlotTitle(menuName,i).contains(prefix + InteractionType.PLACEHOLDER)){
                    String title = manager.getConfigManager().getSlotTitle(menuName, i).replace(prefix + InteractionType.PLACEHOLDER,"");
                    this.addItemToInventory(title, Material.getMaterial(manager.getConfigManager().getSlotMaterial(menuName,i)),"§" + manager.getConfigManager().getSlotTitleColor(menuName,i),i);

                }else if(manager.getConfigManager().getSlotTitle(menuName,i).contains(prefix + InteractionType.INTERACTION)){
                    String title = manager.getConfigManager().getSlotTitle(menuName, i).replace(prefix + InteractionType.INTERACTION + " /shop", this.getTag("Coin-Shop"));
                    this.addItemToInventory(title, Material.getMaterial(manager.getConfigManager().getSlotMaterial(menuName,i)),"§" + manager.getConfigManager().getSlotTitleColor(menuName,i),i);

                }else if(manager.getConfigManager().getSlotTitle(menuName,i).contains(prefix + InteractionType.VISUAL + " /ts")){
                    String title = manager.getConfigManager().getSlotTitle(menuName, i).replace(prefix + InteractionType.VISUAL + " /ts", "Teamspeak: §e" + manager.getConfigManager().getTeamspeak());
                    this.addItemToInventory(title, Material.getMaterial(manager.getConfigManager().getSlotMaterial(menuName,i)),"§" + manager.getConfigManager().getSlotTitleColor(menuName,i),i);

                }else if(manager.getConfigManager().getSlotTitle(menuName,i).contains(prefix + InteractionType.VISUAL + " /dc")){
                    String title = manager.getConfigManager().getSlotTitle(menuName, i).replace(prefix + InteractionType.VISUAL + " /dc", "Discord: §e" + manager.getConfigManager().getDiscord());
                    this.addItemToInventory(title, Material.getMaterial(manager.getConfigManager().getSlotMaterial(menuName,i)),"§" + manager.getConfigManager().getSlotTitleColor(menuName,i),i);

                }else{
                    this.addItemToInventory(manager.getConfigManager().getSlotTitle(menuName,i), Material.getMaterial(manager.getConfigManager().getSlotMaterial(menuName,i)),"§" + manager.getConfigManager().getSlotTitleColor(menuName,i),i);
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

        if(e.getInventory().getName().equals("§"+ manager.getConfigManager().getMenuTitleColor(menuName) + manager.getConfigManager().getMenuTitle(menuName))){

            if (e.getCurrentItem() != null) {

                 if(e.getCurrentItem().getItemMeta().getDisplayName().contains(this.getTag("Coin-Shop"))) {

                     p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);
                     manager.getMenuManager().getCoinShopMenu().openInventory(p);


                }else if(e.getCurrentItem().getItemMeta().getDisplayName().contains(this.getTag("Player-Settings"))){

                     p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);

                 }

            }

        }

    }

}
