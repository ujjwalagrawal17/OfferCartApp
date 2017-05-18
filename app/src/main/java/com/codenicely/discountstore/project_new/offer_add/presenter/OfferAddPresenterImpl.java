package com.codenicely.discountstore.project_new.offer_add.presenter;

import android.net.Uri;
import android.util.Log;

import com.codenicely.discountstore.project_new.R;
import com.codenicely.discountstore.project_new.helper.MyApplication;
import com.codenicely.discountstore.project_new.offer_add.data.OfferAddData;
import com.codenicely.discountstore.project_new.offer_add.model.OfferAddHelper;
import com.codenicely.discountstore.project_new.offer_add.view.OfferAddView;
import com.codenicely.discountstore.project_new.shop_register.data.ShopRegisterData;

import java.io.File;
import java.io.IOException;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.codenicely.discountstore.project_new.helper.FileProvider.requestNewFile;

/**
 * Created by ujjwal on 12/5/17.
 */
public class OfferAddPresenterImpl implements OfferAddPresenter {
	OfferAddView offerAddView;
	private static final String TAG = "OfferEditPresenterImpl";

	OfferAddHelper offerAddHelper;
	private Observable<OfferAddData> offerAddDataObservable;
	private Subscription subscription;


	public OfferAddPresenterImpl(OfferAddView offerAddView, OfferAddHelper offerAddHelper) {
		this.offerAddView = offerAddView;
		this.offerAddHelper = offerAddHelper;
	}


	@Override
	public void openCamera() {
		File image = requestNewFile();

		if (offerAddView.checkPermissionForCamera()) {
			offerAddView.fileFromPath(image.getPath());
			offerAddView.showCamera();
		} else {
			if (offerAddView.requestCameraPermission()) {
				offerAddView.fileFromPath(image.getPath());
				offerAddView.showCamera();
			}
		}
	}

	@Override
	public void openGallery() {

		if (offerAddView.checkPermissionForGallery()) {

			offerAddView.showGallery();
		} else {

			if (offerAddView.requestGalleryPermission()) {
				offerAddView.showGallery();
			}
		}

	}

	@Override
	public void addOffer(String shop_access_token, String offer_name, String offer_description,
						 String offer_price, String expiry_date, Uri imageUri) {
		offerAddView.showDialogLoader(true);
		try {
			offerAddDataObservable = offerAddHelper.addOffer(shop_access_token,
					offer_name, offer_description,offer_price,expiry_date,imageUri);
			Log.i(TAG, "Value of Observable" + offerAddDataObservable.toString());
			subscription = offerAddDataObservable.subscribeOn(Schedulers.newThread()).
																								 observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<OfferAddData>() {
				@Override
				public void onCompleted() {
					offerAddView.showLoader(false);
				}

				@Override
				public void onError(Throwable e) {

					offerAddView.showLoader(false);
					offerAddView.showMessage(MyApplication.getContext().getResources().getString(R.string.failure_message));
					e.printStackTrace();
				}

				@Override
				public void onNext(OfferAddData offerAddData) {
					Log.i(TAG, "Response " + offerAddData.toString());

					if (offerAddData.isSuccess()) {
						offerAddView.showLoader(false);
						offerAddView.showDialogLoader(false);
						offerAddView.onOfferAdded();
					} else {
						offerAddView.showLoader(false);
						offerAddView.showDialogLoader(false);
						offerAddView.showMessage(offerAddData.getMessage());
					}
				}

			});

		} catch (IOException e) {
			e.printStackTrace();
		}


	}
}
