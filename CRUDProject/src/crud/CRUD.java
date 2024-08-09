package crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CRUD {
    private Connection conn;

    public CRUD() {
        try {
            KoneksiMysql koneksiMysql = new KoneksiMysql();
            conn = koneksiMysql.getKoneksi();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet tampilData() throws SQLException {
        String sql = "SELECT * FROM siswa";
        PreparedStatement pst = conn.prepareStatement(sql);
        return pst.executeQuery();
    }

    public void simpanData(String id, String nama, String alamat) throws SQLException {
        String sql = "INSERT INTO siswa (id, nama, alamat) VALUES (?, ?, ?)";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, id);
        pst.setString(2, nama);
        pst.setString(3, alamat);
        pst.executeUpdate();
    }

    public void ubahData(String id, String nama, String alamat) throws SQLException {
        String sql = "UPDATE siswa SET nama=?, alamat=? WHERE id=?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, nama);
        pst.setString(2, alamat);
        pst.setString(3, id);
        pst.executeUpdate();
    }

    public void hapusData(String id) throws SQLException {
        String sql = "DELETE FROM siswa WHERE id=?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, id);
        pst.executeUpdate();
    }
}
