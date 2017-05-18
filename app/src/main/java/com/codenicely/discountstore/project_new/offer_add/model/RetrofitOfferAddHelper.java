package com.codenicely.discountstore.project_new.offer_add.model;

import android.content.Context;
import android.net.Uri;

import com.codenicely.discountstore.project_new.helper.Urls;
import com.codenicely.discountstore.project_new.helper.utils.BitmapUtils;
import com.codenicely.discountstore.project_new.helper.utils.FileUtils;
import com.codenicely.discountstore.project_new.helper.utils.UriUtils;
import com.codenicely.discountstore.project_new.offer_add.api.OfferAddApi;
import com.codenicely.discountstore.project_new.offer_add.data.OfferAddData;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by ujjwal on 18/5/17.
 */
public class RetrofitOfferAddHelper implements OfferAddHelper{
	private Retrofit retrofit;
	private OfferAddApi offerAddApi;
	private Context context;

	public RetrofitOfferAddHelper(Context context) {
		this.context = context;
		HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
		interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


		retrofit = new Retrofit.Builder()
						   .baseUrl(Urls.BASE_URL)
						   .client(client)
						   .addConverterFactory(GsonConverterFactory.create())
						   .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
						   .build();
		offerAddApi =retrofit.create(OfferAddApi.class);
	}


	@Override
	public Observable<OfferAddData> addOffer(String shop_access_token, String offer_name, String offer_description,
											 String offer_price, String expiry_date, Uri imageUri) throws IOException {

		RequestBody shop_access_token1 =
				RequestBody.create(
						MediaType.parse("multipart/form-data"), shop_access_token);

		RequestBody offer_name1 =
				RequestBody.create(
						MediaType.parse("multipart/form-data"), offer_name);
		RequestBody offer_description1 =
				RequestBody.create(
						MediaType.parse("multipart/form-data"), offer_description);

		RequestBody offer_price1 =
				RequestBody.create(
						MediaType.parse("multipart/form-data"), offer_price);
		RequestBody expiry_date1 =
				RequestBody.create(
						MediaType.parse("multipart/form-data"), expiry_date);

		if (imageUri != null) {
			//    File imageFile=new File(imageUri.getPath());
			File imageFile = FileUtils.BitmapToFileConverter(context, BitmapUtils.filePathToBitmapConverter(UriUtils.uriToFilePathConverter(context, imageUri)));
			RequestBody fbody = RequestBody.create(MediaType.parse("multipart/form-data"), imageFile);

			MultipartBody.Part image =
					MultipartBody.Part.createFormData("offer_image", imageFile.getName(), fbody);

			return offerAddApi.requestOfferAdd(shop_access_token1, offer_name1, offer_description1,
					expiry_date1,offer_price1,image);
		}


			return null;
	}
}