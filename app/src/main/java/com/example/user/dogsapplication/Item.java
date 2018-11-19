package com.example.user.dogsapplication;

public class Item {
    private  int image;
    private String nmae;

    public Item(int image, String name) {
        this.image = image;
        this.nmae = name;

    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getNmae() {
        return nmae;
    }

    public void setNmae(String nmae) {
        this.nmae = nmae;
    }
}
