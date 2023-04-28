package com.example.loginfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class UserHome extends AppCompatActivity {
    FirebaseAuth auth;
    Button Logout, LogMenu,GraphBtn;
    TextView Username;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userhome_page);
        auth = FirebaseAuth.getInstance();
        Username = findViewById(R.id.txtUsername);
        user = auth.getCurrentUser();
        Logout = findViewById(R.id.LogoutBtn);
        LogMenu = findViewById(R.id.logActBtn);
        GraphBtn = findViewById(R.id.GraphBtn);
        if(user == null){
            Intent intent = new Intent(getApplicationContext(),Login.class);
            startActivity(intent);
            finish();
        }
        else{
            Username.setText(user.getEmail());

        }
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
                finish();
            }
        });
        LogMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Log");
            }
        });
        GraphBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Graph");
            }
        });
    }
}