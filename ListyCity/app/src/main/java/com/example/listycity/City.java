package com.example.listycity;

public class City {
    private final String name;
    private final String province;
    public City(String name, String province) {
        this.name = name;
        this.province = province;
    }
    public String getName() {
        return name;
    }
    public String getProvince() {return province; }
}
