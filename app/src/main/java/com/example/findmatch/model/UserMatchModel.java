package com.example.findmatch.model;

import com.google.firebase.firestore.IgnoreExtraProperties;

@IgnoreExtraProperties
public class UserMatchModel {
    private String matchItemId, userId;
    private String sportTag, sportTitle, sportAddressLocation;
    private String currentPlayer, maxPlayer;
    private String statusMatch, statusPlay;

    public UserMatchModel() {

    }

    public UserMatchModel(String matchItemId, String userId, String sportTag,
                          String sportTitle, String sportAddressLocation,
                          String currentPlayer, String maxPlayer,
                          String statusMatch, String statusPlay) {

        this.matchItemId = matchItemId;
        this.userId = userId;
        this.sportTag = sportTag;
        this.sportTitle = sportTitle;
        this.sportAddressLocation = sportAddressLocation;
        this.currentPlayer = currentPlayer;
        this.maxPlayer = maxPlayer;
        this.statusMatch = statusMatch;
        this.statusPlay = statusPlay;
    }

    public String getMatchItemId() {
        return matchItemId;
    }

    public void setMatchItemId(String matchItemId) {
        this.matchItemId = matchItemId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSportTag() {
        return sportTag;
    }

    public void setSportTag(String sportTag) {
        this.sportTag = sportTag;
    }

    public String getSportTitle() {
        return sportTitle;
    }

    public void setSportTitle(String sportTitle) {
        this.sportTitle = sportTitle;
    }

    public String getSportAddressLocation() {
        return sportAddressLocation;
    }

    public void setSportAddressLocation(String sportAddressLocation) {
        this.sportAddressLocation = sportAddressLocation;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(String currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public String getMaxPlayer() {
        return maxPlayer;
    }

    public void setMaxPlayer(String maxPlayer) {
        this.maxPlayer = maxPlayer;
    }

    public String getStatusMatch() {
        return statusMatch;
    }

    public void setStatusMatch(String statusMatch) {
        this.statusMatch = statusMatch;
    }

    public String getStatusPlay() {
        return statusPlay;
    }

    public void setStatusPlay(String statusPlay) {
        this.statusPlay = statusPlay;
    }
}
