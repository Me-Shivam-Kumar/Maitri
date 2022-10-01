package com.engage.maitri;

import java.util.List;

public class BooksModel {
    private String bookName,bookDescription,imageLink,noOfPages,rating,authorName;

    public BooksModel(String bookName, String bookDescription, String imageLink, String noOfPages, String rating, String authorName) {
        this.bookName = bookName;
        this.bookDescription = bookDescription;
        this.imageLink = imageLink;
        this.noOfPages = noOfPages;
        this.rating = rating;
        this.authorName = authorName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getNoOfPages() {
        return noOfPages;
    }

    public void setNoOfPages(String noOfPages) {
        this.noOfPages = noOfPages;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
