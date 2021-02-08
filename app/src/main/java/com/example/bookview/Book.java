package com.example.bookview;

import java.io.Serializable;

public class Book implements Serializable {
    public String title, imageURI, author, category;
    public int ratingNo;

    public Book() {
    }

    public Book(String title, String imageURI, String author, String category, int ratingNo) {
        this.title = title;
        this.imageURI = imageURI;
        this.author = author;
        this.category = category;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getRatingNo() {
        return ratingNo;
    }

    public void setRatingNo(int ratingNo) {
        this.ratingNo = ratingNo;
    }

}
