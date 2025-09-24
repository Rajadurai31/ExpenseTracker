package com.model;
import java.util.Date;

public class Expense {
    private int expenseId;
    private int categoryId;
    private double amount;
    private String paymentMethod;
    private Date expenseDate;
    private String note;

    public Expense(int expenseId, int categoryId, double amount, String paymentMethod, Date expenseDate, String note) {
        this.expenseId = expenseId;
        this.categoryId = categoryId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.expenseDate = expenseDate;
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

    public Date getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(Date expenseDate) {
        this.expenseDate = expenseDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}