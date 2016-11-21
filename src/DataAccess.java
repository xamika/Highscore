/**
 * Created by jajaj on 09.11.2016.
 */
import org.sqlite.SQLiteConfig;

import java.sql.*;
import java.util.HashMap;
import java.util.HashSet;

public class DataAccess {

    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:highscore.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public HashMap<String, Integer> getAllhighscores() {
        return selectData("SELECT * FROM Highscore");
    }

    public HashMap<String, Integer> getHighscoresByPlayerName(String name) {
        return selectData("SELECT * FROM Highscore WHERE name = '" + name + "'");
    }


    private HashMap<String, Integer> selectData(String sql){
        HashMap result = new HashMap();

        try (Connection conn = connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                result.put(rs.getString("name"), rs.getInt("score"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public void insertData(String playerName, Integer score) {
        try
        {
            Connection conn = connect();
            Statement stmt  = conn.createStatement();
            stmt.executeQuery("INSERT INTO highscore (name, score) VALUES ('" +
                    playerName + "', " + score + ")");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateData(String playerName, Integer score) {
        try
        {
            Connection conn = connect();
            Statement stmt  = conn.createStatement();
            stmt.executeQuery("UPDATE highscore SET score=" + score +
                    " WHERE name='" + playerName + "'");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
