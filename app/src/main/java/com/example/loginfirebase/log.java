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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class log extends Activity {
    TextInputEditText UserHeartRate,UserWeight,UserKcal;
    Button LogHeartRateButton,LogWeightButton,LogKcals;
    TextView txtHR,txtWLoss,txtWeight,txtKcal;
    FirebaseAuth auth;
    FirebaseFirestore db;
    String R1,R2,R3,R4,R5,R6,R7,R8,R9,R10,StartWeight;
    ProgressBar progressBar;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log);

        // initialise the objects

        UserHeartRate = findViewById(R.id.tboxHR);
        UserWeight = findViewById(R.id.tboxweight);
        UserKcal = findViewById(R.id.tboxKcalConsumed);

        LogHeartRateButton = findViewById(R.id.btnlogHR);
        LogWeightButton = findViewById(R.id.btnlogweight);
        LogKcals = findViewById(R.id.btnlogKcals);

        txtHR= findViewById(R.id.txtAvrHR);
        txtKcal= findViewById(R.id.txtCals);
        txtWeight= findViewById(R.id.txtWeight);
        txtWLoss= findViewById(R.id.txtLost);


        LogHeartRateButton = findViewById(R.id.btnlogHR);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        progressBar = findViewById(R.id.progressBar);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("HR").document(user.getEmail());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        System.out.println("OK");
                        R1 = document.get("1").toString();
                        R2 = document.get("2").toString();
                        R3 = document.get("3").toString();
                        R4 = document.get("4").toString();
                        R5 = document.get("5").toString();
                        R6 = document.get("6").toString();
                        R7 = document.get("7").toString();
                        R8 = document.get("8").toString();
                        R9 = document.get("9").toString();
                        R10 = document.get("10").toString();
                        if (R10 != null){
                            txtHR.setText("Last Heart Rate Reading was: "+R10);
                        } else if (R9!=null) {
                            txtHR.setText("Last Heart Rate Reading was: "+R9);
                        }else if (R8!=null) {
                            txtHR.setText("Last Heart Rate Reading was: "+R9);
                        }else if (R7!=null) {
                            txtHR.setText("Last Heart Rate Reading was: "+R9);
                        }else if (R6!=null) {
                            txtHR.setText("Last Heart Rate Reading was: "+R9);
                        }else if (R5!=null) {
                            txtHR.setText("Last Heart Rate Reading was: "+R9);
                        }else if (R4!=null) {
                            txtHR.setText("Last Heart Rate Reading was: "+R9);
                        }else if (R3!=null) {
                            txtHR.setText("Last Heart Rate Reading was: "+R9);
                        }else if (R2!=null) {
                            txtHR.setText("Last Heart Rate Reading was: "+R9);
                        }else if (R1!=null) {
                            txtHR.setText("Last Heart Rate Reading was: "+R9);
                        }else{
                            Toast.makeText(log.this, "No Heart rate Readings Recorded", Toast.LENGTH_SHORT).show();
                            txtHR.setText("No Heart Rate Recorded:");
                        }
                    } else {
                        System.out.println("No Doc");
                        txtHR.setText("No Heart Data");
                    }
                } else {
                    System.out.println("get failed");
                    txtHR.setText("Heart Rate Retreval Error");
                }
            }
        });

        DocumentReference docRef1 = db.collection("Weight").document(user.getEmail());
        docRef1.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        System.out.println("OK");
                        R1 = document.get("1-Weight").toString();
                        R2 = document.get("2-Weight").toString();
                        R3 = document.get("3-Weight").toString();
                        R4 = document.get("4-Weight").toString();
                        R5 = document.get("5-Weight").toString();
                        R6 = document.get("6-Weight").toString();
                        R7 = document.get("7-Weight").toString();
                        R8 = document.get("8-Weight").toString();
                        R9 = document.get("9-Weight").toString();
                        R10 = document.get("10-Weight").toString();
                        StartWeight = document.get("User-Weight").toString();
                        if (R10 != null){
                            txtWeight.setText("Your Current Weight is: "+R10);
                            double Loss = Double.valueOf(StartWeight) - Double.valueOf(R10);
                            txtWLoss.setText("You Have Lost:"+String.valueOf(Loss)+"KG");
                        } else if (R9!=null) {
                            txtWeight.setText("Your Current Weight is: "+R9);
                            double Loss = Double.valueOf(StartWeight) - Double.valueOf(R10);
                            txtWLoss.setText("You Have Lost:"+String.valueOf(Loss)+"KG");
                        }else if (R8!=null) {
                            txtWeight.setText("Your Current Weight is: "+R8);
                            double Loss = Double.valueOf(StartWeight) - Double.valueOf(R10);
                            txtWLoss.setText("You Have Lost:"+String.valueOf(Loss)+"KG");
                        }else if (R7!=null) {
                            txtWeight.setText("Your Current Weight is: "+R7);
                            double Loss = Double.valueOf(StartWeight) - Double.valueOf(R10);
                            txtWLoss.setText("You Have Lost:"+String.valueOf(Loss)+"KG");
                        }else if (R6!=null) {
                            txtWeight.setText("Your Current Weight is: "+R6);
                            double Loss = Double.valueOf(StartWeight) - Double.valueOf(R10);
                            txtWLoss.setText("You Have Lost:"+String.valueOf(Loss)+"KG");
                        }else if (R5!=null) {
                            txtWeight.setText("Your Current Weight is: "+R5);
                            double Loss = Double.valueOf(StartWeight) - Double.valueOf(R10);
                            txtWLoss.setText("You Have Lost:"+String.valueOf(Loss)+"KG");
                        }else if (R4!=null) {
                            txtWeight.setText("Your Current Weight is: "+R4);
                            double Loss = Double.valueOf(StartWeight) - Double.valueOf(R10);
                            txtWLoss.setText("You Have Lost:"+String.valueOf(Loss)+"KG");
                        }else if (R3!=null) {
                            txtWeight.setText("Your Current Weight is: "+R3);
                            double Loss = Double.valueOf(StartWeight) - Double.valueOf(R10);
                            txtWLoss.setText("You Have Lost:"+String.valueOf(Loss)+"KG");
                        }else if (R2!=null) {
                            txtWeight.setText("Your Current Weight is: "+R2);
                            double Loss = Double.valueOf(StartWeight) - Double.valueOf(R10);
                            txtWLoss.setText("You Have Lost:"+String.valueOf(Loss)+"KG");
                        }else if (R1!=null) {
                            txtWeight.setText("Your Current Weight is: "+R1);
                            double Loss = Double.valueOf(StartWeight) - Double.valueOf(R10);
                            txtWLoss.setText("You Have Lost:"+String.valueOf(Loss)+"KG");
                        }else{
                            Toast.makeText(log.this, "No Heart rate Readings Recorded", Toast.LENGTH_SHORT).show();
                            txtWeight.setText("Your Current Weight is: "+StartWeight);
                            double Loss = 0;
                            txtWLoss.setText("You Have Lost:"+String.valueOf(Loss)+"KG");
                        }
                    } else {
                        System.out.println("No Doc");
                        txtWeight.setText("No Heart Data");
                    }
                } else {
                    System.out.println("get failed");
                    txtWeight.setText("Heart Rate Retreval Error");
                }
            }
        });

        DocumentReference docRef2 = db.collection("Cal").document(user.getEmail());
        docRef2.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        System.out.println("OK");
                        R1 = document.get("1").toString();
                        R2 = document.get("2").toString();
                        R3 = document.get("3").toString();
                        R4 = document.get("4").toString();
                        R5 = document.get("5").toString();
                        R6 = document.get("6").toString();
                        R7 = document.get("7").toString();
                        R8 = document.get("8").toString();
                        R9 = document.get("9").toString();
                        R10 = document.get("10").toString();
                        if (R10 != null){
                            UserKcal.setText("You Have Consumed : "+R10+"Kcals Today");
                        } else if (R9!=null) {
                            UserKcal.setText("You Have Consumed : "+R9+"Kcals Today");
                        }else if (R8!=null) {
                            UserKcal.setText("You Have Consumed : "+R8+"Kcals Today");
                        }else if (R7!=null) {
                            UserKcal.setText("You Have Consumed : "+R7+"Kcals Today");
                        }else if (R6!=null) {
                            UserKcal.setText("You Have Consumed : "+R6+"Kcals Today");
                        }else if (R5!=null) {
                            UserKcal.setText("You Have Consumed : "+R5+"Kcals Today");
                        }else if (R4!=null) {
                            UserKcal.setText("You Have Consumed : "+R4+"Kcals Today");
                        }else if (R3!=null) {
                            UserKcal.setText("You Have Consumed : "+R3+"Kcals Today");
                        }else if (R2!=null) {
                            UserKcal.setText("You Have Consumed : "+R2+"Kcals Today");
                        }else if (R1!=null) {
                            UserKcal.setText("You Have Consumed : "+R1+"Kcals Today");
                        }else{
                            Toast.makeText(log.this, "No Kcal Data", Toast.LENGTH_SHORT).show();
                            UserKcal.setText("No Kcal Data:");
                        }
                    } else {
                        System.out.println("No Doc");
                        UserKcal.setText("No Kcal Data");
                    }
                } else {
                    System.out.println("get failed");
                    UserKcal.setText("KCal Retreval Error");
                }
            }
        });

//add an onclick listener for the button
        LogHeartRateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //setting the visibility of the progress bar
                progressBar.setVisibility(View.VISIBLE);
                //read the test from edit text
                int HR;
                HR = Integer.parseInt(UserHeartRate.getText().toString());
                // check if textboxes are not empty
                DocumentReference docRef = db.collection("Weight").document(user.getEmail());
                docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            int numFields = documentSnapshot.getData().size();
                            Map<String, Object> HeartRate = new HashMap<>();
                            HeartRate.put(String.valueOf(numFields + 1), UserHeartRate);
                            db.collection("Weight").document(user.getEmail()).set(HeartRate);
                        }
                    }
                });
                if(TextUtils.isEmpty(UserHeartRate.getText().toString())){
                    Toast.makeText(log.this, "Please Enter Your Current Beats Per Min", Toast.LENGTH_SHORT).show();
                }
                if (true){

                                    Intent intent= new Intent(getApplicationContext(), log.class);

                                    //start the activity for that call

                                    startActivity(intent);
                                    //finish the current Activity for that call
                                    finish();

                                }else {
                                    // If sign in fails, display a message to the user.

                                    Toast.makeText(log.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

        LogWeightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //setting the visibility of the progress bar
                progressBar.setVisibility(View.VISIBLE);
                //read the test from edit text
                String Weight;
                Weight = String.valueOf(UserWeight.getText());
                // check if textboxes are not empty
                FirebaseFirestore db = null;
                DocumentReference docRef = db.collection("Weight").document(user.getEmail());
                docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            int numFields = documentSnapshot.getData().size();
                            Map<String, Object> WeightFS = new HashMap<>();
                            WeightFS.put(numFields + 1 + "-Weight", Weight);
                            db.collection("Weight").document(user.getEmail()).set(WeightFS);
                        }
                    }
                });
                if (TextUtils.isEmpty(Weight)) {
                    Toast.makeText(log.this, "Enter Your Weight in Kg", Toast.LENGTH_SHORT).show();
                }
                if (true) {

                    Intent intent = new Intent(getApplicationContext(), log.class);

                    //start the activity for that call

                    startActivity(intent);
                    //finish the current Activity for that call
                    finish();

                } else {
                    // If sign in fails, display a message to the user.

                    Toast.makeText(log.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();

                }
            }
        });

        LogKcals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //setting the visibility of the progress bar
                progressBar.setVisibility(View.VISIBLE);
                //read the test from edit text
                double KCal;
                KCal = Double.valueOf(UserKcal.getText().toString());
                // check if textboxes are not empty
                DocumentReference docRef = db.collection("Cals").document(user.getEmail());
                docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            int numFields = documentSnapshot.getData().size();
                            Map<String, Object> KCalFS = new HashMap<>();
                            KCalFS.put(String.valueOf(numFields + 1), KCal);
                            db.collection("Weight").document(user.getEmail()).set(KCalFS);
                        }
                    }
                });
                if(TextUtils.isEmpty(UserHeartRate.getText().toString())){
                    Toast.makeText(log.this, "Please Enter Your Current Beats Per Min", Toast.LENGTH_SHORT).show();
                }
                if (true){

                    Intent intent= new Intent(getApplicationContext(), log.class);

                    //start the activity for that call

                    startActivity(intent);
                    //finish the current Activity for that call
                    finish();

                }else {
                    // If sign in fails, display a message to the user.

                    Toast.makeText(log.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

        }

