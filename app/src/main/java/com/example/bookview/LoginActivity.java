package com.example.bookview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.*;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    DatabaseReference reference;
    FirebaseAuth auth;
    EditText emailinput, passwordinput;
    Button registerBtn, loginBtn;
    public User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference("users");
        registerBtn = findViewById(R.id.btnRegister);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
        loginBtn = findViewById(R.id.btnLogin);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
    }

    private void loginUser() {
        emailinput = findViewById(R.id.editEmail);
        passwordinput = findViewById(R.id.editPassword);

        String email = emailinput.getText().toString();
        String password = passwordinput.getText().toString();
        if (email.isEmpty()) {
            emailinput.setError("Please enter your username");
            emailinput.requestFocus();
        } else if (password.isEmpty()) {
            passwordinput.setError("Please enter your password");
            passwordinput.requestFocus();
        } else {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Query getUserInfo = reference.orderByChild(auth.getCurrentUser().getUid());
                        getUserInfo.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()) {
                                    String username = snapshot.child(auth.getCurrentUser().getUid()).child("username").getValue(String.class);
                                    String birthday = snapshot.child(auth.getCurrentUser().getUid()).child("birthday").getValue(String.class);
                                    ArrayList<String> genres = snapshot.child(auth.getCurrentUser().getUid()).child("genres").getValue(ArrayList.class);
                                    user = new User(email, username, birthday, genres);
                                    System.out.println("Success");
                                } else {
                                    Toast.makeText(getApplicationContext(), "Datasnapshot does not exist", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    } else {
                        Toast.makeText(getApplicationContext(), "Incorrect credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}