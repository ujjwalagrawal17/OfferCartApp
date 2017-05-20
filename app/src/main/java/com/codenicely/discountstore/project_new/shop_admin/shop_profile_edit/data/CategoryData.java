package com.codenicely.discountstore.project_new.shop_admin.shop_profile_edit.data;

/**
 * Created by ujjwal on 17/05/17.
 */

public class CategoryData {

    private int id;
    private String name;

    public CategoryData(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}