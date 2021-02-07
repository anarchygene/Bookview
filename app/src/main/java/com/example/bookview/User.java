package com.example.bookview;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    public String email, username, birthday;
    public ArrayList<String> genres;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String email, String username, String birthday, ArrayList<String> genres) {
        this.email = email;
        this.username = username;
        this.birthday = birthday;
        this.genres = genres;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }
}
