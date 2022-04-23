package de.dragonhard.lobby.manager.other;

import de.dragonhard.lobby.components.menu.Game_Menu;
import de.dragonhard.lobby.components.menu.Lobby_Menu;
import de.dragonhard.lobby.components.menu.admin.Admin_External_Menu;
import de.dragonhard.lobby.components.menu.admin.Admin_Menu;
import de.dragonhard.lobby.components.menu.admin.Admin_Server_Menu;
import de.dragonhard.lobby.components.menu.friend.Friend_Menu;
import de.dragonhard.lobby.components.menu.player.Player_Menu;
import de.dragonhard.lobby.components.menu.shop.Shop_Coin_Menu;
import de.dragonhard.lobby.components.menu.shop.Shop_Menu;

public class MenuManager {

    public Lobby_Menu getLobbyMenu(){
        return new Lobby_Menu();
    }

    public Game_Menu getGameMenu(){
        return new Game_Menu();
    }

    public Shop_Menu getShopMenu(){
        return new Shop_Menu();
    }

    public Shop_Coin_Menu getCoinShopMenu(){
        return new Shop_Coin_Menu();
    }

    public Player_Menu getPlayerMenu(){
        return new Player_Menu();
    }

    public Friend_Menu getFriendMenu(){
        return new Friend_Menu();
    }

    public Admin_Menu getAdminMenu(){
        return new Admin_Menu();
    }

    public Admin_Server_Menu getAdminServerMenu(){
        return new Admin_Server_Menu();
    }

    public Admin_External_Menu getAdminExternalMenu(){
        return new Admin_External_Menu();
    }

}
