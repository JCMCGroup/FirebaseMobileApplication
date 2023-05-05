package com.example.loginfirebase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.AggregateQuery;
import com.google.firebase.firestore.AggregateQuerySnapshot;
import com.google.firebase.firestore.AggregateSource;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentChange.Type;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.MetadataChanges;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.Query.Direction;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.ServerTimestamp;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.firestore.Source;
import com.google.firebase.firestore.Transaction;
import com.google.firebase.firestore.WriteBatch;

import java.util.HashMap;
import java.util.Map;

public class CompleteReg extends Activity {
    TextInputEditText editTextHeight, editTestWeight,DOB;
    Button buttonComReg;

    FirebaseAuth auth;

    ProgressBar progressBar;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);

// initialise the objects

        editTextHeight = findViewById(R.id.height);
        editTestWeight = findViewById(R.id.weight);
        buttonComReg = findViewById(R.id.btn_register);
        DOB = findViewById(R.id.dateofbirth);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
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
                    //Todo: Add SQL Code
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                    Map<String, Object> Height = new HashMap<>();
                Map<String, Object> Weight = new HashMap<>();
                Map<String, Object> BMI = new HashMap<>();
                Map<String, Object> UserBDay = new HashMap<>();
                    Height.put("User-Height", UserHeight);
                    Weight.put("User-Weight", UserWeight);
                    int intUserWeight = Integer.parseInt(UserWeight);
                    int intUserHeightCM = Integer.parseInt(UserHeight);
                    double intUserHeightM = intUserHeightCM/100;
                    double BMICalc = intUserWeight / (intUserHeightM*intUserHeightM);
                    BMI.put("User-BMI", BMICalc);
                    UserBDay.put("User-Birthday",UserBirthday);
                    db.collection("Height").document(user.getEmail()).set(Height);
                    db.collection("Weight").document(user.getEmail()).set(Weight);
                    db.collection("BMI").document(user.getEmail()).set(BMI);
                    db.collection("Birthday").document(user.getEmail()).set(UserBDay);
                if (true){

                                    Intent intent= new Intent(getApplicationContext(),UserHome.class);

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
