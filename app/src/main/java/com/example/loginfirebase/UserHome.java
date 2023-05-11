package com.example.loginfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
public class UserHome extends AppCompatActivity {
    FirebaseAuth auth;
    Button Logout, LogMenu, GraphBtn;
    TextView Username, BMITxt,Cansumed,Cals,Exercise;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userhome_page);
        auth = FirebaseAuth.getInstance();
        Username = findViewById(R.id.txtUsername);
        BMITxt = findViewById(R.id.txtBMI);
        Cansumed = findViewById(R.id.txtTodaySpCal);
        Cals = findViewById(R.id.txtTodayCal);
        Exercise = findViewById(R.id.txtxTodayMins);

        user = auth.getCurrentUser();
        Logout = findViewById(R.id.LogoutBtn);
        LogMenu = findViewById(R.id.logActBtn);
        GraphBtn = findViewById(R.id.GraphBtn);
        if (user == null) {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        } else {
            Username.setText(user.getEmail());

        }
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("BMI").document(user.getEmail());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        System.out.println("OK");
                        BMITxt.setText((CharSequence) "Your BMI is: "+document.get("User-BMI").toString()); //TODO: Fix Output Curent Output is Eg. {User-BMI=141.0}
                    } else {
                        System.out.println("No Doc");
                    }
                } else {
                    System.out.println("get failed");
                }
            }
        });
        DocumentReference docRef1 = db.collection("Cals").document(user.getEmail());
        docRef1.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task1) {
                if (task1.isSuccessful()) {
                    DocumentSnapshot document1 = task1.getResult();
                    if (document1.exists()) {
                        System.out.println("OK");
                        Cansumed.setText((CharSequence) "Today You Have Consumed: "+document1.get("User-Consumed").toString()+" Kcals");
                        Cals.setText((CharSequence)  "Today You Have Expended: "+document1.get("User-Cal").toString()+" Kcals");
                    } else {
                        System.out.println("No Doc");
                    }
                } else {
                    System.out.println("get failed");
                }
            }
        });
        DocumentReference docRef2 = db.collection("Exercise").document(user.getEmail());
        docRef2.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task2) {
                if (task2.isSuccessful()) {
                    DocumentSnapshot document2 = task2.getResult();
                    if (document2.exists()) {
                        System.out.println("OK");
                        Exercise.setText((CharSequence)  "Today You Have Completed: "+document2.get("Exercise").toString()+" Minutes of Exercise");
                    } else {
                        System.out.println("No Doc");
                    }
                } else {
                    System.out.println("get failed");
                }
            }
        });
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });
        LogMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Log");
                Intent intent = new Intent(getApplicationContext(), log.class);
                startActivity(intent);
                finish();
            }
        });
        GraphBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), graph.class);
                startActivity(intent);
                finish();
                System.out.println("Graph");
            }
        });
    }
}
