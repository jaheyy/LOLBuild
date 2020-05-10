package com.example.lolbuild.jobs;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.lolbuild.mainApp.MainAppActivity;
import com.example.lolbuild.utilities.Utilities;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class FetchChampions extends AsyncTask<Void, Void, Void> {
    private String championJson;
    private AsyncResponse delegate;
    private SharedPreferences sharedPreferences;

    public FetchChampions(SharedPreferences sharedPreferences, AsyncResponse delegate) {
        this.delegate = delegate;
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        championJson = Utilities.fetchData("http://ddragon.leagueoflegends.com/cdn/" + MainAppActivity.getLolVersion() + "/data/en_US/champion.json");
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        try {
            JSONObject champions = new JSONObject(championJson).getJSONObject("data");
            ArrayList<String> championList = new ArrayList<>();
            champions.keys().forEachRemaining(championList::add);
            MainAppActivity.setChampions(championList);
            Gson gson = new Gson();
            String championsJson = gson.toJson(championList);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("champions", championsJson);
            editor.commit();
            delegate.processFinish("Success");
        } catch (JSONException e) {
            e.printStackTrace();
            delegate.processFinish("Something went wrong.");
        }
    }
}
