package com.expense;
import com.expense.util.DatabaseConnection;
import com.expense.gui.MainGUI;
import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] arsg) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        DatabaseConnection db_Connection = new DatabaseConnection();
        try {
            Connection cn = db_Connection.getDBConnection();
            System.out.println("Connection established");
        } catch (
                SQLException e) {
            System.out.println("Conection Failed");
            System.exit(1);
        }
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        SwingUtilities.invokeLater(() -> {
            try {
                new MainGUI().setVisible(true);
            }catch (Exception e){
                System.err.println("Error starting the application "+e.getLocalizedMessage());
            }
        });
    }
}
