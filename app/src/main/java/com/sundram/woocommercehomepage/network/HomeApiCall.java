package com.sundram.woocommercehomepage.network;

import android.content.Context;
import android.util.Log;

import com.google.gson.GsonBuilder;
import com.sundram.woocommercehomepage.model.Data;
import com.sundram.woocommercehomepage.repository.HomeRepository;
import com.sundram.woocommercehomepage.utils.ConnectionUtils;
import com.sundram.woocommercehomepage.utils.ConstantUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Collections;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomeApiCall {

    private HomeRepository repository;
    private static final String TAG = "HOME_API_CALL";

    @Inject
    public HomeApiCall(HomeRepository repository) {
        this.repository = repository;
    }

    public void getHomePageData(ApiResponse apiResponse, Context context) {
        if (ConnectionUtils.OnConnectivityCheck(context)) {
            repository.getHomePageDAta()
                    .subscribeOn(Schedulers.io())
                    .map(new Function<Object, Object>() {
                        @Override
                        public Object apply(Object o) throws Throwable {
                            JSONObject jsonObject = new JSONObject(String.valueOf(o));
                            JSONObject jsonObject1 = null;
                            Data data = null;
                            if (jsonObject.has("status")) {
                                if (jsonObject.getBoolean("status")) {
                                    jsonObject1 = jsonObject.getJSONObject("data");
                                    data = new GsonBuilder().create().fromJson(jsonObject.getJSONObject("data").toString(), Data.class);
                                }
                            }
                            Log.i(TAG, "apply: "+o.toString());
                            return data;
                        }
                    })
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(result -> {
                                apiResponse.OnSuccess(result);
                            },
                            error -> {
                                Log.i(TAG, "ERROR getHomePageData: " + error.getMessage());
                                apiResponse.OnError(ConstantUtils.OPS);
                            });

        } else {
            apiResponse.OnError(ConstantUtils.CONNECTION_ERROR);
        }
    }

    public void getHomePageObjData(ApiResponse apiResponse, Context context) {
        if (ConnectionUtils.OnConnectivityCheck(context)) {
            repository.getHomePageDAta()
                    .subscribeOn(Schedulers.io())
                    .map(new Function<Object, Object>() {
                        @Override
                        public Object apply(Object o) throws Throwable {
                            Log.i("response","obj "+o);
                            JSONObject jsonObject = new JSONObject(String.valueOf(o));
                            return jsonObject;
                        }
                    })
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(result -> {
                                apiResponse.OnSuccess(result);
                            },
                            error -> {
                                Log.i(TAG, "ERROR getHomePageData: " + error.getMessage());
                                apiResponse.OnError(ConstantUtils.OPS);
                            });

        } else {
            apiResponse.OnError(ConstantUtils.CONNECTION_ERROR);
        }
    }
}
