 package com.example.chatapp;

 import android.content.Intent;
 import android.os.Bundle;
 import android.text.TextUtils;
 import android.view.View;
 import android.widget.Button;
 import android.widget.EditText;
 import android.widget.Toast;

 import androidx.annotation.NonNull;
 import androidx.appcompat.app.AppCompatActivity;
 import androidx.appcompat.widget.Toolbar;

 import com.google.android.gms.tasks.OnCompleteListener;
 import com.google.android.gms.tasks.Task;
 import com.google.firebase.auth.AuthResult;
 import com.google.firebase.auth.FirebaseAuth;

 public class Login extends AppCompatActivity {

     EditText email, pass;
     Button login;
     Toolbar toolbar;
     FirebaseAuth auth;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_login);
         toolbar = findViewById(R.id.toolbar);
         setSupportActionBar(toolbar);
         toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
         toolbar.setNavigationOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent i = new Intent(Login.this, MainActivity.class);
                 startActivity(i);
                 finish();
             }
         });
         email = findViewById(R.id.email);
         pass = findViewById(R.id.password);
         login = findViewById(R.id.login);

         auth = FirebaseAuth.getInstance();

         login.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 String text_email = email.getText().toString();
                 String text_pass = pass.getText().toString();

                 if(TextUtils.isEmpty(text_email) || TextUtils.isEmpty(text_pass)){
                     Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_LONG).show();
                 }
                 else{
                     auth.signInWithEmailAndPassword(text_email, text_pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                         @Override
                         public void onComplete(@NonNull Task<AuthResult> task) {
                             if(task.isSuccessful()){
                                 Intent i = new Intent(Login.this, Home.class);
                                 i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                 startActivity(i);
                                 finish();
                             }
                             else{
                                 Toast.makeText(getApplicationContext(), "Authentication failed", Toast.LENGTH_LONG).show();
                             }
                         }
                     });
                 }
             }
         });
     }
 }