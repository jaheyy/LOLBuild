package com.example.lolbuild.jobs;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.lolbuild.mainApp.MainAppActivity;
import com.example.lolbuild.models.Item;
import com.example.lolbuild.utilities.Utilities;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class FetchItems extends AsyncTask<Void, Void, Void> {
    private AsyncResponse delegate;
    private String itemsJson;
    private SharedPreferences sharedPreferences;

    public FetchItems(SharedPreferences sharedPreferences, AsyncResponse delegate) {
        this.sharedPreferences = sharedPreferences;
        this.delegate = delegate;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        itemsJson = Utilities.fetchData("http://ddragon.leagueoflegends.com/cdn/" + MainAppActivity.getLolVersion() + "/data/en_US/item.json");
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        try {
            ArrayList<Item> itemList = new ArrayList<>();
            JSONObject items = new JSONObject(itemsJson).getJSONObject("data");
            Iterator<String> keys = items.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                JSONObject currentItem = items.getJSONObject(key);
                Item item = new Item(
                        Integer.parseInt(key),
                        currentItem.getString("name"),
                        currentItem.getString("description"),
                        key + ".png",
                        currentItem.getJSONObject("gold").getInt("total"),
                        currentItem.getJSONObject("stats")
                );
                itemList.add(item);
            }
            MainAppActivity.setItems(itemList);
            Gson gson = new Gson();
            String itemsJson = gson.toJson(itemList);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("items", itemsJson);
            editor.commit();
            FetchChampions fetchChampions = new FetchChampions(sharedPreferences, delegate);
            fetchChampions.execute();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
