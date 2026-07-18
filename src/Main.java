import db.DBConnection;
import java.sql.Connection;

public class Main {

    public static void main(String[] args) {

        try {
            Connection conn = DBConnection.getConnection();

            if (conn != null) {
                System.out.println("Database Connected Successfully!");
            } else {
                System.out.println("Connection Failed!");
            }

            // Launch the Application via Controller
            javax.swing.SwingUtilities.invokeLater(() -> {
                controller.AuthController authController = new controller.AuthController();
                authController.start();
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}