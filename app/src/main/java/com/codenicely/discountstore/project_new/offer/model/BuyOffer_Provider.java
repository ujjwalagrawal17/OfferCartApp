package com.codenicely.discountstore.project_new.offer.model;

import com.codenicely.discountstore.project_new.offer.OnBuyOffer;

/**
 * Created by meghal on 2/11/16.
 */

public interface BuyOffer_Provider {

    void buyOffer(int offer_id, String access_token, OnBuyOffer onBuyOffer);


}