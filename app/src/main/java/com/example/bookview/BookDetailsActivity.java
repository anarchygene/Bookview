package com.example.bookview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;

public class BookDetailsActivity extends AppCompatActivity {
    public User user;
    public ArrayList<Book> booklist;
    public Book b;
    public ArrayList<Review> reviewlist;
    public ImageView bookImage;
    public TextView bookTitle, bookAuthor, bookRatings, bookReviews;
    public Button reviewBtn;
    public RatingBar ratingBar;
    DatabaseReference reference;
    RecyclerView review_recyclerview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        user = (User)getIntent().getSerializableExtra("userInfo");
        booklist = (ArrayList<Book>)getIntent().getSerializableExtra("bookInfo");
        b = (Book)getIntent().getSerializableExtra("Book");

        bookImage = findViewById(R.id.bookDetailImg);
        bookTitle = findViewById(R.id.bookDetailTitle);
        bookAuthor = findViewById(R.id.bookDetailAuthor);
        bookRatings = findViewById(R.id.bookDetailRatings);
        bookReviews = findViewById(R.id.bookDetailReviews);
        reviewBtn = findViewById(R.id.bookDetailReviewBtn);
        ratingBar = findViewById(R.id.bookDetailStarRatings);
        reviewlist = new ArrayList<Review>();
        review_recyclerview = findViewById(R.id.review_recyclerview);

        Picasso.get().load(b.getImageURI()).into(bookImage);
        bookTitle.setText(b.getTitle());
        bookAuthor.setText("by " + b.getAuthor());
        bookRatings.setText(Integer.toString(b.getRatingNo())+" ratings");

        reference = FirebaseDatabase.getInstance().getReference("reviews");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                System.out.println(snapshot);
                for (DataSnapshot childsnapshot: snapshot.getChildren()) {
                    if(childsnapshot.child("forBook").getValue(String.class).equals(b.getTitle())) {
                        String reviewDesc = childsnapshot.child("reviewDesc").getValue(String.class);
                        String forBook = childsnapshot.child("forBook").getValue(String.class);
                        float starcount = childsnapshot.child("starcount").getValue(float.class);
                        String timestamp = childsnapshot.child("timestamp").getValue(String.class);
                        String username = childsnapshot.child("username").getValue(String.class);
                        reviewlist.add(new Review(username, reviewDesc, forBook, timestamp, starcount));
                        bookReviews.setText(reviewlist.size() + " reviews");
                    }
                }
                System.out.println(reviewlist.get(0).getForBook());
                loadRecyclerView(review_recyclerview, reviewlist);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        reviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i;
                i = new Intent(BookDetailsActivity.this, ReviewActivity.class);
                i.putExtra("userInfo", user);
                i.putExtra("bookInfo", (Serializable)booklist);
                i.putExtra("Book", b);
                i.putExtra("starcount", ratingBar.getRating());
                startActivity(i);
            }
        });
    }


    private void loadRecyclerView(RecyclerView recyclerView, ArrayList<Review> reviewlist) {
        //Set recycler view to display wallets
        ReviewAdapter adapter = new ReviewAdapter(user, reviewlist, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}