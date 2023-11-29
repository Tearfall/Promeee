package com.mobprog.promee_main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    Button createTaskBtn, profileBtn;
    FirebaseUser user;
    private DatabaseReference _users;
    private DatabaseReference _userData;
    private DatabaseReference _taskData;
    String userId, userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        createTaskBtn = findViewById(R.id.createTaskBtn);
        profileBtn = findViewById(R.id.profileBtn);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        if(user == null){
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        }


        _users = FirebaseDatabase.getInstance().getReference("users");
        _userData = _users.child(userId);
        _taskData = _userData.child("todo");

        createTaskBtn.setOnClickListener(view -> {
            goToCreateTask();
        });
        profileBtn.setOnClickListener(view -> {
            goToProfile();
        });

    }

    void goToCreateTask(){
        Intent intent = new Intent(getApplicationContext(), CreateTaskActivity.class);
        startActivity(intent);
        finish();
    }
    void goToProfile(){
        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
        startActivity(intent);
        finish();
    }

}