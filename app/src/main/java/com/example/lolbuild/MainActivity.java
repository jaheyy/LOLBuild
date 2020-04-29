package com.example.lolbuild;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private static String lolVersion;
    private static JSONObject champions;

    public static String getLolVersion() {
        return lolVersion;
    }

    public static void setLolVersion(String lolVersion) {
        MainActivity.lolVersion = lolVersion;
    }

    public static JSONObject getChampions() {
        return champions;
    }

    public static void setChampions(JSONObject champions) {
        MainActivity.champions = champions;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
