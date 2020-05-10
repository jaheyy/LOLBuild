package com.example.lolbuild.jobs;

import android.content.SharedPreferences;
import android.os.AsyncTask;

import com.example.lolbuild.authentication.AuthenticationActivity;
import com.example.lolbuild.models.Item;
import com.example.lolbuild.utilities.Utilities;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class FetchLolVersion extends AsyncTask<Void, Void, Void> {
    private String versionJson;
    private SharedPreferences sharedPreferences;
    private AsyncResponse delegate;

    public FetchLolVersion(SharedPreferences savedLolVersion, AsyncResponse delegate) {
        this.sharedPreferences = savedLolVersion;
        this.delegate = delegate;
    }

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
            String currentLolVersion = json.get(0).toString();
            AuthenticationActivity.setLolVersion(currentLolVersion);
            if (!sharedPreferences.getString("LolVersion", "").equals(currentLolVersion) ||
                sharedPreferences.getString("items", "").equals("") ||
                sharedPreferences.getString("champions", "").equals("")) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("LolVersion", currentLolVersion);
                    editor.commit();
                    FetchItems fetchItems = new FetchItems(sharedPreferences, delegate);
                    fetchItems.execute();
            } else {
                delegate.processFinish("Success");
                Gson gson = new Gson();

                String items = sharedPreferences.getString("items", "");
                Type arrayOfItems = new TypeToken<ArrayList<Item>>() {}.getType();
                ArrayList<Item> itemsList = gson.fromJson(items, arrayOfItems);
                AuthenticationActivity.setItems(itemsList);

                String champions = sharedPreferences.getString("champions", "");
                Type arrayOfStrings = new TypeToken<ArrayList<String>>() {}.getType();
                ArrayList<String> championsList = gson.fromJson(champions, arrayOfStrings);
                AuthenticationActivity.setChampions(championsList);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
