package com.example.bookview;

import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ReviewViewHolder extends RecyclerView.ViewHolder{
    CardView cardView;
    TextView username, date, review;
    RatingBar ratingBar;
    public ReviewViewHolder(@NonNull View itemView) {
        super(itemView);
        cardView = itemView.findViewById(R.id.reviewcardview);
        username = itemView.findViewById(R.id.username);
        date = itemView.findViewById(R.id.date);
        review = itemView.findViewById(R.id.reviewContent);
        ratingBar = itemView.findViewById(R.id.ratingBar);
    }
}
