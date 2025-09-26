package com.expense.dao;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.model.Expense;
import com.model.Category;
import com.expense.util.DatabaseConnection;

import javax.xml.crypto.Data;

public class MainDAO {
        private static final String SELECT_ALL_EXPENSE = "select * from expense order by expense_date desc";
        private static final String INSERT_INTO = "INSERT INTO expense (category_id, amount, payment_method, note) VALUES (?, ?, ?, ?)";
        private static final String SELECT_EXPENSEID_BY_NAME = "SELECT category_id FROM category WHERE name = ?";
        private static final String UPDATE_EXPENSE = "UPDATE expenses SET category_id=?, amount=?, payment_method=?, expense_at=?, note=? WHERE expense_id=?";
        private static final String DELETE_EXPENSE = "DELETE FROM expenses WHERE expense_id=?";

        private static final String SELECT_ALL_CATEGORY = "select * from category";
        private static final String INSERT_CATEGORY = "INSERT INTO category(name, description) VALUES (?, ?)";
        private static final String UPDATE_CATEGORY = "UPDATE category SET name=?, description=? WHERE category_id=?";
        private static final String DELETE_CATEGORY = "DELETE FROM category WHERE category_id=?";

    public static int getCategoryIdByName(String categoryName) throws SQLException{
             try(Connection conn = DatabaseConnection.getDBConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_EXPENSEID_BY_NAME);){
                 stmt.setString(1,categoryName);
                 ResultSet res = stmt.executeQuery();
                 if(res.next()){
                     return res.getInt("Category_id");
                 }
             }catch (SQLException e){
                 e.printStackTrace();
             }
             return -1;
        }

        private Expense getExpenseRow(ResultSet res) throws SQLException {
                return new Expense(
                        res.getInt("expense_id"),
                        res.getInt("category_id"),
                        res.getDouble("amount"),
                        res.getString("payment_method"),
                        res.getTimestamp("expense_date").toLocalDateTime(),
                        res.getString("note")
                );
            }
            public  List<Expense> getAllExpense() throws SQLException{
                List<Expense> expenses = new ArrayList<>();
                try (Connection conn = DatabaseConnection.getDBConnection();
                     PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_EXPENSE);
                     ResultSet res = stmt.executeQuery();
                ) {
                    System.out.println("Query executed successfully!");
                    while (res.next()) {
                        Expense expense = getExpenseRow(res);
                        System.out.println(expense);
                        expenses.add(expense);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return expenses;
            }

        public boolean addExpense(Expense expense) throws SQLException {
                try(
                        Connection conn = DatabaseConnection.getDBConnection();
                        PreparedStatement stmt = conn.prepareStatement(INSERT_INTO,Statement.RETURN_GENERATED_KEYS);
                   ){
                    System.out.println("Connecting to database...");
                    stmt.setInt(1, expense.getCategoryId());
                    stmt.setDouble(2, expense.getAmount());
                    stmt.setString(3, expense.getPaymentMethod());
                    stmt.setString(4, expense.getNote());

                    int rowsInserted = stmt.executeUpdate();
                    return rowsInserted > 0;
                }catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                }
        }
    public boolean updateExpense(Expense expense) throws SQLException {
        try (Connection conn = DatabaseConnection.getDBConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_EXPENSE)) {

            stmt.setInt(1, expense.getCategoryId());
            stmt.setString(2, expense.getPaymentMethod().toString());
            stmt.setBigDecimal(3, BigDecimal.valueOf(expense.getAmount()));
            stmt.setString(4, expense.getNote());
            stmt.setTimestamp(5, Timestamp.valueOf(expense.getExpenseAt()));
            stmt.setInt(6, expense.getExpenseId());

            return stmt.executeUpdate() > 0;
        }
    }
    public boolean deleteExpense(Expense expense) throws SQLException {
        try (Connection conn = DatabaseConnection.getDBConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_EXPENSE)) {

            stmt.setInt(1, expense.getExpenseId());
            return stmt.executeUpdate() > 0;
        }
    }




    public int addCategory(Category category) throws SQLException {

        try (Connection conn = DatabaseConnection.getDBConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_CATEGORY, Statement.RETURN_GENERATED_KEYS)) {


            stmt.setString(1, category.getName());
            stmt.setString(2, category.getDescription());

            int rows = stmt.executeUpdate();
            if (rows <= 0) {
                throw new SQLException("Error while inserting category");
            }


            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);               }
            }
        }
        return -1;
    }
        public List<Category> getAllCategory() throws SQLException {
            List<Category> categories = new ArrayList<>();
            try (Connection conn = DatabaseConnection.getDBConnection();
                 PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_CATEGORY);
                 ResultSet res = stmt.executeQuery();
            ) {
                System.out.println("Query executed successfully!");
                while (res.next()) {
                    Category category = getCategoryRow(res);
                    categories.add(category);
                }
            }
            return categories;
        }
    public boolean updateCategory(Category category) throws SQLException {

        try (Connection conn = DatabaseConnection.getDBConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_CATEGORY)) {


            stmt.setString(1, category.getName());
            stmt.setString(2, category.getDescription());
            stmt.setInt(3, category.getCategoryId());


            return stmt.executeUpdate() > 0;
        }
    }
    public boolean deleteCategory(Category category) throws SQLException {

        try (Connection conn = DatabaseConnection.getDBConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_CATEGORY)) {


            stmt.setInt(1, category.getCategoryId());


            return stmt.executeUpdate() > 0;
        }
    }
    private Category getCategoryRow(ResultSet res) throws SQLException {
            return new Category(
                    res.getInt("category_id"),
                    res.getString("name"),
                    res.getString("description")
            );
        }
}
