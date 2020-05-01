package com.example.lolbuild.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lolbuild.R;
import com.example.lolbuild.utilities.Utilities;
import com.google.firebase.firestore.DocumentSnapshot;


import java.util.ArrayList;
import java.util.List;

public class MyBuildsAdapter extends RecyclerView.Adapter<MyBuildsAdapter.MyBuildsViewHolder> {
    private List<DocumentSnapshot> myBuilds;
    private Context context;

    public MyBuildsAdapter(Context context, List<DocumentSnapshot> myBuilds) {
        this.context = context;
        this.myBuilds = myBuilds;
    }

    @NonNull
    @Override
    public MyBuildsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.build_layout, parent, false);
        MyBuildsViewHolder myBuildsViewHolder = new MyBuildsViewHolder(view);
        return myBuildsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyBuildsViewHolder holder, int position) {
        DocumentSnapshot currentBuild = myBuilds.get(position);
        ArrayList<Integer> startingItems = (ArrayList<Integer>) currentBuild.get("startingItems");
        ArrayList<Integer> coreItems = (ArrayList<Integer>) currentBuild.get("coreItems");
        ArrayList<Integer> situationalItems = (ArrayList<Integer>) currentBuild.get("situationalItems");
        Bitmap image = Utilities.getImageFromAssets(context, "champions", currentBuild.get("champion") + ".png");
        holder.championImageView.setImageBitmap(image);
        ImageView[] holderStartingItems = holder.startingItems;
        ImageView[] holderCoreItems = holder.coreItems;
        ImageView[] holderSituationalItems = holder.situationalItems;
        for (int i=0; i<holderStartingItems.length; i++) {
            if (i < startingItems.size()) {
                Bitmap itemImage = Utilities.getImageFromAssets(context, "items", startingItems.get(i) + ".png");
                holderStartingItems[i].setImageBitmap(itemImage);
            } else {
                holderStartingItems[i].setImageDrawable(null);
            }
        }
        for (int i=0; i<holderCoreItems.length; i++) {
            if (i < coreItems.size()) {
                Bitmap itemImage = Utilities.getImageFromAssets(context, "items", coreItems.get(i) + ".png");
                holderCoreItems[i].setImageBitmap(itemImage);
            } else {
                holderCoreItems[i].setImageDrawable(null);
            }
        }
        for (int i=0; i<holderSituationalItems.length; i++) {
            if (i < situationalItems.size()) {
                Bitmap itemImage = Utilities.getImageFromAssets(context, "items", situationalItems.get(i) + ".png");
                holderSituationalItems[i].setImageBitmap(itemImage);
            } else {
                holderSituationalItems[i].setImageDrawable(null);
            }
        }
    }

    @Override
    public int getItemCount() {
        return myBuilds.size();
    }

    class MyBuildsViewHolder extends RecyclerView.ViewHolder {
        ImageView championImageView;
        ImageView startingItem1;
        ImageView startingItem2;
        ImageView startingItem3;
        ImageView startingItem4;
        ImageView startingItem5;
        ImageView startingItem6;
        ImageView[] startingItems;
        ImageView coreItem1;
        ImageView coreItem2;
        ImageView coreItem3;
        ImageView coreItem4;
        ImageView coreItem5;
        ImageView coreItem6;
        ImageView[] coreItems;
        ImageView situationalItem1;
        ImageView situationalItem2;
        ImageView situationalItem3;
        ImageView situationalItem4;
        ImageView situationalItem5;
        ImageView situationalItem6;
        ImageView[] situationalItems;
        public MyBuildsViewHolder(@NonNull View itemView) {
            super(itemView);
            championImageView = itemView.findViewById(R.id.championImageView);
            startingItem1 = itemView.findViewById(R.id.startingItem1);
            startingItem2 = itemView.findViewById(R.id.startingItem2);
            startingItem3 = itemView.findViewById(R.id.startingItem3);
            startingItem4 = itemView.findViewById(R.id.startingItem4);
            startingItem5 = itemView.findViewById(R.id.startingItem5);
            startingItem6 = itemView.findViewById(R.id.startingItem6);
            startingItems = new ImageView[] {startingItem1, startingItem2, startingItem3, startingItem4, startingItem5, startingItem6};
            coreItem1 = itemView.findViewById(R.id.coreItem1);
            coreItem2 = itemView.findViewById(R.id.coreItem2);
            coreItem3 = itemView.findViewById(R.id.coreItem3);
            coreItem4 = itemView.findViewById(R.id.coreItem4);
            coreItem5 = itemView.findViewById(R.id.coreItem5);
            coreItem6 = itemView.findViewById(R.id.coreItem6);
            coreItems = new ImageView[] {coreItem1, coreItem2, coreItem3, coreItem4, coreItem5, coreItem6};
            situationalItem1 = itemView.findViewById(R.id.situationalItem1);
            situationalItem2 = itemView.findViewById(R.id.situationalItem2);
            situationalItem3 = itemView.findViewById(R.id.situationalItem3);
            situationalItem4 = itemView.findViewById(R.id.situationalItem4);
            situationalItem5 = itemView.findViewById(R.id.situationalItem5);
            situationalItem6 = itemView.findViewById(R.id.situationalItem6);
            situationalItems = new ImageView[] {situationalItem1, situationalItem2, situationalItem3, situationalItem4, situationalItem5, situationalItem6};
        }
    }
}
