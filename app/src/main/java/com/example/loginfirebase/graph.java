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

import java.util.ArrayList;

public class graph extends AppCompatActivity {
    FirebaseAuth auth;
    Button back,Logmenu;
    LineData lineData;
    LineChart lineChart;

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
        pricesClose.add(new Entry(4f, 9f));


        LineDataSet closeLineDataSet = new LineDataSet(pricesClose, "test");
        closeLineDataSet.setDrawCircles(true);
        closeLineDataSet.setCircleRadius(4);
        closeLineDataSet.setDrawValues(false);
        closeLineDataSet.setLineWidth(3);
        closeLineDataSet.setColor(Color.rgb(255, 165, 0));
        closeLineDataSet.setCircleColor(Color.rgb(255, 165, 0));
        dataSets.add(closeLineDataSet);
        lineChart.setData(lineData);

// Format X and Y axes
        XAxis xAxis = lineChart.getXAxis();
        if (xAxis != null) {
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setGranularity(1f);
            xAxis.setLabelCount(5, true);
        }

        YAxis yAxisLeft = lineChart.getAxisLeft();
        if (yAxisLeft != null) {
            yAxisLeft.setGranularity(1f);
            yAxisLeft.setLabelCount(5, true);
        }

        YAxis yAxisRight = lineChart.getAxisRight();
        yAxisRight.setGranularity(1f);
        if (yAxisRight != null) {
            yAxisRight.setGranularity(1f);
            yAxisRight.setLabelCount(5, true);
        }

        lineChart.invalidate();
    }
}
