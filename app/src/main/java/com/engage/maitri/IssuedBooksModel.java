package com.engage.maitri;

public class IssuedBooksModel {
    String bookName,authorName,accNum,imageLink;
    int transDay,transMonth,transYear;
    int returnDay,returnMonth,returnYear;


    public IssuedBooksModel(String bookName, String authorName, String accNum, String imageLink, int transDay, int transMonth, int transYear, int returnDay, int returnMonth, int returnYear) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.accNum = accNum;
        this.imageLink = imageLink;
        this.transDay = transDay;
        this.transMonth = transMonth;
        this.transYear = transYear;
        this.returnDay = returnDay;
        this.returnMonth = returnMonth;
        this.returnYear = returnYear;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAccNum() {
        return accNum;
    }

    public void setAccNum(String accNum) {
        this.accNum = accNum;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public int getTransDay() {
        return transDay;
    }

    public void setTransDay(int transDay) {
        this.transDay = transDay;
    }

    public int getTransMonth() {
        return transMonth;
    }

    public void setTransMonth(int transMonth) {
        this.transMonth = transMonth;
    }

    public int getTransYear() {
        return transYear;
    }

    public void setTransYear(int transYear) {
        this.transYear = transYear;
    }

    public int getReturnDay() {
        return returnDay;
    }

    public void setReturnDay(int returnDay) {
        this.returnDay = returnDay;
    }

    public int getReturnMonth() {
        return returnMonth;
    }

    public void setReturnMonth(int returnMonth) {
        this.returnMonth = returnMonth;
    }

    public int getReturnYear() {
        return returnYear;
    }

    public void setReturnYear(int returnYear) {
        this.returnYear = returnYear;
    }
}
