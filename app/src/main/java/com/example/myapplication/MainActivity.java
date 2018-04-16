package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.myapplication.adapter.ArticlesAdapter;
import com.example.myapplication.common.NetworkUtil;
import com.example.myapplication.common.UrlUtils;
import com.example.myapplication.controller.AppController;
import com.example.myapplication.controller.BaseActivity;
import com.example.myapplication.model.ResponseBean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerViewActiveSR;
    private List<ResponseBean> responseList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewActiveSR = (RecyclerView) findViewById(R.id.recyclerViewArticles);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewActiveSR.setLayoutManager(linearLayoutManager);
        recyclerViewActiveSR.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        getMostViewedArticles();
        /*ArticlesAdapter articlesAdapter = new ArticlesAdapter(getActivity());
        recyclerViewActiveSR.setAdapter(articlesAdapter);*/
    }

    private void getMostViewedArticles() {

        showLoader();
        NetworkUtil.checkInternetConnection(this, new NetworkUtil.NetworkStatusListener() {
            @Override
            public void onNetworkAvailable() {


                JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                        UrlUtils.GET_MOST_VIEWED_ARTICLES, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e(TAG, response.toString());
                        responseList.clear();
                        try {
                            // Parsing json object response
                            // response will be a json object
                            JSONArray resultsJSONArray = response.getJSONArray("results");
                            for (int i = 0; i < resultsJSONArray.length(); i++) {

                                JSONObject resultsObject = resultsJSONArray.getJSONObject(i);
                                ResponseBean responseBean = new ResponseBean();
                                String resultsTitle = resultsObject.getString("title");
                                String resultAbstract = resultsObject.getString("abstract");
                                String resultsPublishedDate = resultsObject.getString("published_date");
                                responseBean.setTitle(resultsTitle);
                                responseBean.setDescription(resultAbstract);
                                responseBean.setDate(resultsPublishedDate);
                                JSONArray mediaArray = resultsObject.getJSONArray("media");
                                for (int j = 0; j < mediaArray.length(); j++) {

                                    JSONObject mediaObject = mediaArray.getJSONObject(j);
                                    JSONArray mediaMetaDataArray = mediaObject.getJSONArray("media-metadata");
                                    JSONObject mediaMetaDataObject = mediaMetaDataArray.getJSONObject(1);
                                    String urlImage=mediaMetaDataObject.getString("url");
                                    responseBean.setImage(urlImage);
                               }

                                responseList.add(responseBean);
                                //String mobile = phone.getString("mobile");
                            }

                            ArticlesAdapter articlesAdapter = new ArticlesAdapter(MainActivity.this, responseList);
                            recyclerViewActiveSR.setAdapter(articlesAdapter);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                        hideLoader();
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(TAG, "Error: " + error.getMessage());
                        Toast.makeText(getApplicationContext(),
                                error.getMessage(), Toast.LENGTH_SHORT).show();
                        // hide the progress dialog
                        hideLoader();
                    }
                });

                // Adding request to request queue
                AppController.getInstance().addToRequestQueue(jsonObjReq);

            }

            @Override
            public void onNetworkNotAvailable() {
                hideLoader();
            }
        });
    }
}
