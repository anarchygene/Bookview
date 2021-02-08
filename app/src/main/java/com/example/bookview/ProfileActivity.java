package com.example.bookview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.io.Serializable;
import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {
    FirebaseAuth auth;
    BottomNavigationView bottomNavigationView;
    TextView profileUsername, profileEmail, profileBirthday, profileGenres;
    String username, email, birthday, genresString;
    ArrayList<String> genres;
    public User user;
    public ArrayList<Book> booklist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.getMenu().findItem(R.id.action_profile).setChecked(true);

        user = (User)getIntent().getSerializableExtra("userInfo");
        booklist = (ArrayList<Book>)getIntent().getSerializableExtra("bookInfo");

        auth = FirebaseAuth.getInstance();
        username = user.getUsername();
        email = user.getEmail();
        birthday = user.getBirthday();
        genres = user.getGenres();
        genresString = "";
        if (genres != null) {
            for (int i = 0; i < genres.size(); i++) {
                if(i == genres.size()) {
                    genresString += genres.get(i);
                } else {
                    genresString += genres.get(i) + ", ";
                }
            }
        }
        if (genresString.equals("")) {
            genresString = "No favorite genres yet";
        }

        profileUsername = findViewById(R.id.profileName);
        profileEmail = findViewById(R.id.profileEmail);
        profileBirthday = findViewById(R.id.profileBirthday);
        profileGenres = findViewById(R.id.profileGenre);
        profileUsername.setText(username);
        profileEmail.setText(email);
        profileBirthday.setText(birthday);
        profileGenres.setText(genresString);
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

    public void onClick2(View v) {
        switch (v.getId()) {
            case R.id.profileLogOutBtn:
                auth.signOut();
                Intent i = new Intent(this, LoginActivity.class);
                i.putExtra("userInfo", user);
                i.putExtra("bookInfo", (Serializable)booklist);
                startActivity(i);
                break;
        }
    }
}