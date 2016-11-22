package highscore;

/**
 * Created by Tobias on 09.11.2016.
 */

import java.sql.*;
import java.util.HashMap;

public class DataAccess {

    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:highscore.db";
        Connection conn = null;
        try {
        	conn = DriverManager.getConnection(url);
            if (conn == null) {
            	createDatabase(conn);
                conn = DriverManager.getConnection(url);
                createTableIfNotExist(conn);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    
    private void createDatabase(Connection conn) {
		try {
			conn.getMetaData();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
    }
    
    private void createTableIfNotExist(Connection conn) {
    	String sql = "CREATE TABLE IF NOT EXISTS Highscore (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	name varchar(50) NOT NULL,\n"
                + "	score integer\n"
                + ");";
		try {
			Statement stmt = conn.createStatement();
			stmt.execute(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
       
    }
    
    public HashMap<String, Integer> getAllHighscores() {
        return selectData("SELECT * FROM Highscore ORDEr BY score DESC");
    }

    public HashMap<String, Integer> getHighscoresByPlayerName(String name) {
        return selectData("SELECT * FROM Highscore WHERE name = '" + name + "' ORDER BY score DESC");
    }

    private HashMap<String, Integer> selectData(String sql){
        HashMap<String, Integer> result = new HashMap<>();

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
