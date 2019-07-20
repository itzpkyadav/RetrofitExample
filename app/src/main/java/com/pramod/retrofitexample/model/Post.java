package com.pramod.retrofitexample.model;

import com.google.gson.annotations.SerializedName;

public class Post {
    private int userId;

    private int id;

    private String title;

    /***
     * if json key and variable name is differ then use serializedName which is gson annotation and simply pass json key in form of string
     * */
    @SerializedName("body")
    private String text;

    public Post(int userId, String title, String text) {
        this.userId = userId;
        this.title = title;
        this.text = text;
    }

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
