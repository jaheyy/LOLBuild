package com.example.lolbuild.viewModels;

import androidx.lifecycle.ViewModel;

import com.example.lolbuild.models.Item;

import java.util.ArrayList;
import java.util.Collections;

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
        if (startingItems.size() < 6)
            startingItems.add(item);
    }

    public void addCoreItem(Item item) {
        if (coreItems.size() < 6)
            coreItems.add(item);
    }

    public void addSituationalItem(Item item) {
        if (situationalItems.size() < 6)
            situationalItems.add(item);
    }

    public ArrayList<Integer> getStartingItemsIDs() {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Item item:
             startingItems) {
            ids.add(item.getID());
        }
        return ids;
    }

    public ArrayList<Integer> getCoreItemsIDs() {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Item item:
                coreItems) {
            ids.add(item.getID());
        }
        return ids;
    }

    public ArrayList<Integer> getSituationalItemsIDs() {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Item item:
                situationalItems) {
            ids.add(item.getID());
        }
        return ids;
    }
}
