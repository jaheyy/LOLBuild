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
import com.example.lolbuild.mainApp.MainAppActivity;
import com.example.lolbuild.viewModels.BuildViewModel;


public class ChampionsFragment extends Fragment {

    public ChampionsFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_champions, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BuildViewModel buildViewModel = new ViewModelProvider(requireActivity()).get(BuildViewModel.class);
        NavController navController = Navigation.findNavController(view);
        RecyclerView recyclerView = view.findViewById(R.id.championsRecyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 5);
        recyclerView.setLayoutManager(gridLayoutManager);
        ChampionsAdapter championsAdapter = new ChampionsAdapter(getContext(), MainAppActivity.getChampions(), navController, buildViewModel);
        recyclerView.setAdapter(championsAdapter);
    }
}
