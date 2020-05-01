package com.example.lolbuild.mainApp;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lolbuild.R;
import com.example.lolbuild.adapters.ChampionsAdapter;
import com.example.lolbuild.authentication.AuthenticationActivity;
import com.example.lolbuild.jobs.FetchChampions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AppMainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AppMainFragment extends Fragment {
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
//    MyBuildAdapter myBuildAdapter;
    FloatingActionButton createBuildFAB;
    NavController navController;

    public AppMainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_app_main, container, false);
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
                navController.navigate(R.id.action_appMainFragment_to_championsFragment2);
            }
        });
    }
}
