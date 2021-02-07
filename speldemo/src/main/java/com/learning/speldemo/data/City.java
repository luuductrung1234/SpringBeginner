package com.learning.speldemo.data;

import org.springframework.stereotype.Component;

@Component("city")
public class City {
    private String name;
    private double shipping;
    private boolean isCapital;

    public City() {
    }

    public City(String name, double shipping, boolean isCapital) {
        this.name = name;
        this.shipping = shipping;
        this.isCapital = isCapital;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getShipping() {
        return shipping;
    }

    public void setShipping(double shipping) {
        this.shipping = shipping;
    }

    public boolean getIsCapital() {
        return isCapital;
    }

    public void setIsCapital(boolean capital) {
        this.isCapital = capital;
    }
}
