package com.example.firebasesignindemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class SignupActivity extends AppCompatActivity {
private TextInputLayout emailfield,passfield;
private MaterialButton signupbtn;
private TextView singintext;
FirebaseAuth mAuth;
static Handler handler;
static String userEmail,userPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        emailfield=findViewById(R.id.email_inputLayout);
        passfield=findViewById(R.id.pass_inputLayout);
        signupbtn=findViewById(R.id.signup_btn);
        singintext=findViewById(R.id.signin_textview);
         handler=new Handler(Looper.getMainLooper());
        mAuth=FirebaseAuth.getInstance();
    }


//user signup
    public void signUpUserCredentials(View view) {
        userEmail=emailfield.getEditText().getText().toString();
        userPassword=passfield.getEditText().getText().toString();
        if(userEmail.length()<10){
            Toast.makeText(SignupActivity.this, "Please enter valid email", Toast.LENGTH_SHORT).show();
        }
        if(userPassword.length()<7){
            Toast.makeText(this, "password must have atleast 8 characters", Toast.LENGTH_SHORT).show();
        }
        else{
            mAuth.createUserWithEmailAndPassword(userEmail,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){

                    Toast.makeText(SignupActivity.this, "success", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignupActivity.this,HomeActivity.class));
                    finish();
                    }
                    else {
                        Toast.makeText(SignupActivity.this, "failed    ", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }






    }

    //this shows toast on main UI thread
    static void showThisToastOnUIThread(Context activityContext,String message){

        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(activityContext, message, Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void gotoSigninActivity(View view) {
        startActivity(new Intent(SignupActivity.this,SignInActivity.class));
        finish();
    }
}