package de.dragonhard.lobby.manager.database;

import de.dragonhard.lobby.components.util.Converter;
import de.dragonhard.lobby.manager.Managers;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionManager extends Managers implements Listener {
    private static Connection con;
    private static String mysql = "§cMySQL-System: ";

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        this.getConnectionStateManager().setState(ConnectionState.OFFLINE);
        Player p = e.getPlayer();
        this.getMySqlManager().connect("createRow", p);
    }

    public void createRow(Player p) throws SQLException {
        if (this.getConnectionStateManager().isConnected()) {
            if (isInDatabase(p)) {
                try {
                    PreparedStatement st1 = getCon().prepareStatement("INSERT INTO Users(UUID,PLAYERNAME,LASTLOGIN,WARP_USED,WARP_MAX) VALUES (?,?,?,?,?)");
                    st1.setString(1, p.getUniqueId().toString());
                    st1.setString(2, p.getName());
                    st1.setString(3, this.getDateManager().getCurrentDate());
                    st1.executeUpdate();
                    PreparedStatement st2 = getCon().prepareStatement("INSERT INTO Coins(UUID,COINS) VALUES (?,?)");
                    st2.setString(1, p.getUniqueId().toString());
                    st2.setInt(2,0);
                    st2.executeUpdate();
                    PreparedStatement st3 = getCon().prepareStatement("INSERT INTO Level(UUID,LEVEL) VALUES (?,?)");
                    st3.setString(1, p.getUniqueId().toString());
                    st3.setInt(2,0);
                    st3.executeUpdate();
                    PreparedStatement st4 = getCon().prepareStatement("INSERT INTO Modes(UUID,BUILD,HIDE) VALUES (?,?,?)");
                    st4.setString(1, p.getUniqueId().toString());
                    st4.setInt(2,0);
                    st4.setInt(3,0);
                    st4.executeUpdate();
                    Bukkit.getConsoleSender().sendMessage(mysql + "§aTable des Spielers " + p.getName() + " erfolgreich erstellt.");
                    this.getConnectionStateManager().setState(ConnectionState.OFFLINE);
                } catch (SQLException e) {
                    System.err.print(e.getMessage());
                }
            }
        }
    }

    public int callRowBuildMode(Player p) {
        if (this.getConnectionStateManager().isConnected()) {

            try {

                PreparedStatement st = getCon().prepareStatement("SELECT * FROM Modes WHERE UUID = ?");
                st.setString(1,p.getUniqueId().toString());
                ResultSet rs = st.executeQuery();
                while(rs.next()){
                    return rs.getInt("BUILD");
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return 0;
    }

    public int callRowHideMode(Player p) {
        if (this.getConnectionStateManager().isConnected()) {

            try {

                PreparedStatement st = getCon().prepareStatement("SELECT * FROM Modes WHERE UUID = ?");
                st.setString(1,p.getUniqueId().toString());
                ResultSet rs = st.executeQuery();
                while(rs.next()){
                    return rs.getInt("HIDE");
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return 0;
    }

    public int toggleRowBuildMode(Player p) throws SQLException {
        if (this.getConnectionStateManager().isConnected()) {
            if (isInDatabase(p)) {
                try {

                    PreparedStatement st = getCon().prepareStatement("INSERT INTO Modes(UUID,BUILD,HIDE) VALUES (?,?,?)");
                    st.setString(1, p.getUniqueId().toString());
                    st.setInt(2, callRowBuildMode(p));
                    st.setInt(2, callRowHideMode(p));
                    st.executeUpdate();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            } else {

                try {

                    PreparedStatement st = getCon().prepareStatement("UPDATE Modes SET BUILD = ? WHERE UUID = ?");
                    if(callRowBuildMode(p) == 0){
                        st.setInt(1, 1);
                    }else{
                        st.setInt(1, 0);
                    }
                    st.setString(2, p.getUniqueId().toString());
                    st.executeUpdate();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        }
        return 0;
    }

    public int toggleRowHideMode(Player p) {
        if (this.getConnectionStateManager().isConnected()) {
            if (callRowCoins(p) == 0) {
                try {

                    PreparedStatement st = getCon().prepareStatement("INSERT INTO Modes(UUID,BUILD,HIDE) VALUES (?,?,?)");
                    st.setString(1, p.getUniqueId().toString());
                    st.setInt(2, callRowBuildMode(p));
                    st.setInt(2, callRowHideMode(p));
                    st.executeUpdate();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            } else {

                try {

                    PreparedStatement st = getCon().prepareStatement("UPDATE Modes SET HIDE = ? WHERE UUID = ?");
                    if(callRowBuildMode(p) == 0){
                        st.setInt(1, 1);
                    }else{
                        st.setInt(1, 0);
                    }
                    st.setString(2, p.getUniqueId().toString());
                    st.executeUpdate();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        }
        return 0;
    }

    public String callRowLevel(Player p) {
        if (this.getConnectionStateManager().isConnected()) {

            try {

                PreparedStatement st = getCon().prepareStatement("SELECT * FROM Level WHERE UUID = ?");
                st.setString(1, p.getUniqueId().toString());
                ResultSet rs = st.executeQuery();

                while(rs.next()){
                    return rs.getString("LEVEL");
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return "non";
    }

    public String callRowServer(int slot, String label) {
        if (this.getConnectionStateManager().isConnected()) {

            try {

                PreparedStatement st = getCon().prepareStatement("SELECT * FROM Network WHERE SLOT = ?");
                st.setInt(1,slot);
                ResultSet rs = st.executeQuery();
                while(rs.next()){
                    return rs.getString(label);
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return "non";
    }

    public String setRowServer(String status, String name, String material, String server, int slot) {
        if (this.getConnectionStateManager().isConnected()) {
            if (status.equals("add")) {
                try {

                    PreparedStatement st = getCon().prepareStatement("INSERT INTO Network(NAME,SERVER,MATERIAL,SLOT) VALUES (?,?,?,?)");
                    st.setString(1, name);
                    st.setString(2, server);
                    st.setString(3, material);
                    st.setInt(4, slot);
                    st.executeUpdate();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            } else {

                try {

                    PreparedStatement st1 = getCon().prepareStatement("UPDATE Network SET NAME = ? WHERE SLOT = ?");
                    st1.setString(1, name);
                    st1.setInt(2, slot);
                    st1.executeUpdate();

                    PreparedStatement st2 = getCon().prepareStatement("UPDATE Network SET SERVER = ? WHERE SLOT = ?");
                    st2.setString(1, server);
                    st2.setInt(2, slot);
                    st2.executeUpdate();

                    PreparedStatement st3 = getCon().prepareStatement("UPDATE Network SET MATERIAL = ? WHERE SLOT = ?");
                    st3.setString(1, material);
                    st3.setInt(2, slot);
                    st3.executeUpdate();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        }
        return "";
    }

    public String setRowWarp(Player p) {
        if (this.getConnectionStateManager().isConnected()) {
            if (callRowWarpUsed(p) == 0) {
                try {

                    PreparedStatement st = getCon().prepareStatement("INSERT INTO Users(UUID,PLAYERNAME,LASTLOGIN,WARP_USED,WARP_MAX) VALUES (?,?,?,?,?)");
                    st.setString(1, p.getUniqueId().toString());
                    st.setString(2, p.getName());
                    st.setString(3, this.getDateManager().getCurrentDate());
                    st.setInt(4,0);
                    st.setInt(5,5);
                    st.executeUpdate();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            } else {

                try {

                    PreparedStatement st = getCon().prepareStatement("UPDATE Users SET WARP_USED = ? WHERE UUID = ?");
                    st.setInt(1, callRowWarpUsed(p) + 1);
                    st.setString(2, p.getUniqueId().toString());
                    st.executeUpdate();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        }
        return "";
    }

    public String delRowWarp(Player p) {
        if (this.getConnectionStateManager().isConnected()) {
            if (callRowWarpMax(p) == 0) {
                try {

                    PreparedStatement st = getCon().prepareStatement("INSERT INTO Users(UUID,PLAYERNAME,LASTLOGIN,WARP_USED,WARP_MAX) VALUES (?,?,?,?,?)");
                    st.setString(1, p.getUniqueId().toString());
                    st.setString(2, p.getName());
                    st.setString(3, this.getDateManager().getCurrentDate());
                    st.setInt(4,0);
                    st.setInt(5,5);
                    st.executeUpdate();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            } else {

                try {

                    PreparedStatement st = getCon().prepareStatement("UPDATE Users SET WARP_USED = ? WHERE UUID = ?");
                    st.setInt(1, callRowWarpUsed(p) - 1);
                    st.setString(2, p.getUniqueId().toString());
                    st.executeUpdate();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        }
        return "";
    }

    public int callRowWarpMax(Player p) {
        if (this.getConnectionStateManager().isConnected()) {

            try {
                String uuid = p.getUniqueId().toString();
                PreparedStatement st = getCon().prepareStatement("SELECT * FROM Users WHERE UUID = ?");
                st.setString(1,uuid);
                ResultSet rs = st.executeQuery();
                while(rs.next()){
                    return rs.getInt("WARP_MAX");
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return 0;
    }

    public int callRowWarpUsed(Player p) {
        if (this.getConnectionStateManager().isConnected()) {

            try {
                String uuid = p.getUniqueId().toString();
                PreparedStatement st = getCon().prepareStatement("SELECT * FROM Users WHERE UUID = ?");
                st.setString(1,uuid);
                ResultSet rs = st.executeQuery();
                while(rs.next()){
                    return rs.getInt("WARP_USED");
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return 0;
    }

    public String callRowDate(Player p) {
        if (this.getConnectionStateManager().isConnected()) {

            try {
                String uuid = p.getUniqueId().toString();
                PreparedStatement st = getCon().prepareStatement("SELECT * FROM Users WHERE UUID = ?");
                st.setString(1,uuid);
                ResultSet rs = st.executeQuery();
                while(rs.next()){
                    return rs.getString("LASTLOGIN");
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return "00.00.9999";
    }

    public String setRowDate(Player p) {
        if (this.getConnectionStateManager().isConnected()) {
            if (callRowCoins(p) == 0) {
                try {

                    PreparedStatement st = getCon().prepareStatement("INSERT INTO Users(UUID,PLAYERNAME,LASTLOGIN,WARP_USED,WARP_MAX) VALUES (?,?,?,?,?)");
                    st.setString(1, p.getUniqueId().toString());
                    st.setString(2, p.getName());
                    st.setString(3, this.getDateManager().getCurrentDate());
                    st.setInt(4, callRowWarpUsed(p));
                    st.setInt(5, callRowWarpMax(p));
                    st.executeUpdate();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            } else {

                try {

                    PreparedStatement st = getCon().prepareStatement("UPDATE Users SET LASTLOGIN = ? WHERE UUID = ?");
                    st.setString(1, this.getDateManager().getCurrentDate());
                    st.setString(2, p.getUniqueId().toString());
                    st.executeUpdate();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        }
        return "";
    }

    public int callRowCoins(Player p) {
        if (this.getConnectionStateManager().isConnected()) {

            try {
                String uuid = p.getUniqueId().toString();
                PreparedStatement st = getCon().prepareStatement("SELECT * FROM Coins WHERE UUID = ?");
                st.setString(1,uuid);
                ResultSet rs = st.executeQuery();
                while(rs.next()){
                    return rs.getInt("COINS");
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return 0;
    }

    public int setRowCoins(Player p, int coins) {
        if (this.getConnectionStateManager().isConnected()) {
            if (callRowCoins(p) == 0) {
                try {

                    String uuid = p.getUniqueId().toString();
                    PreparedStatement st = getCon().prepareStatement("INSERT INTO Coins(UUID,COINS) VALUES (?,?)");
                    st.setString(1, uuid);
                    st.setInt(2, coins);
                    st.executeUpdate();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            } else {

                try {
                    String uuid = p.getUniqueId().toString();
                    PreparedStatement st = getCon().prepareStatement("UPDATE Coins SET COINS = ? WHERE UUID = ?");
                    st.setInt(1, coins);
                    st.setString(2, uuid);
                    st.executeUpdate();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        }
        return 0;
    }

    public String callRowBlacklist(Player p) {
        if (this.getConnectionStateManager().isConnected()) {

            try {

                PreparedStatement st = getCon().prepareStatement("SELECT * FROM Blacklist WHERE PLAYERNAME = ?");
                st.setString(1,p.getName());
                ResultSet rs = st.executeQuery();
                while(rs.next()){
                    return rs.getString("UUID");
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return "";
    }

    public int setRowBlacklist(Player p) {
        if (this.getConnectionStateManager().isConnected()) {
            if (callRowCoins(p) == 0) {
                try {

                    PreparedStatement st = getCon().prepareStatement("INSERT INTO Blacklist(UUID,PLAYERNAME) VALUES (?,?)");
                    st.setString(1, p.getUniqueId().toString());
                    st.setString(2, p.getName());
                    st.executeUpdate();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return 0;
    }

    public void createTable() {
        if (this.getConnectionStateManager().isConnected()) {
            try {
                PreparedStatement st1 = getCon().prepareStatement("CREATE TABLE IF NOT EXISTS Users(id INT(6) AUTO_INCREMENT UNIQUE, UUID VARCHAR(255), PLAYERNAME VARCHAR(255), LASTLOGIN VARCHAR(255), WARP_USED INT(255), WARP_MAX INT(255))");
                st1.executeUpdate();

                PreparedStatement st2 = getCon().prepareStatement("CREATE TABLE IF NOT EXISTS Coins(id INT(6) AUTO_INCREMENT UNIQUE, UUID VARCHAR(255), COINS INT(255))");
                st2.executeUpdate();

                PreparedStatement st3 = getCon().prepareStatement("CREATE TABLE IF NOT EXISTS Level(id INT(6) AUTO_INCREMENT UNIQUE, UUID VARCHAR(255), LEVEL INT(255))");
                st3.executeUpdate();

                PreparedStatement st4 = getCon().prepareStatement("CREATE TABLE IF NOT EXISTS Modes(id INT(6) AUTO_INCREMENT UNIQUE, UUID VARCHAR(255), BUILD INT(255), HIDE INT(255))");
                st4.executeUpdate();

                PreparedStatement st5 = getCon().prepareStatement("CREATE TABLE IF NOT EXISTS Blacklist(id INT(6) AUTO_INCREMENT UNIQUE, UUID VARCHAR(255), PLAYERNAME VARCHAR(255))");
                st5.executeUpdate();

                PreparedStatement st6 = getCon().prepareStatement("CREATE TABLE IF NOT EXISTS Network(id INT(6) AUTO_INCREMENT UNIQUE, NAME VARCHAR(255), SERVER VARCHAR(255), MATERIAL VARCHAR(255), SLOT INT(255))");
                st6.executeUpdate();

                Bukkit.getConsoleSender().sendMessage(mysql + "§aTables erfolgreich erstellt.");
            } catch (SQLException e) {
                System.err.print(e.getMessage());
            }
        }
    }


    public ResultSet getResult(String qry) {
        if (this.getConnectionStateManager().isConnected()) {
            try {
                return getCon().createStatement().executeQuery(qry);
            } catch (SQLException e) {
                System.err.print(e.getMessage());
            }
        }
        return null;
    }

    public static void setConnection(Connection connection) {
        con = connection;
    }
    public static Connection getCon() {
        return con;
    }

    public static void closeConnection() throws SQLException {
       if(getCon() != null){getCon().close();}
    }
    public static boolean isInDatabase(Player p) throws SQLException {
        PreparedStatement st = getCon().prepareStatement("SELECT UUID FROM Users WHERE UUID = ?");
        st.setString(1, p.getUniqueId().toString());
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            return false;
        }
        PreparedStatement st1 = getCon().prepareStatement("SELECT UUID FROM Coins WHERE UUID = ?");
        st1.setString(1, p.getUniqueId().toString());
        ResultSet rs1 = st1.executeQuery();
        while (rs1.next()) {
            return false;
        }
        PreparedStatement st2 = getCon().prepareStatement("SELECT UUID FROM Level WHERE UUID = ?");
        st2.setString(1, p.getUniqueId().toString());
        ResultSet rs2 = st2.executeQuery();
        while (rs2.next()) {
            return false;
        }

        PreparedStatement st3 = getCon().prepareStatement("SELECT UUID FROM Modes WHERE UUID = ?");
        st3.setString(1, p.getUniqueId().toString());
        ResultSet rs3 = st3.executeQuery();
        while (rs3.next()) {
            return false;
        }
        return true;
    }
}
