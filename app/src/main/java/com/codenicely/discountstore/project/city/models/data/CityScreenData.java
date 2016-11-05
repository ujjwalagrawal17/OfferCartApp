package com.codenicely.discountstore.project.city.models.data;

/**
 * Created by aman on 15/10/16.
 */
public class CityScreenData {
    private String city_id, city_name, data_type;

    public CityScreenData(String city_id, String city_name, String data_type) {
        this.city_id = city_id;
        this.city_name = city_name;
        this.data_type = data_type;
    }

    public String getCity_id() {
        return city_id;
    }

    public String getCity_name() {
        return city_name;
    }

    public String getData_type() {
        return data_type;
    }
}