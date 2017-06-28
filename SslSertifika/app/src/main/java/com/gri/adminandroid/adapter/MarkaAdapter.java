package com.gri.adminandroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.gri.adminandroid.R;
import com.gri.adminandroid.activity.MarkaDetayActivity;
import com.gri.adminandroid.model.Marka;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by mustafa on 15.12.2016.
 */

public class MarkaAdapter extends ArrayAdapter<Marka> {

    Context context;
    List<Marka> markaList;

    public MarkaAdapter(Context context, List<Marka> markaList) {
        super(context, R.layout.fragment_marka_item,markaList);
        this.context=context;
        this.markaList =markaList;

    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final Marka marka = markaList.get(position);

        View rowView = inflater.inflate(R.layout.fragment_marka_item, parent, false);
        LinearLayout linearLayout = (LinearLayout)rowView.findViewById(R.id.fragment_marka_item_parent_linlay) ;
        linearLayout.setBackgroundColor(position%2 == 0
                ?
                context.getResources().getColor(R.color.colorMarkaDetayItem1)
                :
                context.getResources().getColor(R.color.colorMarkaDetayItem2)
        );
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MarkaDetayActivity.class);
                intent.putExtra("MarkaId",marka.getId());
                context.startActivity(intent);
            }
        });

        ImageView logoImageView = (ImageView)rowView.findViewById(R.id.fragment_marka_item_logo_imageview);
        Picasso
                .with(context)
                .load(marka.getMarkaImageUrl())
                .into(logoImageView, new Callback() {
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError() {
                    }
                });



        return rowView;
    }
}
