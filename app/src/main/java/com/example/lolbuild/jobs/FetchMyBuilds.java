package com.example.lolbuild.jobs;

import android.os.AsyncTask;

import androidx.annotation.NonNull;

import com.example.lolbuild.mainApp.MyBuildsFragment;
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


public class FetchMyBuilds extends AsyncTask<Void, Void, Void> {
    private AsyncResponse delegate = null;
    private String userID;
    private ArrayList<String> savedBuildsIds;
    private List<DocumentSnapshot> myBuilds;


    public FetchMyBuilds(String userID) {
        this.userID = userID;
    }

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
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference userAccount = db.collection("accounts").document(userID);
        Task<DocumentSnapshot> task = userAccount.get();
        savedBuildsIds = null;
        task.addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                savedBuildsIds = (ArrayList<String>) task.getResult().get("savedBuilds");
                Query builds = db.collection("builds").whereIn(FieldPath.documentId(), savedBuildsIds);
                Task<QuerySnapshot> querySnapshot = builds.get();
                querySnapshot.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        myBuilds = querySnapshot.getResult().getDocuments();
                    }
                });
            }
        });
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        MyBuildsFragment.setMyBuilds(myBuilds);
        delegate.processFinish("success");
    }

}
