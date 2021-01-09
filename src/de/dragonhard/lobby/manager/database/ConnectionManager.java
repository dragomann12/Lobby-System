package de.dragonhard.lobby.manager.database;

import de.dragonhard.lobby.manager.AcessManager;
import de.dragonhard.lobby.manager.ConfigManager;
import de.dragonhard.lobby.manager.DateManager;
import de.dragonhard.lobby.manager.PlayerConfigManager;
import io.netty.handler.timeout.TimeoutException;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.*;

public class ConnectionManager extends ConfigManager {
    private static ConfigManager cm = new ConfigManager();
    private static Enum currentState = ConnectionState.OFFLINE;
    private static DateManager dm = new DateManager();
    private static PlayerConfigManager pm = new PlayerConfigManager();
    private static AcessManager am = new AcessManager();

    public void setState(Enum state){
        currentState = state;
    }

    public Enum getState(){
        return currentState;
    }

    public boolean checkConfig(){
        if(!cm.getHost().equals("") && !cm.getPort().equals("") && !cm.getDatabase().equals("") && !cm.getUsername().equals("") && !cm.getPassword().equals("")){return true;}
        return false;

    }

    public Boolean convertIntegerToBoolean(int value){
        return value >= 1;
    }

    public int convertBooleanToInteger(Boolean bool){
        return bool ? 1 : 0;
    }

    public int getCoins(Player p){
        if(!checkConfig()){Bukkit.getConsoleSender().sendMessage(cm.getTag() + "§4Error information required please check the config_main and fill all mysql stuff"); return 0;}

        if(!currentState.equals(ConnectionState.CONNECTION_ESTABLISHED)) { Bukkit.getConsoleSender().sendMessage(cm.getTag() + "§etry to connect with MySQL Database!  §b[§5"+ cm.getDatabase() +"§b]" );
            setState(ConnectionState.CONNECTING);}

        try (Connection conn = DriverManager.getConnection(this.getHost_url(), this.getUsername(), this.getPassword())) {
            Class.forName("com.mysql.jdbc.Driver");
            if (conn != null) {
                //erfolgreich
                if (!currentState.equals(ConnectionState.CONNECTION_ESTABLISHED)) {
                    Bukkit.getConsoleSender().sendMessage(cm.getTag() + "§aConnected with MySQL Database!  §b[§5" + cm.getDatabase() + "§b]");

                    setState(ConnectionState.CONNECTION_ESTABLISHED);
                }
                //uuid VARCHAR(36), username VARCHAR(35),login DATE, coins INTEGER(8), Level INTEGER(8), buildmode INTEGER(0), blacklisted INTEGER(0), hide INTEGER(0))
                Bukkit.getConsoleSender().sendMessage(cm.getTag() + "§esending request to the Server!  §b[§5" + cm.getDatabase() + "§b]");
                ResultSet rset = conn.createStatement().executeQuery("SELECT * FROM LobbySystem WHERE uuid = '" + p.getUniqueId() + "'");
                Bukkit.getConsoleSender().sendMessage(cm.getTag() + "§arequest send to the Server!  §b[§5" + cm.getDatabase() + "§b]");
                return rset.getInt("coins");

            } else {

                //nicht erfolgreich
                setState(ConnectionState.CONNECTION_ERROR);
                Bukkit.getConsoleSender().sendMessage(cm.getTag() + "§4Error can't keep the connection up!  §b[§5" + cm.getDatabase() + "§b]");
            }

            setState(ConnectionState.OFFLINE);
            Bukkit.getConsoleSender().sendMessage(cm.getTag() + "§4closed the connection with MySQL Database!  §b[§5" + cm.getDatabase() + "§b]");

        } catch (SQLException ex) {
            switch(ex.getSQLState()){
                case "42S01":
                    setState(ConnectionState.OFFLINE);
                    Bukkit.getConsoleSender().sendMessage(cm.getTag() + "§eInformation the request was send but has no affect at the Database!  §b[§5" + cm.getDatabase() + "§b]");
                    Bukkit.getConsoleSender().sendMessage(cm.getTag() + "§4closed the connection with MySQL Database!  §b[§5" + cm.getDatabase() + "§b]");break;
                default:
                    setState(ConnectionState.CONNECTION_ERROR);
                    Bukkit.getConsoleSender().sendMessage("§eServer-status: §4" + ex.getSQLState());
                    Bukkit.getConsoleSender().sendMessage("§eError: §4" + ex.toString());break;
            }

        }catch (TimeoutException ex){
            setState(ConnectionState.CONNECTION_ERROR);
            Bukkit.getConsoleSender().sendMessage(cm.getTag() + "§e[§4Timeout§e] §4unable to handle the connection! §e(§4too high ping§e)  §b[§5"+ cm.getDatabase() +"§b]" );
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
            return 0;
    }

    public ResultSet getResult(Connection conn, String qry){

        try{
            return conn.createStatement().executeQuery(qry);
        }catch (SQLException ex){
            return null;
        }


    }

    public void addPlayerToDb(Player p){
        if(!checkConfig()){Bukkit.getConsoleSender().sendMessage(cm.getTag() + "§4Error information required please check the config_main and fill all mysql stuff"); return;}

        if(!currentState.equals(ConnectionState.CONNECTION_ESTABLISHED)) { Bukkit.getConsoleSender().sendMessage(cm.getTag() + "§etry to connect with MySQL Database!  §b[§5"+ cm.getDatabase() +"§b]" );
            setState(ConnectionState.CONNECTING);}

        try (Connection conn = DriverManager.getConnection(this.getHost_url(), this.getUsername(), this.getPassword())) {
            Class.forName("com.mysql.jdbc.Driver");
            if (conn != null) {
                //erfolgreich
                if (!currentState.equals(ConnectionState.CONNECTION_ESTABLISHED)) {
                    Bukkit.getConsoleSender().sendMessage(cm.getTag() + "§aConnected with MySQL Database!  §b[§5" + cm.getDatabase() + "§b]");

                    setState(ConnectionState.CONNECTION_ESTABLISHED);
                }
                //uuid VARCHAR(36), username VARCHAR(35),login DATE, coins INTEGER(8), Level INTEGER(8), buildmode INTEGER(0), blacklisted INTEGER(0), hide INTEGER(0))
                Bukkit.getConsoleSender().sendMessage(cm.getTag() + "§esending request to the Server!  §b[§5" + cm.getDatabase() + "§b]");
                PreparedStatement st1 = conn.prepareStatement("INSERT INTO LobbySystem (uuid,username,login,coins,Level,buildmode,blacklisted,hide) VALUES (?,?,?,?,?,?,?,?)");
                st1.setString(1,p.getUniqueId().toString());
                st1.setString(2,p.getName());
                st1.setString(3,dm.getPlayerDate(p));
                st1.setInt(4,pm.getCoins(p));
                st1.setInt(5,pm.getAccessLevel(p));
                st1.setInt(6,0);
                st1.setInt(7,0);
                st1.setInt(8,0);
                st1.executeUpdate();
                Bukkit.getConsoleSender().sendMessage(cm.getTag() + "§arequest send to the Server!  §b[§5" + cm.getDatabase() + "§b]");
            } else {

                //nicht erfolgreich
                setState(ConnectionState.CONNECTION_ERROR);
                Bukkit.getConsoleSender().sendMessage(cm.getTag() + "§4Error can't keep the connection up!  §b[§5" + cm.getDatabase() + "§b]");
            }

            setState(ConnectionState.OFFLINE);
            Bukkit.getConsoleSender().sendMessage(cm.getTag() + "§4closed the connection with MySQL Database!  §b[§5" + cm.getDatabase() + "§b]");

        } catch (SQLException ex) {
            switch(ex.getSQLState()){
                case "42S01":
                    setState(ConnectionState.OFFLINE);
                    Bukkit.getConsoleSender().sendMessage(cm.getTag() + "§eInformation the request was send but has no affect at the Database!  §b[§5" + cm.getDatabase() + "§b]");
                    Bukkit.getConsoleSender().sendMessage(cm.getTag() + "§4closed the connection with MySQL Database!  §b[§5" + cm.getDatabase() + "§b]");break;
                default:
                    setState(ConnectionState.CONNECTION_ERROR);
                    Bukkit.getConsoleSender().sendMessage("§eServer-status: §4" + ex.getSQLState());
                    Bukkit.getConsoleSender().sendMessage("§eError: §4" + ex.toString());break;
            }

        }catch (TimeoutException ex){
            setState(ConnectionState.CONNECTION_ERROR);
            Bukkit.getConsoleSender().sendMessage(cm.getTag() + "§e[§4Timeout§e] §4unable to handle the connection! §e(§4too high ping§e)  §b[§5"+ cm.getDatabase() +"§b]" );
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void createTable(){
        if(!checkConfig()){Bukkit.getConsoleSender().sendMessage(cm.getTag() + "§4Error information required please check the config_main and fill all mysql stuff"); return;}

        if(!currentState.equals(ConnectionState.CONNECTION_ESTABLISHED)) { Bukkit.getConsoleSender().sendMessage(cm.getTag() + "§etry to connect with MySQL Database!  §b[§5"+ cm.getDatabase() +"§b]" );
        setState(ConnectionState.CONNECTING);}

        try (Connection conn = DriverManager.getConnection(this.getHost_url(), this.getUsername(), this.getPassword())) {
            Class.forName("com.mysql.jdbc.Driver");
            if (conn != null) {
                //erfolgreich
                if (!currentState.equals(ConnectionState.CONNECTION_ESTABLISHED)) {
                    Bukkit.getConsoleSender().sendMessage(cm.getTag() + "§aConnected with MySQL Database!  §b[§5" + cm.getDatabase() + "§b]");

                    setState(ConnectionState.CONNECTION_ESTABLISHED);
                }
                Bukkit.getConsoleSender().sendMessage(cm.getTag() + "§esending request to the Server!  §b[§5" + cm.getDatabase() + "§b]");
                PreparedStatement st1 = conn.prepareStatement("CREATE TABLE LobbySystem (id INT(6) AUTO_INCREMENT UNIQUE, uuid VARCHAR(36), username VARCHAR(35),login VARCHAR(255), coins INTEGER(8), Level INTEGER(8), buildmode INTEGER(0), blacklisted INTEGER(0), hide INTEGER(0));");
                st1.executeUpdate();
                Bukkit.getConsoleSender().sendMessage(cm.getTag() + "§arequest send to the Server!  §b[§5" + cm.getDatabase() + "§b]");
            } else {

                //nicht erfolgreich
                setState(ConnectionState.CONNECTION_ERROR);
                Bukkit.getConsoleSender().sendMessage(cm.getTag() + "§4Error can't keep the connection up!  §b[§5" + cm.getDatabase() + "§b]");
            }

            setState(ConnectionState.OFFLINE);
            Bukkit.getConsoleSender().sendMessage(cm.getTag() + "§4closed the connection with MySQL Database!  §b[§5" + cm.getDatabase() + "§b]");

        } catch (SQLException ex) {
            switch(ex.getSQLState()){
                case "42S01":
                    setState(ConnectionState.OFFLINE);
                    Bukkit.getConsoleSender().sendMessage(cm.getTag() + "§eInformation the request was send but has no affect at the Database!  §b[§5" + cm.getDatabase() + "§b]");
                    Bukkit.getConsoleSender().sendMessage(cm.getTag() + "§4closed the connection with MySQL Database!  §b[§5" + cm.getDatabase() + "§b]");break;
                default:
                    setState(ConnectionState.CONNECTION_ERROR);
                    Bukkit.getConsoleSender().sendMessage("§eServer-status: §4" + ex.getSQLState());
                    Bukkit.getConsoleSender().sendMessage("§eError: §4" + ex.toString());break;
            }

        }catch (TimeoutException ex){
            setState(ConnectionState.CONNECTION_ERROR);
            Bukkit.getConsoleSender().sendMessage(cm.getTag() + "§e[§4Timeout§e] §4unable to handle the connection! §e(§4too high ping§e)  §b[§5"+ cm.getDatabase() +"§b]" );
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void connect(){
        if(!checkConfig()){Bukkit.getConsoleSender().sendMessage(cm.getTag() + "§4Error information required please check the config_main and fill all mysql stuff"); return;}
        setState(ConnectionState.CONNECTING);
        Bukkit.getConsoleSender().sendMessage(cm.getTag() + "§etry to connect with MySQL Database!  §b[§5"+ cm.getDatabase() +"§b]" );
        try (Connection conn = DriverManager.getConnection(this.getHost_url(), this.getUsername(), this.getPassword())) {
            Class.forName("com.mysql.jdbc.Driver");

            if (conn != null) {
                //erfolgreich
                setState(ConnectionState.CONNECTION_ESTABLISHED);
                Bukkit.getConsoleSender().sendMessage(cm.getTag() + "§aConnected with MySQL Database!  §b[§5"+ cm.getDatabase() +"§b]" );
                createTable(); return;
            } else {
                //nicht erfolgreich
                setState(ConnectionState.CONNECTION_ERROR);
                Bukkit.getConsoleSender().sendMessage(cm.getTag() + "§4unable to connect with MySQL Database!  §b[§5"+ cm.getDatabase() +"§b]" );
            }

            setState(ConnectionState.OFFLINE);
            Bukkit.getConsoleSender().sendMessage(cm.getTag() + "§4closed the connection with MySQL Database!  §b[§5"+ cm.getDatabase() +"§b]" );

        } catch (SQLClientInfoException ex){
            setState(ConnectionState.CONNECTION_REFUSED);
            Bukkit.getConsoleSender().sendMessage(cm.getTag() + "§e[§4Login§e] §4wrong login information please check the config_main file and update the mysql information! §b[§5"+ cm.getDatabase() +"§b]" );
        } catch (SQLException ex) {
            setState(ConnectionState.CONNECTION_ERROR);
            Bukkit.getConsoleSender().sendMessage("§eServer-status: §4"+ ex.getSQLState());
            Bukkit.getConsoleSender().sendMessage("§eError: §4" + ex.toString());
        }catch (TimeoutException ex){
            setState(ConnectionState.CONNECTION_ERROR);
            Bukkit.getConsoleSender().sendMessage(cm.getTag() + "§e[§4Timeout§e] §4unable to handle the connection! §e(§4too high ping§e)  §b[§5"+ cm.getDatabase() +"§b]" );
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }




}
