package com.example.lolbuild.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.lolbuild.R;
import com.example.lolbuild.jobs.FetchLolVersion;
import com.example.lolbuild.models.Item;

import java.util.ArrayList;

public class AuthenticationActivity extends AppCompatActivity {

    private static String lolVersion;
    private static ArrayList<String> champions;
    private static ArrayList<Item> items;

    public static String getLolVersion() {
        return lolVersion;
    }

    public static void setLolVersion(String lolVersion) {
        AuthenticationActivity.lolVersion = lolVersion;
    }

    public static ArrayList<String> getChampions() {
        return champions;
    }

    public static void setChampions(ArrayList<String> champions) {
        AuthenticationActivity.champions = champions;
    }

    public static ArrayList<Item> getItems() {
        return items;
    }

    public static void setItems(ArrayList<Item> items) {
        AuthenticationActivity.items = items;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
}
}
