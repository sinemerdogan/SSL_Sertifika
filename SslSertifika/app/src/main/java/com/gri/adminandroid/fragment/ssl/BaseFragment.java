package com.gri.adminandroid.fragment.ssl;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.gri.adminandroid.R;
import com.gri.adminandroid.model.Ssl;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;


public class BaseFragment extends Fragment {
    LinearLayout loadingLinLay;
    LinearLayout baseLinLay;
    TextView titleTextView;
    TextView contentTextView;
    ImageView imageView;
    ProgressBar imageProgressBar;
    Ssl ssl;

    public static Fragment newInstance(Ssl ssl) {
        BaseFragment myFragment = new BaseFragment();
        myFragment.setSsl(ssl);
        return myFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_base, container, false);
        titleTextView = (TextView)rootView.findViewById(R.id.base_fragment_title_tv);
        loadingLinLay =(LinearLayout)rootView.findViewById(R.id.base_fragment_loading_linlay);
        contentTextView =(TextView)rootView.findViewById(R.id.base_fragment_content_tv);
        imageView = (ImageView)rootView.findViewById(R.id.base_fragment_imageview);
        imageProgressBar =(ProgressBar)rootView.findViewById(R.id.base_fragment_image_progress);
        baseLinLay = (LinearLayout)rootView.findViewById(R.id.base_fragment_main_linlay) ;


        contentTextView.setMovementMethod(new ScrollingMovementMethod());

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, ssl.getBaseUrl(), null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            ssl.setTitle(response.getString("baslik"));
                            ssl.setContent(response.getString("icerik"));
                            ssl.setImageUrl(response.getString("resim"));
                            titleTextView.setText(ssl.getTitle());
                            contentTextView.setText(Html.fromHtml(ssl.getContent()));

                            Picasso
                                    .with(imageView.getContext())
                                    .load(ssl.getImageUrl())
                                    .into(imageView, new Callback() {
                                @Override
                                public void onSuccess() {
                                    imageProgressBar.setVisibility(View.GONE);
                                    imageView.setVisibility(View.VISIBLE);
                                }

                                @Override
                                public void onError() {
                                }
                            });

                            loadingLinLay.setVisibility(View.GONE);
                            baseLinLay.setVisibility(View.VISIBLE);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {


                    }
                });

        Volley.newRequestQueue(getActivity()).add(jsObjRequest);
        return rootView;
    }


    public void setSsl(Ssl ssl) {
        this.ssl = ssl;
    }
}
