package com.codenicely.discountstore.project_new.shop_profile_show;

import com.codenicely.discountstore.project_new.shop_profile_show.data.ShowShopProfileData;

/**
 * Created by ujjwal on 16/5/17.
 */
public interface ShowShopProfileCallback {
	void onSuccess(ShowShopProfileData shopProfileData);
	void onFailure();
}
