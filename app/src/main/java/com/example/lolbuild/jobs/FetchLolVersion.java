package com.example.lolbuild.jobs;

import android.os.AsyncTask;

import com.example.lolbuild.AppMainFragment;
import com.example.lolbuild.MainActivity;
import com.example.lolbuild.utilities.Utilities;

import org.json.JSONArray;
import org.json.JSONException;

public class FetchLolVersion extends AsyncTask<Void, Void, Void> {
    private String versionJson;

    @Override
    protected Void doInBackground(Void... voids) {
        versionJson = Utilities.fetchData("https://ddragon.leagueoflegends.com/api/versions.json");
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        try {
            JSONArray json = new JSONArray(versionJson);
            MainActivity.setLolVersion(json.get(0).toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
