package de.dragonhard.lobby.components.menu;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MenuItem {

    private final String displayName;
    private final Material material;
    private final int slot;
    private final String skillId;
    private String defaultColor = "§5";

    public MenuItem(String displayName, Material material, String skillId, int slot){

        this.displayName = displayName;
        this.material = material;
        this.slot = slot;
        this.skillId = skillId;
    }

    public int getSlot(){
        return slot;
    }

    public String getSkillId(){
        return skillId;
    }

    public ItemStack item(){

        ItemStack item = new ItemStack(material,1);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(defaultColor + displayName);
        item.setItemMeta(itemMeta);

        return item;
    }
}
