package com.codenicely.discountstore.project.welcome_screen.models;

import com.codenicely.discountstore.project.welcome_screen.WelcomeScreenCallback;

/**
 * Created by aman on 16/10/16.
 */
public interface WelcomeScreenProvider {
    void getWelcomeData(WelcomeScreenCallback welcomeScreenCallback);
}