package com.mobprog.promee_main;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    EditText usernameEt, emailEt, passwordEt;
    Button registerBtn;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    ProgressBar progressBar;
    TextView loginTV;
    String userNameInput, emailInput, passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEt = findViewById(R.id.userNameEt);
        emailEt = findViewById(R.id.emailEt);
        passwordEt = findViewById(R.id.passwordEt);
        registerBtn = findViewById(R.id.registerBtn);
        progressBar = findViewById(R.id.progressBar);
        loginTV = findViewById(R.id.loginTV);
        mAuth = FirebaseAuth.getInstance();

        loginTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
    @Override
    public void onStart() {
        super.onStart();
        currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            gotoHome();
        }

        registerBtn.setOnClickListener(view -> {
            progressBar.setVisibility(View.VISIBLE);
            userNameInput = usernameEt.getText().toString();
            emailInput = emailEt.getText().toString();
            passwordInput = passwordEt.getText().toString();

            if(userNameInput.isEmpty() || emailInput.isEmpty() || passwordInput.isEmpty()){
                Toast.makeText(MainActivity.this, "Some fields are empty.", Toast.LENGTH_SHORT).show();
                return;
            }
            UserData userData = new UserData(userNameInput,emailInput);
            register(emailInput,passwordInput);
        });
    }
    public void gotoHome(){
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("USER_NAME", this.userNameInput);
        startActivity(intent);
        finish();
    }
    public void register(String email,String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (user != null) {
                                String userId = user.getUid();
                                // Now 'userId' contains the unique identifier for the newly created user
                                Toast.makeText(MainActivity.this, "User ID:"+userId, Toast.LENGTH_SHORT).show();
                            }
                            gotoHome();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}