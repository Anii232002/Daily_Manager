package com.example.dailymanager.dataclass;

public class DataItems {
    private String item;
    private int itemImages;

    public DataItems(String item,int itemImages) {
        this.item = item;
        this.itemImages = itemImages;
    }

    public String getItem() {
        return item;
    }

    public int getItemImages() {
        return itemImages;
    }
}
