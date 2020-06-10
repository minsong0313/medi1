package com.example.medi1;


public class Drug {
    private String name;
    private String image;
    private String shape;
    private String color;

    public Drug(String name, String image, String shape, String color) {
        this.name = name;
        this.image = image;
        this.shape = shape;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
