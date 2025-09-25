package com.expense.dao;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.model.Expense;
import com.model.Category;
import com.expense.util.DatabaseConnection;
public class MainDAO {
        private static final String SELECT_ALL_EXPENSE = "select * from expense order by expense_date desc";

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
}
