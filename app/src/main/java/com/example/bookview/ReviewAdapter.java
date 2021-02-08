package com.example.bookview;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewViewHolder>{
    User user;
    ArrayList<Review> reviewlist;
    Activity bookdetailctivity;

    public ReviewAdapter(User user, ArrayList<Review> reviewlist, Activity bookdetailctivity) {
        this.user = user;
        this.reviewlist = reviewlist;
        this.bookdetailctivity = bookdetailctivity;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.reviews_view_item, parent, false);
        ReviewViewHolder viewHolder = new ReviewViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        final Review review = reviewlist.get(position);
        System.out.println(review.getForBook());
        System.out.println(reviewlist.size());
        holder.username.setText(review.getUsername());
        holder.review.setText(review.getReviewDesc());
        holder.date.setText(review.getTimestamp());
        holder.ratingBar.setRating(review.getStarcount());
    }

    @Override
    public int getItemCount() {
        return reviewlist.size();
    }
}
