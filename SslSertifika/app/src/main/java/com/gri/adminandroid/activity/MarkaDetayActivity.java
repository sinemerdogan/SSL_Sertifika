package com.gri.adminandroid.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gri.adminandroid.R;
import com.gri.adminandroid.adapter.MarkaDetayFragmentAdapter;
import com.gri.adminandroid.model.MarkaProduct;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MarkaDetayActivity extends AppCompatActivity {
    ListView listView;
    List<MarkaProduct> markaProductList;
    MarkaDetayFragmentAdapter adapter;
    String markaId;
    ProgressBar loadingMarkaProgress;
    ImageView markaImageView;
    String markaUrl="http://www.sinemerdogan.com/ssl-service/Markalar/get/";
    String markaProductsUrl = "http://www.sinemerdogan.com/ssl-service/Urunler/getWithCat/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marka_detay);

        markaId = (String)getIntent().getSerializableExtra("MarkaId");

        listView = (ListView)findViewById(R.id.markadetay_listview);
        loadingMarkaProgress =(ProgressBar)findViewById(R.id.markadetay_loading_marka_progress);
        markaImageView =(ImageView)findViewById(R.id.markadetay_marka_imageview);
        markaProductList = new ArrayList<>();
        adapter = new MarkaDetayFragmentAdapter(this,markaProductList);
        listView.setAdapter(adapter);

        markaUrl = markaUrl+markaId;

        final JsonObjectRequest markaImageRequest = new JsonObjectRequest
                (Request.Method.GET, markaUrl, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String markaImageUrl = response.getString("resim");
                            Picasso
                                    .with(markaImageView.getContext())
                                    .load(markaImageUrl)
                                    .into(markaImageView, new Callback() {
                                        @Override
                                        public void onSuccess() {
                                            loadingMarkaProgress.setVisibility(View.GONE);
                                            markaImageView.setVisibility(View.VISIBLE);
                                        }

                                        @Override
                                        public void onError() {
                                        }
                                    });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {


                    }
                });

        Volley.newRequestQueue(this).add(markaImageRequest);

        markaProductsUrl = markaProductsUrl+markaId;

        StringRequest markaProductsRequest = new StringRequest(Request.Method.GET, markaProductsUrl,
                new Response.Listener<String>() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray markaProducts = new JSONArray(response);
                            for(int i=0;i<markaProducts.length();i++){
                                JSONObject jsonObject = markaProducts.getJSONObject(i);
                                MarkaProduct markaProduct = new MarkaProduct();
                                markaProduct.setMarkaSslName(jsonObject.getString("baslik"));
                                markaProduct.setPrice(jsonObject.getString("fiyat"));
                                markaProduct.setStarCount(Integer.valueOf(jsonObject.getString("yildiz")));
                                markaProduct.setBuyUrl(jsonObject.getString("buton_link"));
                                markaProductList.add(markaProduct);
                            }
                            adapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }
        );
        Volley.newRequestQueue(this).add(markaProductsRequest);


    }
}
