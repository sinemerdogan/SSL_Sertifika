package com.gri.adminandroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gri.adminandroid.R;
import com.gri.adminandroid.model.MarkaProduct;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mustafa on 15.12.2016.
 */

public class MarkaDetayFragmentAdapter extends ArrayAdapter<MarkaProduct> {
    Context context;
    List<MarkaProduct> markaProductList;

    public MarkaDetayFragmentAdapter(Context context, List<MarkaProduct> markaProductList) {
        super(context, R.layout.activity_marka_detay_item,markaProductList);
        this.context=context;
        this.markaProductList =markaProductList;

    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final MarkaProduct markaProduct = markaProductList.get(position);

        View rowView = inflater.inflate(R.layout.activity_marka_detay_item, parent, false);
        ImageView star1 = (ImageView) rowView.findViewById(R.id.markadetay_item_star1);
        ImageView star2 = (ImageView) rowView.findViewById(R.id.markadetay_item_star2);
        ImageView star3 = (ImageView) rowView.findViewById(R.id.markadetay_item_star3);
        ImageView star4 = (ImageView) rowView.findViewById(R.id.markadetay_item_star4);
        ImageView star5 = (ImageView) rowView.findViewById(R.id.markadetay_item_star5);
        ArrayList<ImageView> stars =new ArrayList< ImageView>();
        stars.add(star1);
        stars.add(star2);
        stars.add(star3);
        stars.add(star4);
        stars.add(star5);

        TextView sslNameTV = (TextView)rowView.findViewById(R.id.markadetay_item_sslname_tv);
        ImageView buyNow = (ImageView) rowView.findViewById(R.id.markadetay_buynow_imageview);
        TextView priceTV =(TextView)rowView.findViewById(R.id.markadetay_item_price_tv);
        LinearLayout parentLinLay = (LinearLayout)rowView.findViewById(R.id.markadetay_item_parent_linlay);

        for(int i=0;i<markaProduct.getStarCount();i++){
            stars.get(i).setVisibility(View.VISIBLE);
        }

        sslNameTV.setText(markaProduct.getMarkaSslName());
        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(markaProduct.getBuyUrl()));
                context.startActivity(i);
            }
        });

        priceTV.setText(markaProduct.getPrice());

        parentLinLay.setBackgroundColor(position%2 == 0
                ?
                context.getResources().getColor(R.color.colorMarkaDetayItem1)
                :
                context.getResources().getColor(R.color.colorMarkaDetayItem2)
        );


        return rowView;
    }


    
}
