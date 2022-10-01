package com.engage.maitri;

public class NewArrivalsModel {
    String bookImage,bookName;

    public NewArrivalsModel(String bookImage, String bookName) {
        this.bookImage = bookImage;
        this.bookName = bookName;
    }

    public String getBookImage() {
        return bookImage;
    }

    public void setBookImage(String bookImage) {
        this.bookImage = bookImage;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}
