According to the values ​​obtained from the humidity sensor in this section, what will be written on the Serial Monitor screen and the relay module will be turned on and off according to these humidity values.
        All of this will be done in a loop, so all operations will be repeated until power down.
        package com.gri.adminandroid.fragment.marka;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gri.adminandroid.R;
import com.gri.adminandroid.adapter.MarkaAdapter;
import com.gri.adminandroid.model.Marka;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sinem erdoğan on 13.10.2016.
 */

public class MarkaFragment extends Fragment {
    public MarkaFragment() {
        // Required empty public constructor
    }





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    private ListView markaListView;
    private List<Marka> markaList;
    String url ="http://www.sinemerdogan.com/ssl-service/Markalar/getAlls";
    MarkaAdapter markaAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_marka, container, false);
        markaListView = (ListView) rootView.findViewById(R.id.marka_listview);
        markaList= new ArrayList<>();
        markaAdapter = new MarkaAdapter(getActivity(),markaList);
        markaListView.setAdapter(markaAdapter);



        StringRequest markaRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray markaArray = new JSONArray(response);
                            for(int i=0; i<markaArray.length(); i++) {
                                JSONObject markaJson = markaArray.getJSONObject(i);
                                Marka marka = new Marka();
                                marka.setId(markaJson.getString("ID"));
                                marka.setMarkaImageUrl(markaJson.getString("resim"));
                                marka.setTitle(markaJson.getString("baslik"));
                                markaList.add(marka);
                            }
                            markaAdapter.notifyDataSetChanged();

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

        Volley.newRequestQueue(getActivity()).add(markaRequest);





        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
