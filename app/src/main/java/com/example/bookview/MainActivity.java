package com.example.bookview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    public User user;
    public ArrayList<Book> booklist, nfbooklist, fbooklist;
    RecyclerView nf_recyclerView, f_recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.getMenu().findItem(R.id.action_home).setChecked(true);

        user = (User)getIntent().getSerializableExtra("userInfo");
        booklist = (ArrayList<Book>) getIntent().getSerializableExtra("bookInfo");
        fbooklist = new ArrayList<Book>();
        nfbooklist = new ArrayList<Book>();

        nf_recyclerView = findViewById(R.id.nonfiction_recyclerview);
        f_recyclerView = findViewById(R.id.fiction_recyclerview);

        if (booklist != null) {
            for (int i = 0; i < booklist.size(); i++) {
                if(booklist.get(i).getCategory().equals("F")) {
                    fbooklist.add(booklist.get(i));
                } else{
                    nfbooklist.add(booklist.get(i));
                }
            }
            loadRecyclerView(nf_recyclerView, nfbooklist);
            loadRecyclerView(f_recyclerView, fbooklist);
        }

    }

    private void loadRecyclerView(RecyclerView recyclerView, ArrayList<Book> list) {
        //Set recycler view to display wallets
        BookAdapter adapter = new BookAdapter(this, list, booklist, user);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        LinearLayoutManager HorizontalLayout = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(HorizontalLayout);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
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