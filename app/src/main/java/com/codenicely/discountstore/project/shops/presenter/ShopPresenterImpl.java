package com.codenicely.discountstore.project.shops.presenter;

import com.codenicely.discountstore.project.shops.model.ShopProvider;
import com.codenicely.discountstore.project.shops.model.data.ShopList;
import com.codenicely.discountstore.project.shops.view.OnShopsReceived;
import com.codenicely.discountstore.project.shops.view.ShopView;

/**
 * Created by iket on 23/10/16.
 */
public class ShopPresenterImpl implements ShopPresenter {
    private ShopView shopView;
    private ShopProvider shopProvider;

    public ShopPresenterImpl(ShopView shopView, ShopProvider shopProvider) {
        this.shopView = shopView;
        this.shopProvider = shopProvider;
    }

    @Override
    public void getShops(String access_token, String category_id) {
        shopView.showLoading(true);
        shopProvider.getShops(access_token, category_id, new OnShopsReceived() {
            @Override
            public void onFailure() {
                shopView.showLoading(false);
                shopView.showMessage("Please Check your Internet Connection");
            }

            @Override
            public void onSuccess(ShopList shopList) {
                shopView.showLoading(false);
                if (shopList.isSuccess()) {
                    shopView.OnShopsDataReceived(shopList.getShopDatas());
                } else {
                    shopView.showMessage(shopList.getMessage());
                }
            }
        });

    }
}