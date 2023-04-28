package com.example.loginfirebase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Date;

public class CompleteReg extends Activity {
    TextInputEditText editTextHeight, editTestWeight,DOB;
    Button buttonComReg;

    FirebaseAuth mAuth;

    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);

// initialise the objects

        editTextHeight = findViewById(R.id.height);
        editTestWeight = findViewById(R.id.weight);
        buttonComReg = findViewById(R.id.btn_register);
        DOB = findViewById(R.id.dateofbirth);
        progressBar = findViewById(R.id.progressBar);



//add an onclick listener for the button
        buttonComReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//setting the visibility of the progress bar

                progressBar.setVisibility(View.VISIBLE);

//read the test from edit text

                String UserHeight, UserWeight,UserBirthday;
                UserHeight=String.valueOf(editTextHeight.getText());
                UserWeight= String.valueOf(editTestWeight.getText());
                UserBirthday= String.valueOf(DOB.getText());

// check if textboxes are not empty

                if(TextUtils.isEmpty(UserHeight)){
                    Toast.makeText(CompleteReg.this, "Enter Your Height in Cm", Toast.LENGTH_SHORT).show();
                }

                if(TextUtils.isEmpty(UserWeight)){
                    Toast.makeText(CompleteReg.this, "Enter Your Weight in Kg", Toast.LENGTH_SHORT).show();
                }

                if(TextUtils.isEmpty(UserBirthday)){
                    Toast.makeText(CompleteReg.this, "Enter Your Date of Birth", Toast.LENGTH_SHORT).show();
                }
// making the user
                FirebaseUser user = mAuth.getCurrentUser();
                if(user == null){
                    Intent intent = new Intent(getApplicationContext(),Login.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    String UserID = user.getUid();

                }
                //Todo: Add SQL Code
                                if (true){

                                    Intent intent= new Intent(getApplicationContext(),MainActivity.class);

//start the activity for that call

                                    startActivity(intent);
//finish the current Activity for that call
                                    finish();

                                }else {
// If sign in fails, display a message to the user.

                                    Toast.makeText(CompleteReg.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });


            }

        }
