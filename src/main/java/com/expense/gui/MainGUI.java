package com.expense.gui;

import javax.swing.*;
import java.awt.*;

public class MainGUI extends JFrame {
    private JPanel panel;
    private JButton expenseButton;
    private JButton categoryButton;

    public MainGUI() {
       initializeComponents();
       setupLayout();
       setupEvenListeners();
    }
    private void initializeComponents(){
            setTitle("Expense Tracker - Home");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(400, 300);
            setLocationRelativeTo(null);

             categoryButton = new JButton("Category");
             categoryButton.setPreferredSize(new Dimension(120, 120));


            expenseButton = new JButton("Expense");
            expenseButton.setPreferredSize(new Dimension(120, 120));
        }
        private void setupLayout() {
            panel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(20, 20, 20, 20);

            gbc.gridx = 0;
            gbc.gridy = 0;
            panel.add(categoryButton, gbc);

            gbc.gridx = 1;
            gbc.gridy = 0;
            panel.add(expenseButton, gbc);

            add(panel);
        }
        private void setupEvenListeners() {
//            categoryButton.addActionListener(e -> { onSelectedExpence();});
//            expenseButton.addActionListener(e -> {onSelectedCategory();});
        }
//        private void onSelectedExpence(){
//            setTitle("Expense");
//            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//            setSize(400, 300);
//            setLocationRelativeTo(null);
//            panel = new JPanel(new GridBagLayout());
//            GridBagConstraints gbc = new GridBagConstraints();
//            gbc.insets = new Insets(20, 20, 20, 20);
//        }
//        private void onSelectedCategory(){
//            setTitle("Category");
//            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//            setSize(400, 300);
//            setLocationRelativeTo(null);
//            panel = new JPanel(new GridBagLayout());
//            GridBagConstraints gbc = new GridBagConstraints();
//            gbc.insets = new Insets(20, 20, 20, 20);
//        }
}
