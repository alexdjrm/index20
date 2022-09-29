package com.afeni.index20.controllers;

import com.afeni.index20.models.Wind;

import java.util.ArrayList;

public class WindManager {

    public static ArrayList<Wind> getWinds(String[] index20) {
        ArrayList<Wind> res = new ArrayList<>();
        for (String windRaw : index20) {
            String[] windRawArray = windRaw.split(",");
            String name = windRawArray[2];
            float float_value = Float.parseFloat(windRawArray[0]);
            String degrees = windRawArray[1];
            String cardinal = windRawArray[3];
            res.add(new Wind(name, float_value, degrees, cardinal));
        }
        return res;
    }
}
