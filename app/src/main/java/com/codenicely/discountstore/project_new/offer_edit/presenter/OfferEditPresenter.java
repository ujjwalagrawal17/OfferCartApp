package com.codenicely.discountstore.project_new.offer_edit.presenter;

import android.net.Uri;

/**
 * Created by ujjwal on 12/5/17.
 */
public interface OfferEditPresenter {
	void openCamera();

	/**
	 * This function s called from view if user chooses to select images already present in gallery
	 */
	void openGallery();

	void requestEditOfferOffer(String shop_access_token, String offer_id,String offer_name, String offer_description,
							   String offer_price, String expiry_date, Uri imageUri);

}
