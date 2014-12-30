package com.scotch.simpleorders.model.entity;

import java.math.BigDecimal;

/**
 * Created by sutupin on 26.12.2014.
 */
public class Commodity {
    private int id;
    private String title;
    private String description;
    private Category category;
    private BigDecimal price; // todo: figure out

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Commodity [id=" + id + ", title=" + title + ", description=" + description /*+ ",category=" + category*/ +"]";
    }

}
