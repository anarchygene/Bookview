package com.example.bookview;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.google.firebase.storage.StorageReference;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.net.URI;
import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookViewHolder> {

    ArrayList<Book> data;
    Activity homeActivity;

    public BookAdapter(Activity activity, ArrayList<Book> books){
        data = books;
        homeActivity = activity;
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
        holder.bookImage.setImageURI(new URI(b.getImageURI()));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBookDetails(b);
            }
        });
    }

    private void openBookDetails(Book b) {
        // Open books details and pass book data to BookDetailsActivity
        Intent intent = new Intent(homeActivity, BookDetailsActivity.class);
        intent.putExtra("Book", b);
        homeActivity.startActivity(intent);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
