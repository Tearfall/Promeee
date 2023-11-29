package com.mobprog.promee_main;

import androidx.appcompat.app.AppCompatActivity;

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

public class CreateTaskActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    Button createBtn;
    FirebaseUser user;
    EditText taskInput, taskDate, taskTime, taskNote;
    private DatabaseReference _users;
    private DatabaseReference _userData;
    private DatabaseReference _taskData;
    String userId;

    TextView gotoHomeTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        taskInput = findViewById(R.id.enterTaskET);
        taskDate = findViewById(R.id.enterDateET);
        taskTime = findViewById(R.id.enterTimeET);
        taskNote = findViewById(R.id.enterNoteET);

        createBtn = findViewById(R.id.createBtn);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        userId = user.getUid();
        _users = FirebaseDatabase.getInstance().getReference("users");
        _userData = _users.child(userId);
        _taskData = _userData.child("todo");

        createBtn.setOnClickListener(view -> {
            writeNewTask();
        });

        gotoHomeTv = findViewById(R.id.gotoHomeTv);
        gotoHomeTv.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
            finish();
        });

    }
    void writeNewTask(){
        String title = taskInput.getText().toString();
        String date = taskDate.getText().toString();
        String time = taskTime.getText().toString();
        String note = taskNote.getText().toString();

        TaskData taskData = new TaskData(title,date, time, note);
        DatabaseReference _todoItem = _taskData.push();
        _todoItem.setValue(taskData);
    }
}