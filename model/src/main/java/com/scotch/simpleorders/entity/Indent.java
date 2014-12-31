package com.scotch.simpleorders.entity;

import java.util.List;

/**
 * Created by sutupin on 26.12.2014.
 */
public class Indent {
    private int id;
    private Customer customer;
    private String description;
    private IndentStatus status;

    private List<Commodity> commodities;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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

    public IndentStatus getStatus() {
        return status;
    }

    public void setStatus(IndentStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Indent[id=" + id + ", description=" + description
                + ", status=" + status  + "]";
    }
}

