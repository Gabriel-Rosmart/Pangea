package com.backend;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;

public class Connector {

    private final String DB_URL;
    private final String DB_USER;
    private final String PASSWORD;
    private final String PLOT_TABLE;
    private final String RECORD_TABLE;


    private Connection conn;
    private String query;
    private final HistoryTree ht;


    public Connector(HistoryTree ht) throws IOException {
        //Path configPath = Path.of("src" + File.separator + "Configs" + File.separator + "db_config.json");
        Path configPath = Path.of("Config" + File.separator + "database" + File.separator + "db_config.json");
        String content = Files.readString(configPath);

        JSONObject obj = new JSONObject(content);
        this.DB_URL = obj.getString("db_url");
        this.DB_USER = obj.getString("db_user");
        this.PASSWORD = obj.getString("db_password");
        this.PLOT_TABLE = obj.getString("db_plot_table");
        this.RECORD_TABLE = obj.getString("db_record_table");

        this.ht = ht;
    }

    public void initConnector() throws SQLException{
        this.conn = DriverManager.getConnection(this.DB_URL, this.DB_USER, this.PASSWORD);
        this.query = "SELECT * FROM " + this.PLOT_TABLE + " WHERE ID = ?";
    }

    public Result fetchHistory() throws SQLException{
        PreparedStatement ps = conn.prepareStatement(this.query);
        ps.setInt(1, this.ht.getNextNode());
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            this.ht.update(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5),
                    rs.getInt(6), rs.getString(7));
            return Result.OK;
        }
        else{
            return Result.ERR;
        }
    }

    public void insertRecord(String username, String chr_type, int points) throws SQLException {
        String insertQuery = "INSERT INTO " + this.RECORD_TABLE + "(USERNAME, CHARACTER_TYPE, POINTS) VALUES(?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(insertQuery);
        ps.setString(1, username);
        ps.setString(2, chr_type);
        ps.setInt(3, points);
        ps.execute();
    }
}
