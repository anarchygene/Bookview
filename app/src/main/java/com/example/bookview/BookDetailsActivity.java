package com.example.bookview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.List;

public class BookDetailsActivity extends AppCompatActivity {
    public User user;
    public List<Book> booklist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        user = (User)getIntent().getSerializableExtra("userInfo");
        booklist = (List<Book>)getIntent().getSerializableExtra("bookInfo");
        if(user == null || booklist == null) {
            System.out.println("User is empty");
        } else {
            System.out.println(user);
            System.out.println(booklist);
        }
    }
}