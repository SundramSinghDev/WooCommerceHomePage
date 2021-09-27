package com.sundram.woocommercehomepage.apiservices;

import static com.sundram.woocommercehomepage.utils.ConstantUtils.HOME_PAGE_DATA_END;


import com.google.gson.JsonElement;

import java.util.HashMap;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface HomeApiService {

    @FormUrlEncoded
    @POST(HOME_PAGE_DATA_END)
    Observable<JsonElement> getHomePageData(@FieldMap HashMap<String,String> params);

}
