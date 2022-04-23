package de.dragonhard.lobby.reader;

import de.dragonhard.lobby.manager.other.ReaderManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class Config {
    static ReaderManager reader_manager = new ReaderManager();
/*
        Config for the Spawn
 */
    protected final static String path_reader_spawn = "plugins/LobbySystem/Data/Spawn";
    protected final static SpawnReader spawn_reader = reader_manager.getSpawnReader();
    protected final static String default_spawn_file = "Spawn";
                    static String spawn_file = "";
                    static boolean use_default_spawn = true;
                    public final static Location spawn = new Location(
                            Bukkit.getWorld(spawn_reader.getString("World")),
                            spawn_reader.getDouble("X"),
                            spawn_reader.getDouble("Y"),
                            spawn_reader.getDouble("Z"),
                            spawn_reader.getfloat("Yaw"),
                            spawn_reader.getfloat("Pitch"));
    /*
            Config for the Warp
     */
    protected final static String path_reader_warp = "plugins/LobbySystem/Data/Player/%pid%/warps";
    protected final static WarpReader warp_reader = reader_manager.getWarpReader();
                    public final static Location warp = new Location(
                            Bukkit.getWorld(warp_reader.getString("World")),
                            warp_reader.getDouble("X"),
                            warp_reader.getDouble("Y"),
                            warp_reader.getDouble("Z"),
                            warp_reader.getfloat("Yaw"),
                            warp_reader.getfloat("Pitch"));

    protected final static String path_reader_config = "plugins/LobbySystem/Data/Player/%pid%/config";
    protected final static String path_reader_plugin_config = "plugins/LobbySystem/Data/Config";
    protected final static String path_reader_warp_list = "plugins/LobbySystem/Data/Player/%pid%/warps";
    protected final static String path_reader_shop = "plugins/LobbySystem/Data/Config/Shop";
    protected final static String path_reader_global_warp = "plugins/LobbySystem/Data/World/Items";
    /*
            under this is not in use
    */
    protected final static String path_reader_language = "plugins/LobbySystem/Data/Language/";
    protected final static String path_reader_group = "plugins/LobbySystem/Data/Player/Groups";
    protected final static String path_reader_message = "plugins/LobbySystem/Data/Message/Server";


}
