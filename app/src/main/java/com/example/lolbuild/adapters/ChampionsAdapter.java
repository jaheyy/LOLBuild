package com.example.lolbuild.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lolbuild.R;
import com.example.lolbuild.utilities.Utilities;

import java.util.ArrayList;

public class ChampionsAdapter extends RecyclerView.Adapter<ChampionsAdapter.ChampionsViewHolder> {

    ArrayList<String> champions;
    Context context;

    public ChampionsAdapter(Context context, ArrayList<String> champions) {
        this.champions = champions;
        this.context = context;
    }

    @NonNull
    @Override
    public ChampionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
        ChampionsViewHolder viewHolder = new ChampionsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChampionsViewHolder holder, int position) {
        String championName = champions.get(position);
        holder.championTextView.setText(championName);
        Bitmap image = Utilities.getImageFromAssets(context, "champions", championName + ".png");
        holder.championImageView.setImageBitmap(image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, champions.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return champions.size();
    }

    public class ChampionsViewHolder  extends RecyclerView.ViewHolder {
        TextView championTextView;
        ImageView championImageView;

        public ChampionsViewHolder(@NonNull View itemView) {
            super(itemView);
            championTextView = itemView.findViewById(R.id.championTextView);
            championImageView = itemView.findViewById(R.id.championImageView);
        }
    }
}
