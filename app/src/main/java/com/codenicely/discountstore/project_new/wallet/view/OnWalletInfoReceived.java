package com.codenicely.discountstore.project_new.wallet.view;

import com.codenicely.discountstore.project_new.wallet.model.data.WalletData;

/**
 * Created by iket on 20/10/16.
 */
public interface OnWalletInfoReceived {
    void onFailure();

    void onSuccess(WalletData walletData);
}
