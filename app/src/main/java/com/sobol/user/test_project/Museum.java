package com.sobol.user.test_project;

import java.io.Serializable;


public class Museum implements Serializable {

    public double lat;
    public double lgn;
    public String place_id;

    public Museum(double lat, double lng,String place_id) {
        this.lat = lat;
        this.lgn = lgn;
        this.place_id = place_id;
    }


}
