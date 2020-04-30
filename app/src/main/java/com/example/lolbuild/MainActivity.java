package com.example.lolbuild;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.example.lolbuild.jobs.FetchLolVersion;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static String lolVersion;
    private static ArrayList<String> champions;

    public static String getLolVersion() {
        return lolVersion;
    }

    public static void setLolVersion(String lolVersion) {
        MainActivity.lolVersion = lolVersion;
    }

    public static ArrayList<String> getChampions() {
        return champions;
    }

    public static void setChampions(ArrayList<String> champions) {
        MainActivity.champions = champions;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FetchLolVersion fetchLolVersion = new FetchLolVersion();
        fetchLolVersion.execute();
    }
}
