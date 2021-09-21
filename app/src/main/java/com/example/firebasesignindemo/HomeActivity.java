package com.example.firebasesignindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {
FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        auth=FirebaseAuth.getInstance();
        setTitle("Home"); //setting action bar title
    }

    public void signOutCurrentUser(View view) {
        auth.signOut();
        SignupActivity.showThisToastOnUIThread(HomeActivity.this,"sign out ");
        startActivity(new Intent(HomeActivity.this,SignupActivity.class));
        finish();
    }
}