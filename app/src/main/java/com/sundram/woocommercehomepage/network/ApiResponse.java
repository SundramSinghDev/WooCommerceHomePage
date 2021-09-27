package com.sundram.woocommercehomepage.network;

public interface ApiResponse {
    void OnSuccess(Object object);

    void OnError(String error);
}
