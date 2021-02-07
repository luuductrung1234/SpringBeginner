package com.learning.speldemo.data;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("shipping")
public class Shipping {
    private Map<String, List<City>> locationByCountry;
    private Map<String, List<City>> chargesByLocation;

    public Shipping() {
        var usCities = new ArrayList<City>() {{
            add(new City("Alabama", 8.50, false));
            add(new City("New Jersey", 10.50, false));
            add(new City("New York", 10.50, false));
            add(new City("Washington", 8.0, true));
        }};

        var ukCities = new ArrayList<City>() {{
            add(new City("London", 25.50, false));
            add(new City("Cambridge", 20.50, false));
            add(new City("Leeds", 15.30, false));
        }};

        var dkCities = new ArrayList<City>() {{
            add(new City("Copenhagen", 25.50, false));
            add(new City("Hadsund", 20.50, false));
            add(new City("Arden", 15.30, false));
        }};

        var myCities = new ArrayList<City>() {{
            add(new City("Kuala Lumpur", 5.20, false));
            add(new City("Johor Badru", 3.50, false));
        }};

        this.locationByCountry = new HashMap<>(){{
            put("US", usCities);
            put("UK", ukCities);
            put("DK", dkCities);
            put("MY", myCities);
        }};

        this.chargesByLocation = new HashMap<>(){{
            put("US", usCities);
            put("UK", ukCities);
            put("DK", dkCities);
            put("MY", myCities);
        }};
    }

    public Map<String, List<City>> getLocationByCountry() {
        return locationByCountry;
    }

    public void setLocationByCountry(Map<String, List<City>> locationByCountry) {
        this.locationByCountry = locationByCountry;
    }

    public Map<String, List<City>> getChargesByLocation() {
        return chargesByLocation;
    }

    public void setChargesByLocation(Map<String, List<City>> chargesByLocation) {
        this.chargesByLocation = chargesByLocation;
    }
}
