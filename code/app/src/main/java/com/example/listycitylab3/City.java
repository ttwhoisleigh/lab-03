package com.example.listycitylab3;

// creates the city class, pretty simple
public class City {
    private String name;
    private String province;

    public City(String name, String province){ // constructor
        this.name = name;
        this.province = province;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
