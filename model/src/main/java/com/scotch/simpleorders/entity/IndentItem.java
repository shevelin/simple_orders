package com.scotch.simpleorders.entity;

/**
 * Created by sutupin on 12.01.2015.
 */
public class IndentItem {
    private int id;
    private Indent indent;
    private Commodity commodity;
    private int count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Indent getIndent() {
        return indent;
    }

    public void setIndent(Indent indent) {
        this.indent = indent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IndentItem)) return false;

        IndentItem that = (IndentItem) o;

        if (count != that.count) return false;
        if (!commodity.equals(that.commodity)) return false;
        if (!indent.equals(that.indent)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = indent.hashCode();
        result = 31 * result + commodity.hashCode();
        result = 31 * result + count;
        return result;
    }
}
