package com.expense.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;

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
            expenseButton.addActionListener(e -> {
                SwingUtilities.invokeLater(
                        ()->{
                            try {
                                new ExpenseGUI().setVisible(true);
                            } catch (Exception t) {
                                System.err.println("Error strarting the application "+t.getLocalizedMessage());
                            }
                        }
                );
            });
            categoryButton.addActionListener(e -> {
                SwingUtilities.invokeLater( ()->{
                        try {
                            new CategoryGUI().setVisible(true);
                        } catch (Exception t) {
                            System.err.println("Error strarting the application "+t.getLocalizedMessage());
                        }
                    }
            );});
        }
}



class ExpenseGUI extends JFrame{
    private JTable expenseTable;
    private DefaultTableModel tableModel;

    private JComboBox<String> categoryCombo;
    private JTextField amountField;
    private JComboBox<String> paymentCombo;
    private JTextField dateField;
    private JTextField noteField;

    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    public ExpenseGUI() {
        initializeComponents();
        setupLayout();
        setupEvenListeners();
    }
    private void initializeComponents(){
        setTitle("Expense");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        String[] columns = {"ID", "Category", "Amount", "Payment", "Date", "Note"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        expenseTable = new JTable(tableModel);
        expenseTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        expenseTable.getSelectionModel().addListSelectionListener(e -> {
//            if (!e.getValueIsAdjusting())
//                loadSelectedExpense();
        });
        categoryCombo = new JComboBox<>();
        amountField = new JTextField(10);
        paymentCombo = new JComboBox<>(new String[]{"Cash", "Online", "Card", "UPI"});
        dateField = new JTextField(LocalDate.now().toString());
        noteField = new JTextField(20);

        addButton = new JButton("Add");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
    }
    private void setupLayout(){
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx=0; gbc.gridy=0; inputPanel.add(new JLabel("Category:"), gbc);
        gbc.gridx=1; inputPanel.add(categoryCombo, gbc);

        gbc.gridx=0; gbc.gridy=1; inputPanel.add(new JLabel("Amount:"), gbc);
        gbc.gridx=1; inputPanel.add(amountField, gbc);

        gbc.gridx=0; gbc.gridy=2; inputPanel.add(new JLabel("Payment:"), gbc);
        gbc.gridx=1; inputPanel.add(paymentCombo, gbc);

        gbc.gridx=0; gbc.gridy=3; inputPanel.add(new JLabel("Date (YYYY-MM-DD):"), gbc);
        gbc.gridx=1; inputPanel.add(dateField, gbc);

        gbc.gridx=0; gbc.gridy=4; inputPanel.add(new JLabel("Note:"), gbc);
        gbc.gridx=1; inputPanel.add(noteField, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.add(inputPanel, BorderLayout.CENTER);
        northPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(northPanel, BorderLayout.NORTH);
        add(new JScrollPane(expenseTable), BorderLayout.CENTER);
    }
    private void setupEvenListeners(){
//        addButton.addActionListener(e -> addExpense());
//        updateButton.addActionListener(e -> updateExpense());
//        deleteButton.addActionListener(e -> deleteExpense());
    }


}
class CategoryGUI extends JFrame{
    private JTable categoryTable;
    private DefaultTableModel tableModel;

    private JTextField nameField;
    private JTextField descriptionField;

    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    public CategoryGUI(){
        initializeComponents();
        setupLayout();
        setupEvenListeners();
    }
    private void initializeComponents(){
        setTitle("Category Management");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        String[] columns = {"ID", "Name", "Description"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        categoryTable = new JTable(tableModel);
        categoryTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//        categoryTable.getSelectionModel().addListSelectionListener(e -> loadSelectedCategory());

        nameField = new JTextField(15);
        descriptionField = new JTextField(20);

        addButton = new JButton("Add");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
    }
    private void setupLayout(){
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx=0; gbc.gridy=0; inputPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx=1; inputPanel.add(nameField, gbc);

        gbc.gridx=0; gbc.gridy=1; inputPanel.add(new JLabel("Description:"), gbc);
        gbc.gridx=1; inputPanel.add(descriptionField, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.add(inputPanel, BorderLayout.CENTER);
        northPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(northPanel, BorderLayout.NORTH);
        add(new JScrollPane(categoryTable), BorderLayout.CENTER);
    }
    private void setupEvenListeners(){
//        addButton.addActionListener(e -> addCategory());
//        updateButton.addActionListener(e -> updateCategory());
//        deleteButton.addActionListener(e -> deleteCategory());
    }
}
