package com.example.firebasesignindemo;

import static com.example.firebasesignindemo.SignupActivity.showThisToastOnUIThread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SignInActivity extends AppCompatActivity {
    static String userEmail,userPassword;
    private TextInputLayout emailfield2,passfield2;
    FirebaseAuth  mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        emailfield2=findViewById(R.id.email_inputLayout2);
        passfield2=findViewById(R.id.pass_inputLayout2);
        mAuth=FirebaseAuth.getInstance();


    }
//user signin
    public void signInUserCredentials(View view) {
        userEmail=emailfield2.getEditText().getText().toString();
        userPassword=passfield2.getEditText().getText().toString();
        if(userEmail.length()<10){
            Toast.makeText(SignInActivity.this, "Please enter valid email", Toast.LENGTH_SHORT).show();
        }
        if(userPassword.length()<7){
            Toast.makeText(this, "password must have atleast 8 characters", Toast.LENGTH_SHORT).show();
        }else{
            ExecutorService executor= Executors.newSingleThreadExecutor();
            //working on background thread
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    userEmail=emailfield2.getEditText().getText().toString();
                    userPassword=passfield2.getEditText().getText().toString();
                    if((userEmail!=null)&&(userPassword!=null)){
                        mAuth.signInWithEmailAndPassword(userEmail,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                showThisToastOnUIThread(SignInActivity.this,"Signin successfull");
                                    startActivity(new Intent(SignInActivity.this,HomeActivity.class));
                                    finish();
                                }else{
                                    showThisToastOnUIThread(SignInActivity.this,"signin failed");
                                }
                            }
                        });
                    }
                }
            });
        }

    }

    public void gotoSignUpActivity(View view) {
        startActivity(new Intent(SignInActivity.this,SignupActivity.class));
        finish();
    }
}