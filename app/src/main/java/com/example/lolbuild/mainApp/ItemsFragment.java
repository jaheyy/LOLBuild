package com.example.lolbuild.mainApp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lolbuild.R;
import com.example.lolbuild.adapters.ItemsAdapter;
import com.example.lolbuild.authentication.AuthenticationActivity;
import com.example.lolbuild.jobs.FetchChampions;
import com.example.lolbuild.jobs.FetchItems;
import com.example.lolbuild.models.Item;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ItemsFragment extends Fragment implements FetchItems.AsyncResponse {

    private static ArrayList<Item> items;
    private NavController navController;
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;

    public ItemsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FetchItems fetchItems = new FetchItems();
        fetchItems.setDelegate(this);
        fetchItems.execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_items, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        recyclerView = view.findViewById(R.id.itemsRecyclerView);
        gridLayoutManager = new GridLayoutManager(getContext(),8);
        recyclerView.setLayoutManager(gridLayoutManager);
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
    }

    @Override
    public void processFinish(String output) {
        if (output.equals("Success")) {
            ItemsAdapter itemsAdapter = new ItemsAdapter(getContext(), AuthenticationActivity.getItems(), navController);
            recyclerView.setAdapter(itemsAdapter);
        }
    }
}
