package com.example.lolbuild.models;

import org.json.JSONObject;

import java.io.Serializable;

public class Item implements Serializable {
    private int ID;
    private String name;
    private String description;
    private String imagePath;
    private int cost;
    private JSONObject stats;

    public Item(int ID, String name, String description, String imagePath, int cost, JSONObject stats) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.imagePath = imagePath;
        this.cost = cost;
        this.stats = stats;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public JSONObject getStats() {
        return stats;
    }

    public void setStats(JSONObject stats) {
        this.stats = stats;
    }
}
