package com.example.bookview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    public User user;
    public ArrayList<Book> booklist;
    RecyclerView nf_recyclerView;
    RecyclerView f_recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        user = (User)getIntent().getSerializableExtra("userInfo");
        booklist = (ArrayList<Book>) getIntent().getSerializableExtra("bookInfo");

        nf_recyclerView = findViewById(R.id.nonfiction_recyclerview);
        f_recyclerView = findViewById(R.id.fiction_recyclerview);

        loadRecyclerView(nf_recyclerView, booklist);
        loadRecyclerView(f_recyclerView, booklist);
    }

    private void loadRecyclerView(RecyclerView recyclerView, ArrayList<Book> list) {
        //Set recycler view to display wallets
        BookAdapter adapter = new BookAdapter(this, list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        LinearLayoutManager HorizontalLayout = new LinearLayoutManager(HomeActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(HorizontalLayout);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}