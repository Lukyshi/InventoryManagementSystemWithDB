import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException; // connecting data base

public class DATABASE_Inventory {

    private static final String url = "jdbc:mysql://localhost:3306/db_inventory_db";
    private static final String root = "root";
    private static final String password = "Luiz010124";

    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(url, root, password);
        } catch (SQLException e) {
            System.out.println("database connection failed " + e.getMessage());
            return null;
        }
    }


}
