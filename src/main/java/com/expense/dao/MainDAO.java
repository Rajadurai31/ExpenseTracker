package com.expense.dao;
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

        private static final String SELECT_ALL_CATEGORY = "select * from category order by categoryId ";

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
                    stmt.setDouble(2, expense.getAmount());   // BigDecimal for DECIMAL
                    stmt.setString(3, expense.getPaymentMethod()); // "Cash", "Online", "Card", "UPI"
                    stmt.setString(4, expense.getNote());

                    int rowsInserted = stmt.executeUpdate();
                    return rowsInserted > 0;
                }catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                }
        }

    //    public boolean updateExpense(Expense expense) {
    //        try (Connection conn = DatabaseConnection.getDBConnection();
    //             PreparedStatement stmt = conn.prepareStatement(UPDATE_EXPENSE)) {
    //            stmt.setInt(1, expense.getCategoryId());
    //            stmt.setDouble(2, expense.getAmount());
    //            stmt.setString(3, expense.getPaymentMethod());
    //            stmt.setTimestamp(4, Timestamp.valueOf(expense.getExpenseAt()));
    //            stmt.setString(5, expense.getNote());
    //            stmt.setInt(6, expense.getExpenseId());
    //            int row = stmt.executeUpdate();
    //            return  row> 0;
    //        } catch (SQLException e) {
    //            e.printStackTrace();
    //            return false;
    //        }
    //    }

        public List<Category> getAllCategory() throws SQLException {
            List<Category> categories = new ArrayList<>();
            try (Connection conn = DatabaseConnection.getDBConnection();
                 PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_CATEGORY);
                 ResultSet res = stmt.executeQuery();
            ) {
                System.out.println("Query executed successfully!");
                while (res.next()) {
                    Category category = getCategoryRow(res);
                    System.out.println(category);
                    categories.add(category);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return categories;
        }

        private Category getCategoryRow(ResultSet res) throws SQLException {
            return new Category(
                    res.getInt("category_id"),
                    res.getString("note"),
                    res.getString("description")
            );
        }
}
