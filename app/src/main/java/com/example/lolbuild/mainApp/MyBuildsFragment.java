package com.example.lolbuild.mainApp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lolbuild.R;
import com.example.lolbuild.adapters.MyBuildsAdapter;
import com.example.lolbuild.jobs.FetchMyBuilds;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyBuildsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyBuildsFragment extends Fragment {
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
//    MyBuildAdapter myBuildAdapter;
    FloatingActionButton createBuildFAB;
    NavController navController;
    FirebaseAuth auth;
    private String userID;
    private static List<DocumentSnapshot> myBuilds;
    private ArrayList<String> savedBuildsIds;
    private MyBuildsAdapter myBuildsAdapter;

    public MyBuildsFragment() {
        // Required empty public constructor
    }

    public static void setMyBuilds(List<DocumentSnapshot> myBuilds) {
        MyBuildsFragment.myBuilds = myBuilds;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        userID = auth.getUid();
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
                        MyBuildsAdapter myBuildsAdapter = new MyBuildsAdapter(getContext(), myBuilds);
                        recyclerView.setAdapter(myBuildsAdapter);
                    }
                });
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_builds, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        FirebaseAuth auth = FirebaseAuth.getInstance();
//        String email = auth.getCurrentUser().getEmail();
//        Log.i("User", auth.getCurrentUser().toString());

        createBuildFAB = view.findViewById(R.id.createBuildFAB);
        navController = Navigation.findNavController(view);
        recyclerView = view.findViewById(R.id.myBuildsRecyclerView);
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return null;
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            }

            @Override
            public int getItemCount() {
                return 0;
            }
        });

        createBuildFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_myBuildsFragment_to_championsFragment);
            }
        });
    }
}
