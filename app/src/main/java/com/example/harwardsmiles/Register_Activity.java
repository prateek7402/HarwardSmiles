package com.example.harwardsmiles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.harwardsmiles.DataSets.DataSet_Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Register_Activity extends AppCompatActivity {
    EditText name, mob, add, state, dob;
    RadioGroup gender;
    ImageView register;
    int size, regno;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    ArrayList<DataSet_Users> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_);
        name = findViewById(R.id.reg_name);
        mob = findViewById(R.id.reg_mobile);
        add = findViewById(R.id.reg_address);
        state = findViewById(R.id.reg_state);
        dob = findViewById(R.id.reg_dob);
        gender = findViewById(R.id.radioGroup);
        register = findViewById(R.id.but_register);
        firebaseFirestore = FirebaseFirestore.getInstance();

     /*   DataSet_Users dataSet_users = new DataSet_Users("Prateek Suman","sumanprateek199@gmail.com","7508687070","Ganpati Apartment Patna","Bihar","Male","25/08/1998",auth.getUid(),1100001);
        firebaseFirestore.collection("Usres").document(auth.getUid()).set(dataSet_users).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(Register_Activity.this, "Registered", Toast.LENGTH_SHORT).show();
            }
        });*/

        firebaseFirestore.collection("Users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot documentSnapshots : task.getResult()) {
                        DataSet_Users dataSet_users = documentSnapshots.toObject(DataSet_Users.class);
                        Toast.makeText(Register_Activity.this, "" + dataSet_users.getUser_registration_number(), Toast.LENGTH_SHORT).show();
                        list.add(new DataSet_Users(dataSet_users.getName(), dataSet_users.getEmail(), dataSet_users.getMobile(), dataSet_users.getAddress(), dataSet_users.getState(), dataSet_users.getGender(), dataSet_users.getDob(), dataSet_users.getUser_auth_id(), dataSet_users.getUser_registration_number()));
                    }
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                size = list.size();
                Toast.makeText(getApplicationContext(), "" + size, Toast.LENGTH_SHORT).show();
                DataSet_Users dataSet_users = list.get(size-1);
                regno = dataSet_users.getUser_registration_number() + 1;
                final String nm = name.getText().toString();
                String mo = mob.getText().toString();
                String ad = add.getText().toString();
                String stat = state.getText().toString();
                String db = dob.getText().toString();
                int id = gender.getCheckedRadioButtonId();
                String email = getIntent().getStringExtra("Email");
                RadioButton radioButton = findViewById(id);
                String Gender = radioButton.getText().toString();
                if (TextUtils.isEmpty(nm) || TextUtils.isEmpty(mo) || TextUtils.isEmpty(ad) || TextUtils.isEmpty(stat) || TextUtils.isEmpty(db) || TextUtils.isEmpty(Gender)) {
                    Snackbar.make(findViewById(R.id.but_register), "enter all the fields", Snackbar.LENGTH_LONG).show();
                } else {
                    setContentView(R.layout.waiting);
                    size = list.size();
                    DataSet_Users dataSet_users2 = list.get(size - 1);
                    regno = dataSet_users2.getUser_registration_number() + 1;
                    DataSet_Users dataSet_users1 = new DataSet_Users(nm, email, mo, ad, stat, Gender, db, auth.getUid(), regno);
                    firebaseFirestore.collection("Users").document(auth.getUid()).set(dataSet_users1).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                         //   Snackbar.make(findViewById(R.id.but_register), "Hello " + nm, Snackbar.LENGTH_LONG).show();
                            Intent i = new Intent(getApplicationContext(), Main_Menu.class);
                            startActivity(i);
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            setContentView(R.layout.activity_register_);
                            Snackbar.make(findViewById(R.id.but_register), "Registration Failed please check the connection", Snackbar.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
    }
}
