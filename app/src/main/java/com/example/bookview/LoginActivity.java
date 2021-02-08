package com.example.bookview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.*;

import java.io.Serializable;
import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    DatabaseReference reference, bookreference;
    FirebaseAuth auth;
    EditText emailinput, passwordinput;
    Button registerBtn, loginBtn;
    public User user;
    public ArrayList<Book> booklist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = (User)getIntent().getSerializableExtra("userInfo");
        booklist = (ArrayList<Book>)getIntent().getSerializableExtra("bookInfo");

        auth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference("users");
        registerBtn = findViewById(R.id.btnRegister);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                i.putExtra("userInfo", user);
                i.putExtra("bookInfo", (Serializable)booklist);
                startActivity(i);
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
                        String pKey = auth.getCurrentUser().getUid();
                        Query getUserInfo = reference.child(pKey);
                        getUserInfo.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()) {
                                    String username = snapshot.child("username").getValue(String.class);
                                    String birthday = snapshot.child("birthday").getValue(String.class);
                                    ArrayList<String> genres = (ArrayList<String>)snapshot.child("genres").getValue();
                                    user = new User(email, username, birthday, genres);
                                    booklist = new ArrayList<Book>();
                                    bookreference = FirebaseDatabase.getInstance().getReference("books");
                                    bookreference.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            //System.out.println(snapshot.getChildrenCount());
                                            for (DataSnapshot childsnapshot: snapshot.getChildren()) {
                                                String author = childsnapshot.child("author").getValue(String.class);
                                                String imageURI = childsnapshot.child("imageURI").getValue(String.class);
                                                String title = childsnapshot.child("title").getValue(String.class);
                                                String category = childsnapshot.child("category").getValue(String.class);
                                                int ratingNo = childsnapshot.child("ratingNo").getValue(int.class);
                                                booklist.add(new Book(title, imageURI, author, category, ratingNo));
                                            }
                                            System.out.println(booklist);
                                            Intent i = (new Intent(LoginActivity.this, MainActivity.class));
                                            i.putExtra("userInfo", user);
                                            i.putExtra("bookInfo", (Serializable)booklist);
                                            startActivity(i);
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });

                                } else {
                                    Toast.makeText(getApplicationContext(), "Datasnapshot does not exist", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    } else {
                        Toast.makeText(getApplicationContext(), "Incorrect credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

}