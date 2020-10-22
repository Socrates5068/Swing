package com.dev.socrates.swing.ApiResMenu;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public interface onLoad {
    void onJsonLoad(JSONObject data);
    void onJsonArrayLoad(JSONArray data);
    void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse);
}
