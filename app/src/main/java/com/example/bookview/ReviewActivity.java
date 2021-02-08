package com.example.bookview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReviewActivity extends AppCompatActivity {
    public User user;
    public ArrayList<Book> booklist;
    public Book b;
    Button submitBtn;
    EditText reviewDesc;
    DatabaseReference reference;
    public Review review;
    public float starcount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        user = (User)getIntent().getSerializableExtra("userInfo");
        booklist = (ArrayList<Book>)getIntent().getSerializableExtra("bookInfo");
        b = (Book)getIntent().getSerializableExtra("Book");
        starcount = (Float)getIntent().getSerializableExtra("starcount");
        if(user == null || booklist == null) {
            System.out.println("User is empty");
        } else {
            System.out.println(user);
            System.out.println(booklist);
        }

        reference = FirebaseDatabase.getInstance().getReference("reviews");
        reviewDesc = findViewById(R.id.reviewDesc);
        submitBtn = findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String reviewContent = reviewDesc.getText().toString();
                if(reviewContent.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Empty reviews are not allowed", Toast.LENGTH_SHORT).show();
                }else {
                    String username = user.getUsername();
                    String forBook = b.getTitle();
                    String timestamp = LocalDate.now().toString();
                    review = new Review(username, reviewContent, forBook, timestamp, starcount);
                    String uniqueKeyRef = reference.push().getKey();
                    reference.child(uniqueKeyRef).setValue(review);

                    Intent i = new Intent(ReviewActivity.this, BookDetailsActivity.class);
                    Toast.makeText(getApplicationContext(), "Thank you for your review", Toast.LENGTH_LONG).show();
                    i.putExtra("userInfo", user);
                    i.putExtra("bookInfo", (Serializable)booklist);
                    i.putExtra("Book", b);
                    startActivity(i);
                }

            }
        });
    }
}