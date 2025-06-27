package mypackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connessione {
    
    private static Connection con;
    
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/universita?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true",
                "root",
                "root"
            );
            System.out.println("Connesso al DB!");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver MySQL non trovato!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Errore di connessione SQL:");
            e.printStackTrace();
        }
    }
    
 // Metodo statico per ottenere la connessione
    public static Connection getCon() {
        return con;
    }
}
