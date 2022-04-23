package de.dragonhard.lobby.manager.other;


import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class InventoryManager {
        String defaultColor = "§5";
        String soonLabel = "kommt noch";
        String betaTag = "Beta";
    public void clearInv(Player p){ p.getInventory().clear(); }

    private Inventory getInv(Player p){

      Inventory inv = p.getInventory();
      inv.setMaxStackSize(1);
        return inv;
    }

    private ItemStack addItem(String title, Material material){

        ItemStack item = new ItemStack(material);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(defaultColor + title);
        item.setItemMeta(itemMeta);

        return item;
    }

    private ItemStack createSkull(String title){
        ItemStack item = new ItemStack(Material.SKULL_ITEM,1,(byte)3);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(title);
        item.setItemMeta(itemMeta);
        return item;
    }

    public void addSkull(Player p,String title, String colorTag, int slotId){
        getInv(p).setItem(slotId,createSkull(colorTag + title));

    }

    private ItemStack addGlassItem(String title, Material material, int id){

        ItemStack item = new ItemStack(material, 1,(short)id);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(defaultColor + title);
        item.setItemMeta(itemMeta);

        return item;
    }

    public void addColoredGlassPane(Inventory inv,Player p, String label, int slotID, int colorCode, int id){

        inv.setItem(slotID,addGlassItem(label,Material.STAINED_GLASS_PANE,id));

    }

    public void removeItem(Player p, Material material){
        getInv(p).remove(material);
    }

    public void createItem(Player p, Material material, String label, int slotID){ getInv(p).addItem( addItem(label, material)); }

    public void createBetaItem(Player p, Material material, String label, int slotID){
        getInv(p).addItem( addItem(label + " §e[§4"+betaTag+"§e]" , material)); }

    public void createSoonItem(Player p, Material material, String label, int slotID){
        getInv(p).addItem( addItem(label + " §e[§4"+soonLabel+"§e]", material)); }

    public void createItem(Inventory inv, Player p, Material material, String label, int slotID){
        inv.addItem( addItem(label, material)); }

    private void addItemToInventory(Player p, ItemStack item, int slotID){
        getInv(p).setItem(slotID, item);
    }

    public void createInventoryItem(Player p, Material material, String label, int slotID){
        addItemToInventory(p, addItem(label, material), slotID);
    }

}
