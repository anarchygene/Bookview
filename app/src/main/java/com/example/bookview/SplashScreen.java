package com.example.bookview;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class SplashScreen extends AppCompatActivity {
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Launch the layout -> splash.xml
        setContentView(R.layout.splash);
        auth = FirebaseAuth.getInstance();
        Thread splashThread = new Thread() {
            public void run() {
                try {
                    // sleep time in milliseconds (3000 = 3sec)
                    sleep(3000);
                }  catch(InterruptedException e) {
                    // Trace the error
                    e.printStackTrace();
                } finally
                {
                    auth.signOut();
                    System.out.println(auth.getCurrentUser());
                    if(auth.getCurrentUser() != null) {
                        // Launch the MainActivity class if the user is already logged in
                        Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        // Launch the LoginActivity class
                        Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                        startActivity(intent);
                    }
                }
            }
        };
        // To Start the thread
        splashThread.start();
    }
}

