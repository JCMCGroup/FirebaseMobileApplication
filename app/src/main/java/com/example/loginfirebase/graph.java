package com.example.loginfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
    Button back,Logmenu;
    LineData lineData;
    Task<DocumentSnapshot> Test;
    LineChart lineChart;
    DocumentReference Weight;
    String userId;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph);

        lineChart= findViewById(R.id.line_chart);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        back = findViewById(R.id.button);
        Logmenu = findViewById(R.id.button2);
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
        CollectionReference weightCollectionRef = db.collection("Weight");
        Log.d("TAG", "Loading...");
        weightCollectionRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                Log.d("TAG", "Number of documents: " + documentSnapshots.size());
                Log.d("TAG", "onEvent called");
                if (e != null) {
                    Log.w("TAG", "Listen failed.", e);
                    return;
                }

                for (DocumentSnapshot document : documentSnapshots) {
                    Log.d("TAG", "DocumentSnapshot data: " + document.getString("User-Weight")); // Log the document snapshot

                    DocumentReference docRef = document.getReference();
                    docRef.get().addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            DocumentSnapshot documentSnapshot = task.getResult();

                            if (documentSnapshot != null && documentSnapshot.exists()) {
                                String Startweight = document.getString("Start-Weight");
                                String weight1 = document.getString("1-Weight");
                                String weight2 = document.getString("2-Weight");
                                String weight3 = document.getString("3-Weight");
                                String weight4 = document.getString("4-Weight");
                                String weight5 = document.getString("5-Weight");
                                String weight6 = document.getString("6-Weight");
                                String weight7 = document.getString("7-Weight");
                                // Do something with the weight value
                            } else {
                                Log.d("Failed", "weight");
                            }
                        } else {
                            Log.d("TAG", "Error getting document: ", task.getException());
                        }
                    });
                }


                pricesClose.add(new Entry(1, 15));
                pricesClose.add(new Entry(2, 18));
                pricesClose.add(new Entry(3, 12));
                pricesClose.add(new Entry(4, 19));
                pricesClose.add(new Entry(5, 10));


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
                    yAxisLeft.setAxisMinimum(0f); // set minimum value for y-axis
                    yAxisLeft.setAxisMaximum(20f); // set maximum value for y-axis
                    yAxisLeft.setGranularity(1f);
                    yAxisLeft.setLabelCount(10, true);
                }

                XAxis xAxis = lineChart.getXAxis();
                if (xAxis != null) {
                    xAxis.setAxisMinimum(1f); // set minimum value for x-axis
                    xAxis.setAxisMaximum(3f); // set maximum value for x-axis
                    xAxis.setGranularity(1f);
                    xAxis.setLabelCount(10, false);
                }


            }
        });
    }
}
