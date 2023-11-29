package com.mobprog.promee_main;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    Button logoutBtn;
    TextView details, gotoHomeTv;
    FirebaseUser user;
    private DatabaseReference _users;
    private DatabaseReference _userData;
    private DatabaseReference _taskData;
    String userId, userName;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mAuth = FirebaseAuth.getInstance();
        logoutBtn = findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        details = findViewById(R.id.userDetailsTV);
        user = mAuth.getCurrentUser();

        details.setText(user.getEmail());
        userId = user.getUid();

        _users = FirebaseDatabase.getInstance().getReference("users");
        _userData = _users.child(userId);
        _userData.setValue(userName);
        _taskData = _userData.child("todo");

        gotoHomeTv = findViewById(R.id.gotoHomeTv);
        gotoHomeTv.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
            finish();
        });
    }

}