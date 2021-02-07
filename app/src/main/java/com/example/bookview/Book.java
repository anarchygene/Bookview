package com.example.bookview;

import java.io.Serializable;

public class Book implements Serializable {
    public String title, imageURI, author;
    public int ratingNo;

    public Book() {
    }

    public Book(String title, String imageURI, String author, int ratingNo) {
        this.title = title;
        this.imageURI = imageURI;
        this.author = author;
        this.ratingNo = ratingNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageURI() {
        return imageURI;
    }

    public void setImageURI(String imageURI) {
        this.imageURI = imageURI;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getRatingNo() {
        return ratingNo;
    }

    public void setRatingNo(int ratingNo) {
        this.ratingNo = ratingNo;
    }
}
