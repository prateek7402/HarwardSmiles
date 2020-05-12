package com.example.harwardsmiles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_Activity extends AppCompatActivity {
EditText email,pass;
TextView register,forgot;
ImageView login;
FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
        email = findViewById(R.id.log_email);
        pass = findViewById(R.id.log_pas1);
        register = findViewById(R.id.log_register);
        login = findViewById(R.id.but_login);
        forgot = findViewById(R.id.log_forgot);
        auth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Email_Register_Activity.class);
                startActivity(i);
            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(getApplicationContext(),Forgot.class);
                startActivity(I);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String em = email.getText().toString();
                String ps = pass.getText().toString();
                if(TextUtils.isEmpty(em)||TextUtils.isEmpty(ps))
                {
                    Snackbar.make(findViewById(R.id.but_login), "Please enter the fields", Snackbar.LENGTH_LONG).show();
                }
                else
                {
                    auth.signInWithEmailAndPassword(em,ps).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            setContentView(R.layout.waiting);
                           // Snackbar.make(findViewById(R.id.but_login), "Login successful", Snackbar.LENGTH_LONG).show();
                            Intent i = new Intent(getApplicationContext(),Main_Menu.class);
                            startActivity(i);
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            setContentView(R.layout.activity_login_);
                            Snackbar.make(findViewById(R.id.but_login), "Something went wrong please try again", Snackbar.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
    }
}
