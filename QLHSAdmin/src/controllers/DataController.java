/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Computer
 */
public class DataController {

    private static DataController instance;

    private DataController() {
    }

    public static synchronized DataController getInstance() {
        if (instance == null) {
            instance = new DataController();
        }
        return instance;
    }

    public final String database = "QUANLIHOCSINH";

    public Connection ConnectDatabase() {
        System.out.println("");

        Connection connection = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            String url = "jdbc:sqlserver://Localhost:1433;databaseName=" + database;
            String Username = "sa";
            String Password = "1234";

            connection = DriverManager.getConnection(url, Username, Password);

            Logger.getLogger(DataController.class.getName()).log(Level.INFO, "Connect database successfull!, database: " + database);

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DataController.class.getName()).log(Level.SEVERE, "Can't connect database!, database: " + database, ex);
        }

        return connection;
    }

    public void DisconnectDatabase(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(DataController.class.getName()).log(Level.SEVERE, "Disconnect database error!, database: " + database, ex);
                return;
            }

            Logger.getLogger(DataController.class.getName()).log(Level.INFO, "Disconnect database successfull!, database: " + database);
        }
    }

    public int ExecuteUpdate(String query) {
        int i = 0;

        Connection connection = null;

        try {
            connection = ConnectDatabase();

            Logger.getLogger(DataController.class.getName()).log(Level.INFO, query);

            i = connection.createStatement().executeUpdate(query);
        } catch (SQLException e) {
            Logger.getLogger(DataController.class.getName()).log(Level.SEVERE, "Can't execute update!", e);
        }

        DisconnectDatabase(connection);
        return i;
    }

    public ResultSet ExecuteQuery(Connection connection, String query) {
        ResultSet rs = null;

        if (query != null) {
            if (query.isEmpty() == false) {
                try {
                    Logger.getLogger(DataController.class.getName()).log(Level.INFO, query);

                    rs = connection.createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_READ_ONLY)
                            .executeQuery(query);
                } catch (SQLException e) {
                    Logger.getLogger(DataController.class.getName()).log(Level.SEVERE, "Can't execute query!", e);
                }
            }
        }

        return rs;
    }

    private PreparedStatement MatchingStatement(PreparedStatement pre_statment, Object... stats) throws Exception {
        int n = stats.length;
        for (int i = 0; i < n; i++) {
            if (stats[i] instanceof String) {
                pre_statment.setString(i + 1, (String) stats[i]);
            } else if (stats[i] instanceof Integer) {
                pre_statment.setInt(i + 1, (Integer) stats[i]);
            } else if (stats[i] instanceof Date) {
                pre_statment.setString(i + 1, new SimpleDateFormat("yyyy-MM-dd").format(stats[i]));
            } else {
                pre_statment.setString(i + 1, stats[i].toString());
            }
        }
        return pre_statment;
    }

    public int ExecuteUpdateParameter(String query, Object... stats) {
        int number_row_success = 0;

        try {
            Connection connection = ConnectDatabase();
            PreparedStatement pre_statment = connection.prepareStatement(query);

            MatchingStatement(pre_statment, stats);

            Logger.getLogger(DataController.class.getName()).log(Level.INFO, query);

            number_row_success = pre_statment.executeUpdate();
            DisconnectDatabase(connection);
        } catch (Exception e) {
            Logger.getLogger(DataController.class.getName()).log(Level.SEVERE, "Can't execute update with parametter!", e);
        }

        return number_row_success;
    }

    public ResultSet ExecuteQueryParametter(Connection connection, String query, Object... stats) {
        ResultSet rs = null;

        try {
            PreparedStatement pre_statment = connection.prepareStatement(query);

            MatchingStatement(pre_statment, stats);

            Logger.getLogger(DataController.class.getName()).log(Level.INFO, query);

            rs = pre_statment.executeQuery();
        } catch (Exception e) {
            Logger.getLogger(DataController.class.getName()).log(Level.SEVERE, "Can't execute query with parametter!", e);
        }

        return rs;
    }
}
