package de.dragonhard.lobby.components.menu;

import de.dragonhard.lobby.manager.skill.SkillManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;


public class Menu implements Listener {
    private final String name;
    private final String color;
    private final Player player;
    private final int lines;
    private final ArrayList<MenuItem> items = new ArrayList<>();
    private final ArrayList<Integer> usedSlots = new ArrayList<>();
    private Inventory inventory = null;



   public Menu(String name, String color, int lines, Player player){

       this.name = name;
       this.color = color;
       this.lines = lines;
       this.player = player;
   }

    public void openInventory(){
        int slots = lines*9;

        inventory = Bukkit.getServer().createInventory(null, slots, name);

        for(int i = 0; i < items.size(); i++){

            inventory.setItem(items.get(i).getSlot(),items.get(i).item());
            usedSlots.add(items.get(i).getSlot());

        }

        player.openInventory(inventory);

    }

    public void addItem(MenuItem menuItem){
       items.add(menuItem);
    }

    @EventHandler
    public void onModeClick(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();

        for(int i = 0; i < items.size(); i++){
            if(e.getClickedInventory().getItem(e.getSlot()).getItemMeta().getDisplayName().equals(
                    items.get(i).item().getItemMeta().getDisplayName())
            ){
                if(items.get(i).getSkillId().equals("") || items.get(i).getSkillId().equals(null)){return;}

                SkillManager.initSkill(items.get(i).getSkillId(), p);

            }
        }

    }

}
