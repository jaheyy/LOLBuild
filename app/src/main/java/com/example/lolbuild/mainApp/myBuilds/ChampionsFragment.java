package com.example.lolbuild.mainApp.myBuilds;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lolbuild.R;
import com.example.lolbuild.adapters.ChampionsAdapter;
import com.example.lolbuild.authentication.AuthenticationActivity;
import com.example.lolbuild.viewModels.BuildViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChampionsFragment extends Fragment {

    private static String championsJson;
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private ChampionsAdapter championsAdapter;
    private NavController navController;
    private BuildViewModel buildViewModel;

    public ChampionsFragment() {
        // Required empty public constructor
    }

    public static String getChampionsJson() {
        return championsJson;
    }

    public static void setChampionsJson(String championsJson) {
        ChampionsFragment.championsJson = championsJson;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_champions, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        buildViewModel = new ViewModelProvider(requireActivity()).get(BuildViewModel.class);
        navController = Navigation.findNavController(view);
        recyclerView = view.findViewById(R.id.championsRecyclerView);
        gridLayoutManager = new GridLayoutManager(getContext(),5);
        recyclerView.setLayoutManager(gridLayoutManager);
        championsAdapter = new ChampionsAdapter(getContext(), AuthenticationActivity.getChampions(), navController, buildViewModel);
        recyclerView.setAdapter(championsAdapter);
    }
}
