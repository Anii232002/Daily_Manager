package com.example.dailymanager.database;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.versionedparcelable.VersionedParcelize;

@VersionedParcelize
@Entity(tableName = "habits_table")
public class HabitsDataEntity {

    @PrimaryKey(autoGenerate = true)
    int id;

    int imageid;
    String h_title,h_description,h_timestamp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImageid() {
        return imageid;
    }

    public void setImageid(int imageid) {
        this.imageid = imageid;
    }

    public String getH_title() {
        return h_title;
    }

    public void setH_title(String h_title) {
        this.h_title = h_title;
    }

    public String getH_description() {
        return h_description;
    }

    public void setH_description(String h_description) {
        this.h_description = h_description;
    }

    public String getH_timestamp() {
        return h_timestamp;
    }

    public void setH_timestamp(String h_timestamp) {
        this.h_timestamp = h_timestamp;
    }





}