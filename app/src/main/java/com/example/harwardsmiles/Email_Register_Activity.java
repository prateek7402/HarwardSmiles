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
import android.widget.Toast;

import com.example.harwardsmiles.DataSets.DataSet_Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Email_Register_Activity extends AppCompatActivity {
EditText email,pass,pass2;
TextView login;
ImageView register;
FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email__register_);
        email = findViewById(R.id.reg_email);
        pass  =findViewById(R.id.reg_pas1);
        pass2 = findViewById(R.id.reg_pass2);
        login = findViewById(R.id.red_login);
        register = findViewById(R.id.but_register);
        auth = FirebaseAuth.getInstance();

       /* FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("Usres").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot documentSnapshots : task.getResult()) {
                        DataSet_Users dataSet_users = documentSnapshots.toObject(DataSet_Users.class);
                        Toast.makeText(getApplicationContext(), ""+dataSet_users.getUser_registration_number(), Toast.LENGTH_SHORT).show();
                        //list.add(new DataSet_Users(dataSet_users.getName(), dataSet_users.getEmail(), dataSet_users.getMobile(), dataSet_users.getAddress(), dataSet_users.getState(), dataSet_users.getGender(), dataSet_users.getDob(), dataSet_users.getUser_auth_id(), dataSet_users.getUser_registration_number()));
                    }
                }
            }
        });
*/
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Email = email.getText().toString();
                String pas = pass.getText().toString();
                String pas2 = pass2.getText().toString();
                if(TextUtils.isEmpty(Email)||TextUtils.isEmpty(pas)||TextUtils.isEmpty(pas2))
                {
                    Snackbar.make(findViewById(R.id.but_register), "enter all the fields", Snackbar.LENGTH_LONG).show();
                }
                else
                {
                    if(pas.equals(pas2)==false||pas.length()<=6)
                    {
                        Snackbar.make(findViewById(R.id.but_register), "password does't match or the length of password is less than 6", Snackbar.LENGTH_LONG).show();
                    }
                    else
                    {
                        auth.createUserWithEmailAndPassword(Email,pas).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                setContentView(R.layout.waiting);
                              //  Snackbar.make(findViewById(R.id.but_register), "registered successfully", Snackbar.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(),Register_Activity.class);
                                intent.putExtra("Email",Email);
                                startActivity(intent);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                setContentView(R.layout.activity_email__register_);
                                Snackbar.make(findViewById(R.id.but_register), "Registry Failed check your connection", Snackbar.LENGTH_LONG).show();
                            }
                        });
                    }
                }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Login_Activity.class);
                startActivity(i);
            }
        });
    }
}
