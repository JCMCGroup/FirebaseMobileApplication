package com.example.loginfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    TextInputEditText editTextEmail, editTestPassword;
    Button buttonLogin;

    FirebaseAuth mAuth;

    ProgressBar progressBar;

    TextView textView;


    @Override
    public void onStart() {
        super.onStart();
// Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), UserHome.class);
//start activity for this call
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth=FirebaseAuth.getInstance();

// initialise the objects

        editTextEmail = findViewById(R.id.email);
        editTestPassword = findViewById(R.id.password);
        buttonLogin = findViewById(R.id.btn_login);

        progressBar = findViewById(R.id.progressBar);

        textView = findViewById(R.id.registerNow);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(getApplicationContext(),Register.class);

//start the activity for that call

                startActivity(intent);
//finish the current Activity for that call
                finish();

            }
        });


// create an onclick listener for the button

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//set visibility of progress bar

                progressBar.setVisibility(View.VISIBLE);

//READ TEXT FROM THE EDIT TEXT

                String email, password;
                email=String.valueOf(editTextEmail.getText());
                password=String.valueOf(editTestPassword.getText());

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Login.this, "Enter email", Toast.LENGTH_SHORT).show();

                }

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(Login.this, "Enter password", Toast.LENGTH_SHORT).show();

                }


//call a function called sign in with email and password, from firebase

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {

                                    Toast.makeText(Login.this, "Login Successful.",
                                            Toast.LENGTH_SHORT).show();

//create intent to open main activity

                                    Intent intent = new Intent(getApplicationContext(), UserHome.class);
                                    startActivity(intent);
                                    finish();

                                } else {


                                    Toast.makeText(Login.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

            }
        });
    }
}