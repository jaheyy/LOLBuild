package com.example.lolbuild.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.lolbuild.R;
import com.example.lolbuild.jobs.FetchLolVersion;

import java.util.ArrayList;

public class AuthenticationActivity extends AppCompatActivity {

    private static String lolVersion;
    private static ArrayList<String> champions;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        FetchLolVersion fetchLolVersion = new FetchLolVersion();
        fetchLolVersion.execute();
    }
}
