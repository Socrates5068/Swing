package com.dev.socrates.swing.ApiRes;

import com.dev.socrates.swing.connect.Link;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class RestauranteApi {
    private onLoadData interfaceevent;
    private AsyncHttpClient client;
    public RestauranteApi(onLoadData interfaceevent) {
        this.interfaceevent = interfaceevent;
        client = new AsyncHttpClient();
    }
    public void loadRestaurant() {
        client.get(Link.RESTAURANT_SERVICE, new JsonHttpResponseHandler(){
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                interfaceevent.onJsonArrayLoad(response);
            }

            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                interfaceevent.onFailure(statusCode, headers, throwable, errorResponse);
            }

        });
    }
}
