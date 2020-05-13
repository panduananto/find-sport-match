package com.example.findmatch.model;

public class SportItemModel {

    private String sport_item_id;
    private String sport_image, sport_name;

    public SportItemModel() {
    }

    public SportItemModel(String sport_item_id, String sport_image, String sport_name) {
        this.sport_item_id = sport_item_id;
        this.sport_image = sport_image;
        this.sport_name = sport_name;
    }

    public String getSport_item_id() {
        return sport_item_id;
    }

    public void setSport_item_id(String sport_item_id) {
        this.sport_item_id = sport_item_id;
    }

    public String getSport_image() {
        return sport_image;
    }

    public void setSport_image(String sport_image) {
        this.sport_image = sport_image;
    }

    public String getSport_name() {
        return sport_name;
    }

    public void setSport_name(String sport_name) {
        this.sport_name = sport_name;
    }
}
