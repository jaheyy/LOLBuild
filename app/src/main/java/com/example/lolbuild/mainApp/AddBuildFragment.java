package com.example.lolbuild.mainApp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.lolbuild.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddBuildFragment extends Fragment {

    public AddBuildFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_build, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavController navController = Navigation.findNavController(view);
        ImageView championImageView = view.findViewById(R.id.championImageView);
        ImageView startingItem1 = view.findViewById(R.id.startingItem1);
        ImageView startingItem2 = view.findViewById(R.id.startingItem2);
        ImageView startingItem3 = view.findViewById(R.id.startingItem3);
        ImageView startingItem4 = view.findViewById(R.id.startingItem4);
        ImageView startingItem5 = view.findViewById(R.id.startingItem5);
        ImageView startingItem6 = view.findViewById(R.id.startingItem6);
        ImageView[] startingItems = new ImageView[] {startingItem1, startingItem2, startingItem3, startingItem4, startingItem5, startingItem6};
        ImageView coreItem1 = view.findViewById(R.id.coreItem1);
        ImageView coreItem2 = view.findViewById(R.id.coreItem2);
        ImageView coreItem3 = view.findViewById(R.id.coreItem3);
        ImageView coreItem4 = view.findViewById(R.id.coreItem4);
        ImageView coreItem5 = view.findViewById(R.id.coreItem5);
        ImageView coreItem6 = view.findViewById(R.id.coreItem6);
        ImageView[] coreItems = new ImageView[] {coreItem1, coreItem2, coreItem3, coreItem4, coreItem5, coreItem6};
        ImageView situationalItem1 = view.findViewById(R.id.situationalItem1);
        ImageView situationalItem2 = view.findViewById(R.id.situationalItem2);
        ImageView situationalItem3 = view.findViewById(R.id.situationalItem3);
        ImageView situationalItem4 = view.findViewById(R.id.situationalItem4);
        ImageView situationalItem5 = view.findViewById(R.id.situationalItem5);
        ImageView situationalItem6 = view.findViewById(R.id.situationalItem6);
        ImageView[] situationalItems = new ImageView[] {situationalItem1, situationalItem2, situationalItem3, situationalItem4, situationalItem5, situationalItem6};

        for (ImageView startingItem: startingItems) {
            startingItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    navController.navigate(R.id.action_addBuildFragment_to_itemsFragment);
                }
            });
        }

        for (ImageView coreItem: coreItems) {
            coreItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    navController.navigate(R.id.action_addBuildFragment_to_itemsFragment);
                }
            });
        }

        for(ImageView situationalItem: situationalItems) {
            situationalItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    navController.navigate(R.id.action_addBuildFragment_to_itemsFragment);
                }
            });
        }
    }
}
