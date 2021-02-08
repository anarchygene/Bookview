package com.example.bookview;

import java.io.Serializable;

public class Review implements Serializable {
    public String username, reviewDesc, forBook, timestamp;
    public float starcount;

    public Review() {

    }

    public Review(String username, String reviewDesc, String forBook, String timestamp, float starcount) {
        this.username = username;
        this.reviewDesc = reviewDesc;
        this.forBook = forBook;
        this.timestamp = timestamp;
        this.starcount = starcount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getReviewDesc() {
        return reviewDesc;
    }

    public void setReviewDesc(String reviewDesc) {
        this.reviewDesc = reviewDesc;
    }

    public String getForBook() {
        return forBook;
    }

    public void setForBook(String forBook) {
        this.forBook = forBook;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public float getStarcount() {
        return starcount;
    }

    public void setStarcount(int starcount) {
        this.starcount = starcount;
    }
}
