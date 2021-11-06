package com.example.userauthproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText uNameLogin, passLogin;
    private Button loginBtn;
    private ProgressBar loadingPB_login;
    private TextView registerTV;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        uNameLogin = findViewById(R.id.idEditUserName1);
        passLogin = findViewById(R.id.idEditPwd);
        loginBtn = findViewById(R.id.idBtnLogin);
        loadingPB_login = findViewById(R.id.idPBLoading);

        registerTV = findViewById(R.id.idTVRegister);
        mAuth = FirebaseAuth.getInstance();


       registerTV.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(i);
           }
       });
       loginBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               loadingPB_login.setVisibility(View.VISIBLE);
               String UserNameLogin = uNameLogin.getText().toString();
               String passwordLogin = passLogin.getText().toString();
               if(TextUtils.isEmpty(UserNameLogin) && TextUtils.isEmpty(passwordLogin)){
                   Toast.makeText(LoginActivity.this, "Please enter your credentials", Toast.LENGTH_SHORT).show();
                    return;
               }else{
                   mAuth.signInWithEmailAndPassword(UserNameLogin, passwordLogin).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                         if(task.isSuccessful()){
                             loadingPB_login.setVisibility(View.GONE);
                             Toast.makeText(LoginActivity.this, "Login Successful!!", Toast.LENGTH_SHORT).show();
                             Intent i = new Intent(LoginActivity.this, MainActivity.class);
                             startActivity(i);
                             finish();
                         }else{
                             loadingPB_login.setVisibility(View.GONE);
                             Toast.makeText(LoginActivity.this, "Failed to Login!", Toast.LENGTH_SHORT).show();
                         }
                       }
                   });
               }

           }
       });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user!=null){

            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
            this.finish();
        }
    }
}