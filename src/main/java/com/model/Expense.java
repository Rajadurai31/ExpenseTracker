package com.model;
import java.time.LocalDateTime;

public class Expense {
    private int expenseId;
    private int categoryId;
    private double amount;
    private String paymentMethod;
    private LocalDateTime expenseAt;
    private String note;

    public Expense(){
        this.expenseAt = LocalDateTime.now();
    }
    public Expense(int expenseId, int categoryId, double amount, String paymentMethod, LocalDateTime expenseAt, String note) {
        this.expenseId = expenseId;
        this.categoryId = categoryId;
        this.amount = amount;
        this.expenseAt=expenseAt;
        this.paymentMethod = paymentMethod;
        this.note = note;
    }

    public int getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public LocalDateTime getExpenseAt() {
        return expenseAt;
    }

    public void setExpenseAt() {
        this.expenseAt = expenseAt;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}