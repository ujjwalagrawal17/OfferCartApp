package com.codenicely.discountstore.project_new.shop_admin.shop_login.view;

/**
 * Created by ujjwal on 15/5/17.
 */
public interface ShopLoginView {
	void showProgressbar(boolean show);
	void showMessage(String message);
	void onLoginVerified(String access_token);
}