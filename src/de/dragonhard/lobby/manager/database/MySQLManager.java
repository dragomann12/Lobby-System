package de.dragonhard.lobby.manager.database;

import de.dragonhard.lobby.manager.Managers;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLManager extends Managers {

    private static String mysql = "§cMySQL-System:";

    public String getHost_url() {
        return "jdbc:mysql://" + getHost() + ":" + getPort() + "/" + getDatabase();
    }

    private String getHost() {
        if (this.getConfigManager().getString("MySQL.Host").equals("")) {
            return "keine eingabe";
        }
        return this.getConfigManager().getString("MySQL.Host");
    }

    private String getPort() {
        if (this.getConfigManager().getString("MySQL.Port").equals("")) {
            return "keine eingabe";
        }
        return this.getConfigManager().getString("MySQL.Port");
    }

    private String getDatabase() {
        if (this.getConfigManager().getString("MySQL.Database").equals("")) {
            return "keine eingabe";
        }
        return this.getConfigManager().getString("MySQL.Database");
    }

    public String getUsername() {
        if (this.getConfigManager().getString("MySQL.Username").equals("")) {
            return "keine eingabe";
        }
        return this.getConfigManager().getString("MySQL.Username");
    }

    public String getPassword() {
        if (this.getConfigManager().getString("MySQL.Password").equals("")) {
            return "keine eingabe";
        }
        return this.getConfigManager().getString("MySQL.Password");
    }

    public ResultSet getResult(Connection conn, String qry) {

        try {
            return conn.createStatement().executeQuery(qry);
        } catch (SQLException ex) {
            return null;
        }
    }

    public void connect(String op, Player p) {
        this.getConnectionStateManager().setState(ConnectionState.CONNECTING);

        String username = this.getConfigManager().getUsername();
        String password = this.getConfigManager().getPassword();
        try {
                Connection conn = DriverManager.getConnection(this.getConfigManager().getHost_url() + "?autoReconnect=true", username, password);
                ConnectionManager.setConnection(conn);
                Class.forName("com.mysql.jdbc.Driver");

                this.getConnectionStateManager().setState(ConnectionState.CONNECTION_ESTABLISHED);

                if (op.equals("createTable")) {
                    this.getConnectionManager().createTable();
                }
                if (op.equals("createRow")) {
                    this.getConnectionManager().createRow(p);
                }
                if (op.equals("callRowCoins")) {
                    this.getConnectionManager().callRowCoins(p);
                }
                if (op.equals("setRowCoins")) {
                this.getConnectionManager().setRowCoins(p,this.getConnectionManager().callRowCoins(p));
                }
                if (op.equals("callRowDate")) {
                this.getConnectionManager().callRowDate(p);
                }
                if (op.equals("setRowDate")) {
                this.getConnectionManager().setRowDate(p);
                }
                if (op.equals("callRowBuild")) {
                this.getConnectionManager().callRowBuildMode(p);
                }
                if (op.equals("callRowHide")) {
                this.getConnectionManager().callRowHideMode(p);
                }
                if (op.equals("callRowNetwork")) {
                this.getConnectionManager().callRowServer(0,"edit");
                }
                if(op.equals("setRowBlacklist")){
                    this.getConnectionManager().setRowBlacklist(p);
                }
                if (op.equals("callRowBlacklist")) {
                this.getConnectionManager().callRowBlacklist(p);
                }

                if (op.equals("setRowWarp")) {
              //  this.getConnectionManager().setRowWarp(p);
                }
                if(op.equals("callRowWarpUsed")) {
              //  this.getConnectionManager().callRowWarpUsed(p);
                }

            if (op.equals("delRowWarp")) {
              //  this.getConnectionManager().delRowWarp(p);
            }



        } catch (SQLException ex) {
            this.getConnectionStateManager().setState(ConnectionState.CONNECTION_ERROR);
            Bukkit.getConsoleSender().sendMessage("§eServer-status: §4" + ex.getSQLState());
            Bukkit.getConsoleSender().sendMessage("§eError: §4" + ex.toString());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String connect(String op, String name, int slot, Player p) {
        this.getConnectionStateManager().setState(ConnectionState.CONNECTING);

        String username = this.getConfigManager().getUsername();
        String password = this.getConfigManager().getPassword();
        try {
            Connection conn = DriverManager.getConnection(this.getConfigManager().getHost_url() + "?autoReconnect=true", username, password);
            ConnectionManager.setConnection(conn);
            Class.forName("com.mysql.jdbc.Driver");

            this.getConnectionStateManager().setState(ConnectionState.CONNECTION_ESTABLISHED);

            if(op.equals("callRowServer")) {
              return this.getConnectionManager().callRowServer(slot,name);
            }

        } catch (SQLException ex) {
            this.getConnectionStateManager().setState(ConnectionState.CONNECTION_ERROR);
            Bukkit.getConsoleSender().sendMessage("§eServer-status: §4" + ex.getSQLState());
            Bukkit.getConsoleSender().sendMessage("§eError: §4" + ex.toString());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    public void connect(String op, String name, String server, String material, int slot, String status, Player p) {
        this.getConnectionStateManager().setState(ConnectionState.CONNECTING);

        String username = this.getConfigManager().getUsername();
        String password = this.getConfigManager().getPassword();
        try {
            Connection conn = DriverManager.getConnection(this.getConfigManager().getHost_url() + "?autoReconnect=true", username, password);
            ConnectionManager.setConnection(conn);
            Class.forName("com.mysql.jdbc.Driver");

            this.getConnectionStateManager().setState(ConnectionState.CONNECTION_ESTABLISHED);

            if (op.equals("setRowServer")) {
                this.getConnectionManager().setRowServer(status,name,material,server,slot);
            }

        } catch (SQLException ex) {
            this.getConnectionStateManager().setState(ConnectionState.CONNECTION_ERROR);
            Bukkit.getConsoleSender().sendMessage("§eServer-status: §4" + ex.getSQLState());
            Bukkit.getConsoleSender().sendMessage("§eError: §4" + ex.toString());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
