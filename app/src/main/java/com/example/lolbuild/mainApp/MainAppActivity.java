package com.example.lolbuild.mainApp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.lolbuild.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainAppActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app);
    }
}
