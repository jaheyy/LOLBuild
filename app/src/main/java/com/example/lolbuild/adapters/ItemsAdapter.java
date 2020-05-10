package com.example.lolbuild.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.TooltipCompat;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lolbuild.R;
import com.example.lolbuild.models.Item;
import com.example.lolbuild.utilities.Utilities;
import com.example.lolbuild.viewModels.BuildViewModel;


import java.util.ArrayList;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemsViewHolder> {
    private Context context;
    private ArrayList<Item> itemList;
    private NavController navController;
    private BuildViewModel buildViewModel;
    private int itemSet;

    public ItemsAdapter(Context context, ArrayList<Item> itemList, NavController navController, BuildViewModel buildViewModel, int itemSet) {
        this.context = context;
        this.itemList = itemList;
        this.navController = navController;
        this.buildViewModel = buildViewModel;
        this.itemSet = itemSet;
    }

    @NonNull
    @Override
    public ItemsAdapter.ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
        return new ItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsAdapter.ItemsViewHolder holder, int position) {
        Item currentItem = itemList.get(position);
        String itemName = currentItem.getName();
        int cost = currentItem.getCost();
        String imagePath = currentItem.getImagePath();
        String tooltipText = "Cost: " + cost + " gold";

        holder.itemTextView.setText(itemName);
        Bitmap image = Utilities.getImageFromAssets(context, "items", imagePath);
        holder.itemImageView.setImageBitmap(image);
        TooltipCompat.setTooltipText(holder.itemView, tooltipText);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (itemSet) {
                    case (0) :
                        buildViewModel.addStartingItem(currentItem);
                        break;
                    case (1) :
                        buildViewModel.addCoreItem(currentItem);
                        break;
                    case (2):
                        buildViewModel.addSituationalItem(currentItem);
                        break;
                }
                navController.navigate(R.id.action_itemsFragment_to_addBuildFragment);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ItemsViewHolder extends RecyclerView.ViewHolder {
        TextView itemTextView;
        ImageView itemImageView;

        public ItemsViewHolder(@NonNull View itemView) {
            super(itemView);
            itemTextView = itemView.findViewById(R.id.championTextView);
            itemImageView = itemView.findViewById(R.id.championImageView);
        }
    }
}
