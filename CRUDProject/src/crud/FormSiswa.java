package crud;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FormSiswa extends JFrame {

    private JPanel contentPane;
    private JTextField txt_id;
    private JTextField txt_nama;
    private JTextField txt_alamat;
    private JTable tabel_siswa;
    private DefaultTableModel tabmode;
    private CRUD aa = new CRUD();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FormSiswa frame = new FormSiswa();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public FormSiswa() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 11, 414, 139);
        contentPane.add(scrollPane);
        
        tabel_siswa = new JTable();
        scrollPane.setViewportView(tabel_siswa);
        tabel_siswa.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {"ID", "Nama", "Alamat"}
        ));
        
        JLabel lblId = new JLabel("ID");
        lblId.setBounds(10, 161, 46, 14);
        contentPane.add(lblId);
        
        txt_id = new JTextField();
        txt_id.setBounds(66, 158, 86, 20);
        contentPane.add(txt_id);
        txt_id.setColumns(10);
        
        JLabel lblNama = new JLabel("Nama");
        lblNama.setBounds(10, 186, 46, 14);
        contentPane.add(lblNama);
        
        txt_nama = new JTextField();
        txt_nama.setBounds(66, 183, 86, 20);
        contentPane.add(txt_nama);
        txt_nama.setColumns(10);
        
        JLabel lblAlamat = new JLabel("Alamat");
        lblAlamat.setBounds(10, 211, 46, 14);
        contentPane.add(lblAlamat);
        
        txt_alamat = new JTextField();
        txt_alamat.setBounds(66, 208, 86, 20);
        contentPane.add(txt_alamat);
        txt_alamat.setColumns(10);
        
        JButton btn_simpan = new JButton("Simpan");
        btn_simpan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (txt_id.getText().trim().equals("")){
                    JOptionPane.showMessageDialog(null, "Maaf ID belum diisi !");
                    txt_id.requestFocus();
                } else if (txt_nama.getText().trim().equals("")){
                    JOptionPane.showMessageDialog(null, "Maaf Nama belum diisi !");
                    txt_nama.requestFocus();
                } else if (txt_alamat.getText().trim().equals("")){
                    JOptionPane.showMessageDialog(null, "Maaf Alamat belum diisi !");
                    txt_alamat.requestFocus();
                } else {
                    try {
                        aa.simpanData(txt_id.getText(), txt_nama.getText(), txt_alamat.getText());
                        JOptionPane.showMessageDialog(null, "Data berhasil tersimpan", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                        tampil_database();
                        reset_text();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Data gagal tersimpan", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
        btn_simpan.setBounds(10, 236, 89, 23);
        contentPane.add(btn_simpan);
        
        JButton btn_reset = new JButton("Reset");
        btn_reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reset_text();
            }
        });
        btn_reset.setBounds(109, 236, 89, 23);
        contentPane.add(btn_reset);
        
        JButton btn_ubah = new JButton("Ubah");
        btn_ubah.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    aa.ubahData(txt_id.getText(), txt_nama.getText(), txt_alamat.getText());
                    JOptionPane.showMessageDialog(null, "Data berhasil diubah", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                    tampil_database();
                    reset_text();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Data gagal diubah", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        btn_ubah.setBounds(208, 236, 89, 23);
        contentPane.add(btn_ubah);
        
        JButton btn_hapus = new JButton("Hapus");
        btn_hapus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (txt_id.getText().trim().equals("")){
                    JOptionPane.showMessageDialog(null, "Maaf ID belum diisi !");
                    txt_id.requestFocus();
                } else {
                    if (JOptionPane.showConfirmDialog(null, "Apakah Anda yakin akan menghapus data ini ?", "Warning", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        try {
                            aa.hapusData(txt_id.getText());
                            JOptionPane.showMessageDialog(null, "Data berhasil dihapus", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                            tampil_database();
                            reset_text();
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, "Data gagal dihapus", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            }
        });
        btn_hapus.setBounds(307, 236, 89, 23);
        contentPane.add(btn_hapus);
        
        tampil_database();
    }
    
    private void tampil_database() {
        DefaultTableModel model = (DefaultTableModel) tabel_siswa.getModel();
        model.setRowCount(0);
        try {
            ResultSet rs = aa.tampilData();
            while (rs.next()) {
                Object[] data = {
                    rs.getString("id"),
                    rs.getString("nama"),
                    rs.getString("alamat")
                };
                model.addRow(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void reset_text() {
        txt_id.setText("");
        txt_nama.setText("");
        txt_alamat.setText("");
    }
}
