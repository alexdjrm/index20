package com.afeni.index20.models;

public class Wind {
    private String name;
    private float float_value;
    private String degrees;
    private String cardinal;

    public Wind(String name, float float_value, String degrees, String cardinal) {
        this.name = name;
        this.float_value = float_value;
        this.degrees = degrees;
        this.cardinal = cardinal;
    }

    @Override
    public String toString(){
        return this.name+" "+this.float_value+" "+this.degrees+" "+this.cardinal;
    }

    public String getName() {
        return name;
    }

    public float getFloat_value() {
        return float_value;
    }

    public String getDegrees() {
        return degrees;
    }

    public String getCardinal() {
        return cardinal;
    }
}
