package com.codenicely.discountstore.project_new.shop_admin.payment_shop.presenter;

/**
 * Created by aman on 19/5/17.
 */

public interface ShopPaymentPresenter {



    void requestShopPaymentHash(int id , String access_token );
    void updateShopPaymentStatus(String access_token, String transaction_id,Boolean success);


}
