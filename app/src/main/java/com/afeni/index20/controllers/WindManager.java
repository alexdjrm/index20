package com.afeni.index20.controllers;

import com.afeni.index20.models.Wind;

import java.util.ArrayList;

public class WindManager {

    public ArrayList<Wind> index20;

    public WindManager(String[] index20) {
        ArrayList<Wind> res = new ArrayList<>();
        for (String windRaw : index20) {
            String[] windRawArray = windRaw.split(",");
            String name = windRawArray[2];
            float float_value = Float.parseFloat(windRawArray[0]);
            String degrees = windRawArray[1];
            String cardinal = windRawArray[3];
            res.add(new Wind(name, float_value, degrees, cardinal));
        }
        this.index20 = res;
    }

    public ArrayList<Wind> getWinds() {
        return this.index20;
    }

    public Wind getWindByRotation(float rotation) {
        if (rotation < getWinds().get(0).getFloat_value() + 5.625 || rotation > getWinds().get(getWinds().size()-1).getFloat_value() + 5.625) {
            return getWinds().get(0);
        } else {
            for (int i = 1; i < getWinds().size(); i++) {
                if (rotation < getWinds().get(i).getFloat_value() + 5.625 && rotation > getWinds().get(i).getFloat_value() - 5.625) {
                    return getWinds().get(i);
                }
            }
        }
        return null;
    }


}
