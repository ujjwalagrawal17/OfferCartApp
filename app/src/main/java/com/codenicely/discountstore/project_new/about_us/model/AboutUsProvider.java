package com.codenicely.discountstore.project_new.about_us.model;


import com.codenicely.discountstore.project_new.about_us.AboutUsCallBack;

/**
 * Created by meghal on 13/10/16.
 */

public interface AboutUsProvider {


    void requestAboutUs(AboutUsCallBack aboutUsCallBack);

    void onDestroy();

}