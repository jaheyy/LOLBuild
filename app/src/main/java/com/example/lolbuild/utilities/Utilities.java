package com.example.lolbuild.utilities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class Utilities {
     public static Bitmap getImageFromAssets(Context context, String subfolder, String imageName) {
        try {
            InputStream is = context.getResources().getAssets().open(subfolder + "/"  + imageName);
            return BitmapFactory.decodeStream(is);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String fetchData(String urlString) {
        StringBuilder data = new StringBuilder();
        try {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while (line != null) {
                line = bufferedReader.readLine();
                data.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data.toString();
    }
}
