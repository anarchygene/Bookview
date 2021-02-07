package com.example.bookview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {
    FirebaseAuth auth;
    EditText etDate;
    EditText emailinput, passwordinput, usernameinput, birthdateinput;
    CheckBox adventure, crime, horror, mystery, romance, scifi;
    ArrayList<String> genres = new ArrayList<>();
    ProgressBar progressBar;
    Button registerBtn, cancelBtn;
    public User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        user = (User)getIntent().getSerializableExtra("userInfo");
        if(user == null) {
            System.out.println("User is empty");
        } else {
            System.out.println(user);
        }

        //Date picker
        etDate = findViewById(R.id.registerBirthdayInput);
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        RegisterActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String monthString = Integer.toString(month);
                        String dayString = Integer.toString(day);
                        if (month < 10) {
                            monthString = "0" + monthString;
                        }
                        if (day < 10) {
                            dayString = "0" + dayString;
                        }
                        String date = year + "-" + monthString + "-" + dayString;
                        etDate.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        //Register
        auth = FirebaseAuth.getInstance();
        emailinput = findViewById(R.id.registerEmailInput);
        passwordinput = findViewById(R.id.registerPasswordInput);
        usernameinput = findViewById(R.id.registerUsernameInput);
        birthdateinput = findViewById(R.id.registerBirthdayInput);
        registerBtn = findViewById(R.id.registerRegisterBtn);
        cancelBtn = findViewById(R.id.registerRegisterBtn2);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        adventure = findViewById(R.id.registerAdventureCB);
        crime = findViewById(R.id.registerCrimeCB);
        horror = findViewById(R.id.registerHorrorCB);
        mystery = findViewById(R.id.registerMysteryCB);
        romance = findViewById(R.id.registerRomanceCB);
        scifi = findViewById(R.id.registerSciFiCB);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();;
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                i.putExtra("userInfo", user);
                startActivity(i);
            }
        });

    }

    private void registerUser() {
        String email = emailinput.getText().toString();
        String password = passwordinput.getText().toString();
        String username = usernameinput.getText().toString();
        String birthday = birthdateinput.getText().toString();
        if (adventure.isChecked()) { genres.add("Adventure"); }
        if (crime.isChecked()) { genres.add("Crime"); }
        if (horror.isChecked()) { genres.add("Horror"); }
        if (mystery.isChecked()) { genres.add("Mystery"); }
        if (romance.isChecked()) { genres.add("Romance"); }
        if (scifi.isChecked()) { genres.add("Sci-Fi"); }
        if (username.isEmpty() || password.isEmpty() || email.isEmpty() || birthday.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Empty input fields detected", Toast.LENGTH_SHORT).show();
        }  else if (password.length() < 6) {
            Toast.makeText(getApplicationContext(), "Password must be more than 6 characters", Toast.LENGTH_SHORT).show();
        } else {
            System.out.println("Register btn clicked");
            progressBar.setVisibility(View.VISIBLE);
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    System.out.println("Trying to run createUser");
                    if(task.isSuccessful()) {
                        System.out.println("createUser success");
                        User userInfo = new User(email, username, birthday, genres);
                        FirebaseDatabase.getInstance().getReference("users")
                                .child(auth.getCurrentUser().getUid())
                                .setValue(userInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()) {
                                    Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                                    i.putExtra("userInfo", user);
                                    Toast.makeText(getApplicationContext(), "Successful in registering", Toast.LENGTH_SHORT).show();
                                    startActivity(i);
                                } else {
                                    Toast.makeText(getApplicationContext(), "Failed register", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                    else{
                        System.out.println(task.getException().getMessage());
                    }
                }
            });
        }
    }



}