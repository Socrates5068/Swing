package com.dev.socrates.swing.ApiResMenu;

import com.dev.socrates.swing.ApiRes.onLoadData;
import com.dev.socrates.swing.connect.Link;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MenuApi {
    private onLoad interfaceevent;
    private AsyncHttpClient client;
    public MenuApi(onLoad interfaceevent) {
        this.interfaceevent = interfaceevent;
        client = new AsyncHttpClient();
    }
    public void loadMenu() {
        client.get(Link.MENU_SERVICE, new JsonHttpResponseHandler(){
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                interfaceevent.onJsonArrayLoad(response);
            }

            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                interfaceevent.onFailure(statusCode, headers, throwable, errorResponse);
            }

        });
    }
}
