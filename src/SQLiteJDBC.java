/**
 * Created by jajaj on 09.11.2016.
 */
import java.sql.*;

public class SQLiteJDBC {
    public static void main( String args[] )
    {
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:highscore.db");
            Statement stm = c.createStatement();
            stm.execute("");

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }
}
