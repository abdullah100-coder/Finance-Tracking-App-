package com.example.e_wallet;

public class expense_details {
    String expense, category, note, date,image="";

    public expense_details() {
    }

    public expense_details(String expense, String category, String note, String date) {
        this.expense = expense;
        this.category = category;
        this.note = note;
        this.date = date;
    }

    public expense_details(String expense, String category, String note, String date, String image) {
        this.expense = expense;
        this.category = category;
        this.note = note;
        this.date = date;
        this.image=image;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
