package de.dragonhard.lobby.manager.database;

import de.dragonhard.lobby.manager.ConfigManager;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager extends ConfigManager {
    private static ConfigManager cm = new ConfigManager();
    private static Enum currentState = ConnectionState.OFFLINE;

    public void setState(Enum state){
        currentState = state;
    }

    public Enum getState(){
        return currentState;
    }


    private void connect(){
        setState(ConnectionState.CONNECTING);
        try (Connection conn = DriverManager.getConnection(this.getHost_url(), this.getUsername(), this.getPassword())) {
            Class.forName("com.mysql.jdbc.Driver");
            if (conn != null) {
                //erfolgreich
                Bukkit.getConsoleSender().sendMessage("[DSE] §4MySQL Verbindung erfolgreich hergestellt.");
                setState(ConnectionState.CONNECTION_ESTABLISHED);
            } else {
                //nicht erfolgreich
                Bukkit.getConsoleSender().sendMessage("[DSE] §4MySQL Verbindung konnte nicht aufgebaut werden.");
                setState(ConnectionState.CONNECTION_ERROR);
            }

        } catch (SQLException ex) {
            setState(ConnectionState.CONNECTION_ERROR);
            Bukkit.getConsoleSender().sendMessage("§eServer-status: §4"+ ex.getSQLState());
            Bukkit.getConsoleSender().sendMessage("§eError: §4" + ex.toString());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }




}
