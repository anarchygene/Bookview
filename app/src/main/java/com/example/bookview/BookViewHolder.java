package com.example.bookview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class BookViewHolder extends RecyclerView.ViewHolder {
    CardView cardView;
    TextView bookTitle;
    ImageView bookImage;
    public BookViewHolder(@NonNull View itemView) {
        super(itemView);
        cardView = itemView.findViewById(R.id.bookcardview);
        bookTitle = itemView.findViewById(R.id.bookname);
        bookImage = itemView.findViewById(R.id.bookimage);
    }
}
