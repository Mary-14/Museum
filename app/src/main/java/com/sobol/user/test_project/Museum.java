package com.sobol.user.test_project;

import java.io.Serializable;


public class Museum implements Serializable {

    public String title;
    public String photo;

    public Museum (String title, String photo) {
        this.title = title;
        this.photo = photo;
    }


}
