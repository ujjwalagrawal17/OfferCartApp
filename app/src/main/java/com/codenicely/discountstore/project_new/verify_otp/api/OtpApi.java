package com.codenicely.discountstore.project_new.verify_otp.api;

import com.codenicely.discountstore.project_new.helper.Urls;
import com.codenicely.discountstore.project_new.verify_otp.models.data.OtpData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by aman on 16/10/16.
 */
public interface OtpApi {

    @FormUrlEncoded
    @POST(Urls.VERIFY_SHOP_OTP)
    Call<OtpData> requestOtp(@Field("otp") String otp, @Field("mobile") String mobile);
}
