package com.codenicely.discountstore.project_new.shop_change_password.presenter;

/**
 * Created by ujjwal on 17/5/17.
 */
public interface ShopChangePasswordPresenter {

	void requestChangePassword(String shop_access_token, String old_password, String new_password);
}
