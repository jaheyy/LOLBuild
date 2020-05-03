package com.example.lolbuild.viewModels;

import androidx.lifecycle.ViewModel;

import com.example.lolbuild.models.Item;

import java.util.ArrayList;

public class BuildViewModel extends ViewModel {
    private String champion;
    private ArrayList<Item> startingItems = new ArrayList<>();
    private ArrayList<Item> coreItems = new ArrayList<>();
    private ArrayList<Item> situationalItems = new ArrayList<>();

    public String getChampion() {
        return champion;
    }

    public void setChampion(String champion) {
        this.champion = champion;
    }

    public ArrayList<Item> getStartingItems() {
        return startingItems;
    }

    public ArrayList<Item> getCoreItems() {
        return coreItems;
    }

    public ArrayList<Item> getSituationalItems() {
        return situationalItems;
    }

    public void addStartingItem(Item item) {
        startingItems.add(item);
    }

    public void addCoreItem(Item item) {
        coreItems.add(item);
    }

    public void addSituationalItem(Item item) {
        situationalItems.add(item);
    }
}
