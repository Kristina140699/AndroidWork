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

public class RegistrationActivity extends AppCompatActivity {

    private TextInputEditText userName, Password, confirmPassword;
    private Button registerBtn;
    private ProgressBar loadingPB;
    private TextView loginTV;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        userName = findViewById(R.id.idEditUserName1);
        Password = findViewById(R.id.idEditPwd);
        confirmPassword = findViewById(R.id.idEditCnfmPswd);
        registerBtn = findViewById(R.id.idBtnRegister);
        loadingPB = findViewById(R.id.idPBLoading);
        loginTV = findViewById(R.id.idTVlogin);

        mAuth = FirebaseAuth.getInstance();

        loginTV.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegistrationActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingPB.setVisibility(View.VISIBLE);
                String userNameStr = userName.getText().toString();
                String pwd = Password.getText().toString();
                String cnfPwd = confirmPassword.getText().toString();

                if(!pwd.equals(cnfPwd)){
                    Toast.makeText(RegistrationActivity.this, "Invalid Password", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(userNameStr) && TextUtils.isEmpty(pwd) && TextUtils.isEmpty(cnfPwd)){
                    Toast.makeText(RegistrationActivity.this, "Please insert your credentials!!", Toast.LENGTH_SHORT).show();
                }
                else{
                    mAuth.createUserWithEmailAndPassword(userNameStr, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                loadingPB.setVisibility(View.GONE);
                                Toast.makeText(RegistrationActivity.this, "User Registered Successfully!!", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(RegistrationActivity.this,LoginActivity.class);
                            startActivity(i);
                            finish();

                            }else{
                               loadingPB.setVisibility(View.GONE);
                                Toast.makeText(RegistrationActivity.this, "Fail to register user!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }

            }
        });
    }
}