package com.example.lolbuild.jobs;

import android.os.AsyncTask;

import com.example.lolbuild.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class FetchChampions extends AsyncTask<Void, Void, Void> {
    private String championJson;

    @Override
    protected Void doInBackground(Void... voids) {
        championJson = FetchData.fetch("http://ddragon.leagueoflegends.com/cdn/" + MainActivity.getLolVersion() + "/data/en_US/champion.json");
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        try {
            JSONObject champions = new JSONObject(championJson).getJSONObject("data");
            MainActivity.setChampions(champions);
            JSONObject championsGFAG = MainActivity.getChampions();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
