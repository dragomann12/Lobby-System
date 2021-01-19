package de.dragonhard.lobby.components.util;

import de.dragonhard.lobby.manager.Managers;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class InventorySetter extends Managers {

    public void getHotbarItems(Player p){
        this.getInventoryManager().createItem(p, Material.getMaterial(this.getConfigManager().getHotbarMaterial("Lobby")),this.getConfigManager().getHotbarTitleColor("Lobby") + this.getConfigManager().getHotbarTitle("Lobby"),0);
        this.getInventoryManager().createItem(p, Material.getMaterial(this.getConfigManager().getHotbarMaterial("shop")),this.getConfigManager().getHotbarTitleColor("shop") + this.getConfigManager().getHotbarTitle("shop"),1);
        this.getInventoryManager().createItem(p, Material.STAINED_GLASS_PANE,"§4 ",2);

        if(this.getPluginWhitelistManager().isAdmin(p) || this.getPluginWhitelistManager().isOwner(p) || this.getPluginWhitelistManager().isPluginDeveloper(p)){

            this.getInventoryManager().createItem(p, Material.getMaterial(this.getConfigManager().getHotbarMaterial("Admin")),this.getConfigManager().getHotbarTitleColor("Admin") + this.getConfigManager().getHotbarTitle("Admin"),3);
        }else{

            this.getInventoryManager().createItem(p, Material.STAINED_GLASS_PANE,"§3 ",3);
        }


        this.getInventoryManager().createItem(p, Material.getMaterial(this.getConfigManager().getHotbarMaterial("Game")),this.getConfigManager().getHotbarTitleColor("Game") + this.getConfigManager().getHotbarTitle("Game"),4);

        if (this.getPluginWhitelistManager().isOwner(p) || this.getPluginWhitelistManager().isPluginDeveloper(p)) {

            this.getInventoryManager().createItem(p, Material.getMaterial(this.getConfigManager().getHotbarMaterial("Settings")), this.getConfigManager().getHotbarTitleColor("Settings") + this.getConfigManager().getHotbarTitle("Settings"), 5);
        } else {

            this.getInventoryManager().createItem(p, Material.STAINED_GLASS_PANE, "§2 ", 5);
        }

        this.getInventoryManager().createItem(p, Material.STAINED_GLASS_PANE,"§1 ",6);

        if(this.getPluginWhitelistManager().isPluginDeveloper(p)){
            this.getInventoryManager().createItem(p, this.getPlayerManager().getHideStatusMaterial(p),"§5Spieler verstecken",7);
        } else{
            this.getInventoryManager().createItem(p, Material.BARRIER,"§5Spieler verstecken §e[§4gesperrt§e]",7);
        }

        this.getInventoryManager().addSkull(p,"§5" + this.getConfigManager().getHotbarTitle("Player"),this.getConfigManager().getHotbarTitleColor("Player"),8);

    }

}
