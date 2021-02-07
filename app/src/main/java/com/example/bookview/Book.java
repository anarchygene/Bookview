package com.example.bookview;

import java.io.Serializable;

public class Book implements Serializable {
    public String title, imageURI;

    public Book() {
    }

    public Book(String title, String imageURI) {
        this.title = title;
        this.imageURI = imageURI;
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
}
