package com.codenicely.discountstore.project_new.shop_offer_list.model.data;

/**
 * Created by aman on 19/10/16.
 */

public class OfferScreenDetails {

    private String description;
    private int offer_id;
    private String validity;
    private String name;
    private String image;
    private int price;


    public OfferScreenDetails(String description, int offer_id, String validity, String name, String image, int price) {
        this.description = description;
        this.offer_id = offer_id;
        this.validity = validity;
        this.name = name;
        this.image = image;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public int getOffer_id() {
        return offer_id;
    }

    public String getValidity() {
        return validity;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public int getPrice() {
        return price;
    }
}
