package com.example.juliana.listviewpersonalizado;

/**
 * Created by Juliana on 01/03/2018.
 */
public class Restaurant {
    private String name;
    private String address;
    private String points;
    private String image;

    public Restaurant(String name, String points, String address, String image) {
        this.name = name;
        this.points = points;
        this.address = address;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
