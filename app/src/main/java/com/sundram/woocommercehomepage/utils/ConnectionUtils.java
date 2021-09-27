package com.sundram.woocommercehomepage.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class ConnectionUtils {

    private static final String TAG = "ConnectionUtils";
    public static boolean OnConnectivityCheck(Context context) {

        boolean flag = false;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            flag = networkInfo != null && networkInfo.isConnectedOrConnecting();
        } catch (Exception e) {
            Log.i(TAG, "OnConnectivityCheck: "+e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

}
