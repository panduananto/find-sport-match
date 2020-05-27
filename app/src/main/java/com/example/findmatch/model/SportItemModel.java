package com.example.findmatch.model;

import com.google.firebase.firestore.IgnoreExtraProperties;

@IgnoreExtraProperties
public class SportItemModel {
    private String sportItemId;
    private String sportImage, sportName;

    public SportItemModel() {

    }

    public SportItemModel(String sportItemId, String sportImage, String sportName) {
        this.sportItemId = sportItemId;
        this.sportImage = sportImage;
        this.sportName = sportName;
    }

    public String getSportItemId() {
        return sportItemId;
    }

    public void setSportItemId(String sportItemId) {
        this.sportItemId = sportItemId;
    }

    public String getSportImage() {
        return sportImage;
    }

    public void setSportImage(String sportImage) {
        this.sportImage = sportImage;
    }

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }
}
