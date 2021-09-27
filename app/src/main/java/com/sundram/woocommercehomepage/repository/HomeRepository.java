package com.sundram.woocommercehomepage.repository;

import com.google.gson.JsonElement;
import com.sundram.woocommercehomepage.apiservices.HomeApiService;

import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class HomeRepository {

    private HomeApiService homeApiService;

    @Inject
    public HomeRepository(HomeApiService homeApiService) {
        this.homeApiService = homeApiService;
    }

    public Observable<JsonElement> getHomePageDAta() {
        HashMap<String, String> params = new HashMap<>();
        return homeApiService.getHomePageData(params);
    }
}
