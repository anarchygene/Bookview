package com.example.bookview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.Serializable;
import java.util.ArrayList;

public class FeedbackActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    public User user;
    public ArrayList<Book> booklist;
    EditText feedback;
    Button submitBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.getMenu().findItem(R.id.action_feedback).setChecked(true);

        user = (User)getIntent().getSerializableExtra("userInfo");
        booklist = (ArrayList<Book>)getIntent().getSerializableExtra("bookInfo");

        feedback = findViewById(R.id.feedback);
        submitBtn = findViewById(R.id.feedbacksubmit);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(feedback.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please don't submit empty feedbacks", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Thank you!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(FeedbackActivity.this, MainActivity.class);
                    i.putExtra("userInfo", user);
                    i.putExtra("bookInfo", (Serializable)booklist);
                    startActivity(i);
                }
            }
        });
    }

    public void onClick(MenuItem item) {
        Intent i;
        switch (item.getItemId()) {
            case R.id.action_home:
                i = new Intent(this, MainActivity.class);
                i.putExtra("userInfo", user);
                i.putExtra("bookInfo", (Serializable)booklist);
                startActivity(i);
                break;
            case R.id.action_about:
                i = new Intent(this, AboutUsActivity.class);
                i.putExtra("userInfo", user);
                i.putExtra("bookInfo", (Serializable)booklist);
                startActivity(i);
                break;
            case R.id.action_feedback:
                i = new Intent(this, FeedbackActivity.class);
                i.putExtra("userInfo", user);
                i.putExtra("bookInfo", (Serializable)booklist);
                startActivity(i);
                break;
            case R.id.action_search:
                i = new Intent(this, SearchActivity.class);
                i.putExtra("userInfo", user);
                i.putExtra("bookInfo", (Serializable)booklist);
                startActivity(i);
                break;
            case R.id.action_profile:
                i = new Intent(this, ProfileActivity.class);
                i.putExtra("userInfo", user);
                i.putExtra("bookInfo", (Serializable)booklist);
                startActivity(i);
                break;
        }
    }
}