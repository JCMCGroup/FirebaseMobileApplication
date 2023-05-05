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
    TextView Username, BMITxt;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userhome_page);
        auth = FirebaseAuth.getInstance();
        Username = findViewById(R.id.txtUsername);
        BMITxt = findViewById(R.id.txtBMI);
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
                        BMITxt.setText((CharSequence) document.get("User-BMI").toString()); //TODO: Fix Output Curent Output is Eg. {User-BMI=141.0}
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
