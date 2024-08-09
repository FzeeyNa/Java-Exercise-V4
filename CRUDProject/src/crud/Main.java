package crud;

public class Main {
    public static void main(String[] args) {
        try {
            FormSiswa form = new FormSiswa();
            form.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
