package com.scotch.simpleorders.model.entity;

import java.util.List;

/**
 * Created by sutupin on 26.12.2014.
 */
public class Category {
    private int id;
    private String title;
    private String description;
    private List<Commodity> commodities;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Commodity> getCommodities() {
        return commodities;
    }

    public void setCommodities(List<Commodity> commodities) {
        this.commodities = commodities;
    }

    @Override
    public String toString() {
        return "Category [id=" + id + ", title=" + title + ", description=" + description + "]";
    }
}
