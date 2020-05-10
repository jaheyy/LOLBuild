package com.example.lolbuild.mainApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.example.lolbuild.R;
import com.example.lolbuild.models.Item;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MainAppActivity extends AppCompatActivity {

    private static String lolVersion;
    private static ArrayList<String> champions;
    private static ArrayList<Item> items;

    public static String getLolVersion() {
        return lolVersion;
    }

    public static void setLolVersion(String lolVersion) {
        MainAppActivity.lolVersion = lolVersion;
    }

    public static ArrayList<String> getChampions() {
        return champions;
    }

    public static void setChampions(ArrayList<String> champions) {
        MainAppActivity.champions = champions;
    }

    public static ArrayList<Item> getItems() {
        return items;
    }

    public static void setItems(ArrayList<Item> items) {
        MainAppActivity.items = items;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bttm_nav);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment);
        NavigationUI.setupWithNavController(bottomNavigationView, navHostFragment.getNavController());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FirebaseAuth.getInstance().signOut();
    }
}
