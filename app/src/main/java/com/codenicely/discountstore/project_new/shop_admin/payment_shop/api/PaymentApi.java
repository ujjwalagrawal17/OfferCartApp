package com.codenicely.discountstore.project_new.shop_admin.payment_shop.api;

import com.codenicely.discountstore.project_new.helper.Urls;
import com.codenicely.discountstore.project_new.shop_admin.payment_shop.model.data.ShopPaymentData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by aman on 19/5/17.
 */

public interface PaymentApi {

    @GET(Urls.SUB_URL_SHOP_PAYMENT_HASH)
    Call<ShopPaymentData> getPaymentHash(@Query("subscription_id") int id,
                                         @Query("shop_access_token") String access_token);



}
