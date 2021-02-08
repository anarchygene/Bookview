package com.example.bookview;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookViewHolder> {
    User user;
    ArrayList<Book> booklist, data;
    Activity homeActivity;

    public BookAdapter(Activity activity, ArrayList<Book> books, ArrayList<Book> allBooks, User user){
        this.data = books;
        this.homeActivity = activity;
        this.booklist = allBooks;
        this.user = user;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_view_item, parent, false);
        BookViewHolder viewHolder = new BookViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        final Book b = data.get(position);
        holder.bookTitle.setText(b.getTitle());
        Picasso.get().load(b.getImageURI()).into(holder.bookImage);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBookDetails(b);
            }
        });
    }

    private void openBookDetails(Book b) {

        // Open books details and pass book data to BookDetailsActivity
        Intent i = new Intent(homeActivity, BookDetailsActivity.class);
        if(user == null || booklist == null) {
            System.out.println("User is empty");
        } else {
            System.out.println("User: " + user);
            System.out.println("Booklist: " + booklist);
        }
        i.putExtra("userInfo", user);
        i.putExtra("bookInfo", (Serializable)booklist);
        i.putExtra("Book", b);
        homeActivity.startActivity(i);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
