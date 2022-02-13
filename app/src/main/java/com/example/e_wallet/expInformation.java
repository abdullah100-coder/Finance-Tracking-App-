package com.example.e_wallet;

public class expInformation {
    String expense, category, note, date;

    public expInformation() {
    }

    public expInformation(String expense, String category, String note, String date) {
        this.expense = expense;
        this.category = category;
        this.note = note;
        this.date = date;
    }

    public String getExpense() {
        return expense;
    }

    public void setExpense(String expense) {
        this.expense = expense;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
