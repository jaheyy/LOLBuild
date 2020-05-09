package com.example.lolbuild.utilities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lolbuild.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


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

    public static void forkBuild(String uid, String buildID, Context context) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("accounts").document(uid);
        Task<DocumentSnapshot> task = docRef.get();
        task.addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot result = task.getResult();
                    ArrayList<String> savedBuilds = (ArrayList<String>) result.get("savedBuilds");
                    if (!savedBuilds.contains(buildID)) {
                        Task<Void> arrayUnion = docRef.update("savedBuilds",
                                FieldValue.arrayUnion(buildID));
                        arrayUnion.addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(context, "Succesfully added a new build.", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(context, "Something went wrong.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }
            }
        });
    }

    public static void deleteBuild(String uid, String buildID, Context context, NavController navController) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        final DocumentReference docRef = db.collection("accounts").document(uid);
        Task<DocumentSnapshot> task = docRef.get();
        task.addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot result = task.getResult();
                    ArrayList<String> savedBuilds = (ArrayList<String>) result.get("savedBuilds");
                    if (savedBuilds.contains(buildID)) {
                        Task<Void> arrayRemove =
                                docRef.update("savedBuilds", FieldValue.arrayRemove(buildID));
                        arrayRemove.addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(context, "Succesfully deleted build from your account.", Toast.LENGTH_SHORT).show();
                                    navController.navigate(R.id.action_myBuildsFragment_self);
                                } else {
                                    Toast.makeText(context, "Something went wrong.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }
            }
        });
    }
}
