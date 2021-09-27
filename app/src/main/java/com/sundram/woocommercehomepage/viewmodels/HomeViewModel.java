package com.sundram.woocommercehomepage.viewmodels;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sundram.woocommercehomepage.model.Data;
import com.sundram.woocommercehomepage.network.ApiResponse;
import com.sundram.woocommercehomepage.network.HomeApiCall;
import com.sundram.woocommercehomepage.repository.HomeRepository;

import org.json.JSONObject;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HomeViewModel extends ViewModel {
    private static final String TAG = "HOME_VIEW_MODEL";

    private HomeRepository repository;
    private HomeApiCall apiCall;
    private MutableLiveData<Data> getHomePageData;
    private MutableLiveData<JSONObject> getHomePageDataObj;
    public MutableLiveData<String> errorData = new MutableLiveData<>();

    @Inject
    public HomeViewModel(HomeRepository repository, HomeApiCall apiCall) {
        this.repository = repository;
        this.apiCall = apiCall;
    }

    public MutableLiveData<Data> getHomePageData(Context context) {
        getHomePageData = new MutableLiveData<>();
        apiCall.getHomePageData(apiResponse, context);
        return getHomePageData;
    }

    public MutableLiveData<JSONObject> getHomePageDataObj(Context context) {
        getHomePageDataObj = new MutableLiveData<>();
        apiCall.getHomePageObjData(apiResponseObj, context);
        return getHomePageDataObj;
    }

    private final ApiResponse apiResponse = new ApiResponse() {
        @Override
        public void OnSuccess(Object object) {
            Data data = (Data) object;
            getHomePageData.setValue(data);
        }

        @Override
        public void OnError(String error) {
            Log.i(TAG, "OnError: " + error);
        }
    };

    private final ApiResponse apiResponseObj = new ApiResponse() {
        @Override
        public void OnSuccess(Object object) {
            JSONObject data = (JSONObject) object;
            getHomePageDataObj.setValue(data);
        }

        @Override
        public void OnError(String error) {
            Log.i(TAG, "OnError: " + error);
        }
    };

    @Override
    protected void onCleared() {
        super.onCleared();
        getHomePageDataObj=null;
        getHomePageData = null;
        errorData = null;
    }
}
