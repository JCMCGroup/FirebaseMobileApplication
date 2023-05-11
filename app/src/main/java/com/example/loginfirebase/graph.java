package com.example.loginfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.CircularArray;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class graph extends AppCompatActivity {
    FirebaseAuth auth;
    Button BtnHome,BtnLog;
    LineData lineData;
    Task<DocumentSnapshot> Test;
    LineChart lineChart;
    DocumentReference Weight;
    String userId;
    FirebaseUser user;
    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph);

        lineChart= findViewById(R.id.line_chart);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        BtnHome = findViewById(R.id.button);
        BtnLog = findViewById(R.id.button2);
        if(user == null){
            Intent intent = new Intent(getApplicationContext(),Login.class);
            startActivity(intent);
            finish();
        }
        else{


        }
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        lineData = new LineData(dataSets);
        ArrayList<Entry> pricesClose = new ArrayList<>();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("Weight").document(user.getEmail());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    System.out.println(document.get("User-Weight"));
                if (document.exists()) {
                    System.out.println("OK");
                    String R1 = "0";
                    if (R1 != null){
                        R1 = (String) document.get("1-Weight");
                        System.out.println("W1 Pulled");
                    } else {
                        R1 = "0";
                        System.out.println("W1 = 0");

                    }
                    String R2 = "0";
                    if (R2 != null){
                        R2 = (String) document.get("2-Weight");
                        System.out.println("W2 Pulled");
                    } else {
                        R2 = "0";
                        System.out.println("W2 = 0");
                    }
                    String R3 = "0";
                    if (R3 != null){
                        R3 = (String) document.get("3-Weight");
                        System.out.println("W3 Pulled");
                    } else {
                        R3 = "0";
                        System.out.println("W3 = 0");
                    }
                    String R4 = "0";
                    if (R4 != null){
                        R4 = (String) document.get("4-Weight");
                        System.out.println("W4 Pulled");
                    } else {
                        R4 = "0";
                        System.out.println("W4 = 0");
                    }
                    String R5 = "0";
                    if (R5 != null){
                        R5 = (String) document.get("5-Weight");
                        System.out.println("W5 Pulled");
                    } else {
                        R5 = "0";
                        System.out.println("W5 = 0");
                    }
                    Log.d("TAG", "Loading...");
                    String StartWeight;
                    StartWeight = (String) document.get("User-Weight");
                    pricesClose.add(new Entry(0, Float.parseFloat(StartWeight)));
                    System.out.println("Start Loaded");
                    pricesClose.add(new Entry(1, Float.parseFloat(R1)));
                    System.out.println("1 Loaded");
                    pricesClose.add(new Entry(2, Float.parseFloat(R2)));
                    System.out.println("2 Loaded");
                    pricesClose.add(new Entry(3, Float.parseFloat(R3)));
                    System.out.println("3 Loaded");
                    pricesClose.add(new Entry(4, Float.parseFloat(R4)));
                    System.out.println("4 Loaded");
                    pricesClose.add(new Entry(5, Float.parseFloat(R5)));
                    System.out.println("5 Loaded");
                    System.out.println("All Loaded");
                    Log.d("TAG", "onEvent called");


                            LineDataSet closeLineDataSet = new LineDataSet(pricesClose, "test");
                            closeLineDataSet.setDrawCircles(true);
                            closeLineDataSet.setCircleRadius(4);
                            closeLineDataSet.setDrawValues(true);
                            closeLineDataSet.setColor(Color.rgb(255, 165, 0));
                            closeLineDataSet.setCircleColor(Color.rgb(255, 165, 0));
                            dataSets.add(closeLineDataSet);
                            lineChart.setData(lineData);
                            Log.d("chart", "Number of entries: " + pricesClose.size());
                            lineChart.invalidate();

// Format X and Y axes
                            YAxis yAxisLeft = lineChart.getAxisLeft();
                            if (yAxisLeft != null) {
                                yAxisLeft.setAxisMinimum(50f); // set minimum value for y-axis
                                yAxisLeft.setAxisMaximum(300f); // set maximum value for y-axis
                                yAxisLeft.setGranularity(1f);
                                yAxisLeft.setLabelCount(10, true);
                            }

                            XAxis xAxis = lineChart.getXAxis();
                            if (xAxis != null) {
                                xAxis.setAxisMinimum(1f); // set minimum value for x-axis
                                xAxis.setAxisMaximum(5f); // set maximum value for x-axis
                                xAxis.setGranularity(10f);
                                xAxis.setLabelCount(10, false);
                            }


                        }
                    }
                }
            });
        BtnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), log.class);
                startActivity(intent);
                finish();
            }
        });
        BtnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), UserHome.class);
                startActivity(intent);
                finish();
            }
        });
        }
}


