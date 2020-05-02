package com.example.lolbuild.jobs;

import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.lolbuild.authentication.AuthenticationActivity;
import com.example.lolbuild.models.Item;
import com.example.lolbuild.utilities.Utilities;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class FetchItems extends AsyncTask<Void, Void, Void> {
    private AsyncResponse delegate = null;
    private String itemsJson;

    public AsyncResponse getDelegate() {
        return delegate;
    }

    public void setDelegate(AsyncResponse delegate) {
        this.delegate = delegate;
    }

    public interface AsyncResponse {
        void processFinish(String output);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        itemsJson = Utilities.fetchData("http://ddragon.leagueoflegends.com/cdn/" + AuthenticationActivity.getLolVersion() + "/data/en_US/item.json");
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
            AuthenticationActivity.setItems(itemList);
            delegate.processFinish("Success");
        } catch (JSONException e) {
            e.printStackTrace();
            delegate.processFinish("Something went wrong.");
        }
    }
}
