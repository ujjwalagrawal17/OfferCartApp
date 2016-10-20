package com.example.aman.offercart_v1.home.view;

import com.example.aman.offercart_v1.home.model.data.CategoryData;

import java.util.List;

/**
 * Created by iket on 19/10/16.
 */
public interface CategoriesView {
    void showMessage(String message);

    void showProgressbar(boolean show);
    void onDataReceived(List<CategoryData> categoryDatas);
}
