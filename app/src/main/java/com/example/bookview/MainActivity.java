package com.example.bookview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button registerBtn, loginBtn;
    public User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = (User)getIntent().getSerializableExtra("userInfo");
        if(user == null) {
            System.out.println("User is empty");
        } else {
            System.out.println(user);
        }

        registerBtn = findViewById(R.id.button);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });

        loginBtn = findViewById(R.id.button2);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
    }

    public void onClick(MenuItem item) {
        Intent i;
        switch (item.getItemId()) {
            case R.id.action_home:
                i = new Intent(this, MainActivity.class);
                i.putExtra("userInfo", user);
                startActivity(i);
                break;
//            case R.id.action_about:
//                i = new Intent(this, AboutUs.class);
//                startActivity(i);
//                break;
////            case R.id.action_feedback:
////                i = new Intent(this, Feedback.class);
////                startActivity(i);
////                break;
//            case R.id.action_search:
//                i = new Intent(this, Search.class);
//                startActivity(i);
//                break;
            case R.id.action_profile:
                i = new Intent(this, ProfileActivity.class);
                i.putExtra("userInfo", user);
                startActivity(i);
                break;
        }
    }


}