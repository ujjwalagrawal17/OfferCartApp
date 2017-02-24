package com.codenicely.discountstore.project_new.my_orders.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.codenicely.discountstore.project_new.R;
import com.codenicely.discountstore.project_new.helper.image_loader.GlideImageLoader;
import com.codenicely.discountstore.project_new.helper.image_loader.ImageLoader;
import com.codenicely.discountstore.project_new.my_orders.model.data.OrderDetails;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by iket on 3/11/16.
 */
public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private List<OrderDetails> orderDetailsList=new ArrayList<>();
    private Context context;
    private LayoutInflater layoutInflater;
    private ImageLoader imageLoader;
    private MyOrdersFragment myOrdersFragment;


    public Adapter(Context context, MyOrdersFragment myOrdersFragment) {

        this.context=context;
        this.myOrdersFragment = myOrdersFragment;
        layoutInflater = LayoutInflater.from(context);
        imageLoader = new GlideImageLoader(context);

    }
    public void setData(List<OrderDetails> orderDetailsList)
    {
        this.orderDetailsList=orderDetailsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.my_orders_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        OrderDetails ordersDetails=orderDetailsList.get(position);

        holder.title.setText(ordersDetails.getOffer_name());
        holder.address.setText(ordersDetails.getShop_address());
        holder.cost.setText("Offer Price - Rs."+ordersDetails.getOffer_price());
        holder.valid.setText("Offer valid upto "+ordersDetails.getOffer_validity());
        holder.shop.setText(ordersDetails.getShop_name());
        holder.offer_code.setText("Offer Code - "+ordersDetails.getOffer_code());
        imageLoader.loadImage(ordersDetails.getOffer_image(),holder.imageView,holder.imageProgressBar);

    }

    @Override
    public int getItemCount() {
        return orderDetailsList.size();
    }

    protected class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView title,shop,address,valid,cost,offer_code;
        private ImageView imageView;
        private ProgressBar imageProgressBar;


        private MyViewHolder(View itemView) {
            super(itemView);
            offer_code=(TextView)itemView.findViewById(R.id.offer_code);
            title=(TextView)itemView.findViewById(R.id.order_title);
            shop=(TextView)itemView.findViewById(R.id.order_shop);
            address=(TextView)itemView.findViewById(R.id.order_address);
            valid=(TextView)itemView.findViewById(R.id.order_validity);
            cost=(TextView)itemView.findViewById(R.id.order_cost);
            imageView=(ImageView)itemView.findViewById(R.id.image);
            imageProgressBar=(ProgressBar)itemView.findViewById(R.id.imageProgressBar);
        }

    }

}
