package crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class KoneksiMysql {
    private Connection koneksi;

    public Connection getKoneksi() throws SQLException {
        if (koneksi == null) {
            try {
                String url = "jdbc:mysql://localhost:3306/crud237"; // Update this to your actual database name
                String user = "root"; 
                String password = ""; 
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                koneksi = DriverManager.getConnection(url, user, password);
                System.out.println("Koneksi Berhasil");
            } catch (SQLException e) {
                System.out.println("Koneksi Gagal: " + e.getMessage());
                throw e;
            }
        }
        return koneksi;
    }
}
