package com.example.choiyorim.self_recyelrview.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.choiyorim.self_recyelrview.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Register extends AppCompatActivity {
    private EditText email;
    private EditText passWord;
    private EditText name;
    private EditText degree;
    private EditText major;
    private Button btn;
    private FirebaseAuth mAuth;
    private String TAG = "";
    private static String id;
    private static String paass;
    public static HashMap<String,String>  values;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        email = (EditText) findViewById(R.id.registe_id);
        passWord = (EditText) findViewById(R.id.registe_password);
        btn = (Button) findViewById(R.id.register);
        name =(EditText)findViewById(R.id.name);
        degree = (EditText)findViewById(R.id.degree);
        major = (EditText)findViewById(R.id.major);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {

                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                id = email.getText().toString();
                paass = passWord.getText().toString();
                register(id,paass);

            }
        });
    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    private void register(String email,String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        if (!task.isSuccessful()) {
                            Toast.makeText(Register.this, "Authentication failed",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            dataBase();
                            Intent intent = new Intent(Register.this, Login.class);
                            startActivity(intent);
                        }
                    }
                });
    }
    private  String StringTest(String email){
        String email1 = email;
        int inx = email1.indexOf(".");
        String email2 = email1.substring(0,inx);
        return email2;
    }
    private void dataBase(){
        String name1 = name.getText().toString();
        String degree1 = degree.getText().toString();
        String major1 = major.getText().toString();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("member");
        String key = StringTest(id);
        values = new HashMap();
        values.put("name",name1);
        values.put("degree",degree1);
        values.put("major",major1);
        myRef.child(key).setValue(values);
        }
    }

