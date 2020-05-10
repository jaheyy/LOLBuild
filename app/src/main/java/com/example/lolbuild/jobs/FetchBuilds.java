package com.example.lolbuild.jobs;

import android.os.AsyncTask;

import androidx.annotation.NonNull;

import com.example.lolbuild.mainApp.explore.ExploreFragment;
import com.example.lolbuild.mainApp.myBuilds.MyBuildsFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class FetchBuilds extends AsyncTask<Void, Void, Void> {
    private String userID;
    private List<DocumentSnapshot> builds;


    public FetchBuilds(String userID) {
        this.userID = userID;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        builds = null;
        Task<QuerySnapshot> task = db.collection("builds").whereEqualTo("champion", "Aatrox").get();
        task.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                builds = task.getResult().getDocuments();
            }
        });
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        ExploreFragment.setBuilds(builds);
    }

}
