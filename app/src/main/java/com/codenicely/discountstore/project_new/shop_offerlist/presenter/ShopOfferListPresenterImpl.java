package com.codenicely.discountstore.project_new.shop_offerlist.presenter;

import com.codenicely.discountstore.project_new.shop_offerlist.ShopOfferCallBack;
import com.codenicely.discountstore.project_new.shop_offerlist.ShopOfferDeleteCallBack;
import com.codenicely.discountstore.project_new.shop_offerlist.model.ShopOfferListProvider;
import com.codenicely.discountstore.project_new.shop_offerlist.model.data.ShopOfferDeleteData;
import com.codenicely.discountstore.project_new.shop_offerlist.model.data.ShopOfferListData;
import com.codenicely.discountstore.project_new.shop_offerlist.view.ShopOfferListView;

/**
 * Created by aman on 16/5/17.
 */

public class ShopOfferListPresenterImpl implements ShopOfferListPresenter {

    private ShopOfferListProvider shopOfferListProvider;
    private ShopOfferListView shopOfferListView;


    public ShopOfferListPresenterImpl(ShopOfferListProvider shopOfferListProvider, ShopOfferListView shopOfferListView) {
        this.shopOfferListProvider = shopOfferListProvider;
        this.shopOfferListView = shopOfferListView;
    }

    @Override
    public void delete(String access_token, int offer_id) {
        shopOfferListView.showProgressBar(true);
        shopOfferListProvider.delete(access_token, offer_id, new ShopOfferDeleteCallBack() {
            @Override
            public void onSuccess(ShopOfferDeleteData shopOfferDeleteData) {
                if(shopOfferDeleteData.isSuccess())
                {
                    shopOfferListView.showProgressBar(false);
                    shopOfferListView.showMessage(shopOfferDeleteData.getMessage());

                }
                else
                {
                    shopOfferListView.showProgressBar(false);
                    shopOfferListView.showMessage(shopOfferDeleteData.getMessage());


                }
            }

            @Override
            public void onFailure(String error) {
                shopOfferListView.showProgressBar(false);
                shopOfferListView.showMessage(error);

            }
        });
    }

    @Override
    public void requestShopOffer(String access_token) {
        shopOfferListView.showProgressBar(true);
        shopOfferListProvider.requestShopOffer(access_token, new ShopOfferCallBack() {
            @Override
            public void onSuccess(ShopOfferListData shopOfferListData) {
                if(shopOfferListData.isSuccess())
                {
                    shopOfferListView.showProgressBar(false);
                    shopOfferListView.showMessage(shopOfferListData.getMessage());
                    shopOfferListView.setData(shopOfferListData);
                }
                else
                {
                    shopOfferListView.showProgressBar(false);
                    shopOfferListView.showMessage(shopOfferListData.getMessage());


                }
            }

            @Override
            public void onFailure(String error) {
                shopOfferListView.showProgressBar(false);
                shopOfferListView.showMessage(error);

            }
        });

    }
}