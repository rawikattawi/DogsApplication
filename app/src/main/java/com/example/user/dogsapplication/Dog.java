package com.example.user.dogsapplication;

import android.graphics.Bitmap;

public class Dog {
    private String image;
    private String name;
    private String date;
    private String weight;
    private String time;


    public Dog(String image, String name, String date, String weight, String time) {
        this.image = image;
        this.name = name;
        this.date = date;
        this.weight = weight;
        this.time = time;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
