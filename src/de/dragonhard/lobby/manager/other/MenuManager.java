package de.dragonhard.lobby.manager.other;

import de.dragonhard.lobby.components.ConsoleWriter;
import de.dragonhard.lobby.components.menu.Menu;
import org.bukkit.Color;

import java.util.HashMap;

public class MenuManager {
    private static final HashMap<String, Menu> menu_list = new HashMap<>();


    public static void addMenu(String id, Menu menu) {
        if (menu_list.containsKey(id)) {
            ConsoleWriter.write(Color.RED + "[ERROR] menu id " + Color.YELLOW + id + Color.RED + " already exists!"); return;
        }

        menu_list.put(id,menu);

    }

    public static Menu getMenu(String id){
        if (!menu_list.containsKey(id)) {
            return null;
        }
        return menu_list.get(id);
    }
}
