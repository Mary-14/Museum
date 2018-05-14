package com.sobol.user.test_project;

import java.io.Serializable;


public class Museum implements Serializable {

    public String title;
    public String photo;
    public double lat;
    public double lgn;
    public String place_id;


    public Museum (String title, String photo) {
        this.title = title;
        this.photo = photo;
    }


    public Museum(double lat, double lng, String place_id) {
    }
}
